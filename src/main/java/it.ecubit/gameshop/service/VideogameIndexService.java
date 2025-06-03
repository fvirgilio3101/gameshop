package it.ecubit.gameshop.service;

import it.ecubit.gameshop.document.VideogameDocument;
import it.ecubit.gameshop.entity.Videogame;
import it.ecubit.gameshop.repository.VideogameDocumentRepository;
import it.ecubit.gameshop.repository.VideogameRepository;
import org.elasticsearch.index.query.BoolQueryBuilder;
import org.elasticsearch.index.query.QueryBuilders;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.elasticsearch.core.ElasticsearchOperations;
import org.springframework.data.elasticsearch.core.SearchHits;
import org.springframework.data.elasticsearch.core.query.StringQuery;
import org.springframework.stereotype.Service;
import org.springframework.data.elasticsearch.core.query.Query;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class VideogameIndexService {

    @Autowired
    private ElasticsearchOperations elasticsearchOperations;

    @Autowired
    VideogameDocumentRepository documentRepository;


    public List<VideogameDocument> search(String title, Double price, String releaseAfter, String platformName,String genre) {
        BoolQueryBuilder boolQuery = QueryBuilders.boolQuery();

        if (title != null && !title.isEmpty()) {
            boolQuery.must(QueryBuilders.matchQuery("titleVideogame", title));
        }

        if (price != null) {
            boolQuery.must(QueryBuilders.rangeQuery("priceVideogame").lte(price));
        }

        if (releaseAfter != null) {

            long releaseAfterMillis = Long.parseLong(releaseAfter);
            boolQuery.must(QueryBuilders.rangeQuery("releaseDateVideogame").gte(releaseAfterMillis));
        }

        if (platformName != null && !platformName.isEmpty()) {
            for (String platform : platformName.split(",")) {
                boolQuery.must(QueryBuilders.termQuery("platforms", platform.trim()));
            }
        }

        if (genre != null && !genre.isEmpty()) {
            for (String g : genre.split(",")) {
                boolQuery.must(QueryBuilders.termQuery("genres", g.trim()));
            }
        }

        String query = boolQuery.toString();
        Query searchQuery = new StringQuery(query);

        SearchHits<VideogameDocument> searchHits = elasticsearchOperations.search(searchQuery, VideogameDocument.class);

        return searchHits.stream()
                .map(hit -> hit.getContent())
                .collect(Collectors.toList());
    }

    public List<VideogameDocument> findAll(){
        List<VideogameDocument> docs = new ArrayList<>();
        documentRepository.findAll().forEach(docs::add);
        return docs;

    }

    public List<VideogameDocument> findDiscountedGames() {
        return documentRepository.findByDiscountGreaterThan(10.0);
    }
/*
    public List<VideogameDocument> findBestSellingGames() {
        return documentRepository.findBySalesGreaterThan(10);
    }

 */
}