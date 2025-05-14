package it.ecubit.gameshop.service;

import it.ecubit.gameshop.document.VideogameDocument;
import it.ecubit.gameshop.entity.Videogame;
import it.ecubit.gameshop.repository.VideogameDocumentRepository;
import it.ecubit.gameshop.repository.VideogameRepository;
import org.apache.lucene.search.join.ScoreMode;
import org.elasticsearch.index.query.BoolQueryBuilder;
import org.elasticsearch.index.query.NestedQueryBuilder;
import org.elasticsearch.index.query.QueryBuilders;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.elasticsearch.core.ElasticsearchOperations;
import org.springframework.data.elasticsearch.core.query.Criteria;
import org.springframework.data.elasticsearch.core.query.CriteriaQuery;
import org.springframework.data.elasticsearch.core.SearchHits;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

@Service
public class VideogameIndexService {

    @Autowired
    private ElasticsearchOperations elasticsearchOperations;

    @Autowired
    VideogameRepository videogameRepository;

    @Autowired
    VideogameDocumentRepository documentRepository;

    public void indexAll() {
        List<Videogame> videogames = videogameRepository.findAll();
        System.out.println("Videogiochi trovati nel DB: " + videogames.size());

        List<VideogameDocument> docs = videogames.stream().map(vg -> {
            VideogameDocument doc = new VideogameDocument();
            doc.setIdVideogame(vg.getIdVideogame() != null ? vg.getIdVideogame() : null);
            doc.setTitleVideogame(vg.getTitleVideogame());
            doc.setDescVideogame(vg.getDescVideogame());
            doc.setPriceVideogame(vg.getPriceVideogame());
            doc.setRating(vg.getAverageRating());
            doc.setReleaseDateVideogame(vg.getReleaseDateVideogame());

            System.out.println("Indicizzazione: " + doc.getTitleVideogame()); // Log singolo doc
            return doc;
        }).toList();

        documentRepository.saveAll(docs);
        System.out.println("Indicizzati " + docs.size() + " documenti su Elasticsearch.");
    }

    public List<VideogameDocument> search(String title, Double price, Date releaseAfter,String platformName) {

        BoolQueryBuilder boolQuery = QueryBuilders.boolQuery();
        Criteria criteria = new Criteria();

        if (title != null && !title.isEmpty()) {
            criteria = criteria.and(new Criteria("titleVideogame").contains(title));
        }

        if (price != null) {
            criteria = criteria.and(new Criteria("priceVideogame").lessThanEqual(price));
        }

        if (releaseAfter != null) {
            criteria = criteria.and(new Criteria("releaseDateVideogame").greaterThanEqual(releaseAfter));
        }

        if (platformName != null && !platformName.isEmpty()) {
            BoolQueryBuilder platformBool = QueryBuilders.boolQuery()
                    .must(QueryBuilders.matchQuery("platforms.name", platformName))
                    .must(QueryBuilders.matchQuery("platforms.abbreviation", platformName));

            NestedQueryBuilder platformNested = QueryBuilders.nestedQuery(
                    "platforms",
                    platformBool,
                    ScoreMode.None
            );
            boolQuery.filter(platformNested);
        }

        CriteriaQuery criteriaQuery = new CriteriaQuery(criteria);

        SearchHits<VideogameDocument> searchHits = elasticsearchOperations.search(criteriaQuery, VideogameDocument.class);

        return searchHits.stream()
                .map(hit -> hit.getContent())
                .toList();
    }
}
