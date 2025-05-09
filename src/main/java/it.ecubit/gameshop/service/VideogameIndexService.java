package it.ecubit.gameshop.service;
import co.elastic.clients.elasticsearch.ElasticsearchClient;
import co.elastic.clients.elasticsearch._types.query_dsl.BoolQuery;
import co.elastic.clients.elasticsearch._types.query_dsl.MatchQuery;
import co.elastic.clients.elasticsearch._types.query_dsl.Query;
import co.elastic.clients.elasticsearch._types.query_dsl.RangeQuery;
import co.elastic.clients.elasticsearch.core.SearchResponse;
import it.ecubit.gameshop.document.VideogameDocument;
import it.ecubit.gameshop.entity.Videogame;
import it.ecubit.gameshop.repository.VideogameDocumentRepository;
import it.ecubit.gameshop.repository.VideogameRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Service
public class VideogameIndexService {

    @Autowired
    private VideogameRepository videogameRepository;

    @Autowired
    private VideogameDocumentRepository documentRepository;

    @Autowired
    private ElasticsearchClient client;


    public void indexAll() {
        List<Videogame> videogames = videogameRepository.findAll();
        System.out.println("Videogiochi trovati nel DB: " + videogames.size());

        List<VideogameDocument> docs = videogames.stream().map(vg -> {
            VideogameDocument doc = new VideogameDocument();
            doc.setId(vg.getIdVideogame() != null ? vg.getIdVideogame().toString() : "null");
            doc.setTitle(vg.getTitleVideogame());
            doc.setDescription(vg.getDescVideogame());
            doc.setPrice(vg.getPriceVideogame());
            doc.setAverageRating(vg.getAverageRating());
            doc.setReleaseDate(vg.getReleaseDateVideogame());

            System.out.println("Indicizzazione: " + doc.getTitle()); // Log singolo doc
            return doc;
        }).toList();

        documentRepository.saveAll(docs);
        System.out.println("Indicizzati " + docs.size() + " documenti su Elasticsearch.");
    }

    public List<VideogameDocument> search(String keyword, Double price) {
        if (keyword != null && price != null) {
            return documentRepository.findByTitleContainingIgnoreCaseAndPriceLessThanEqual(keyword, price);
        } else if (keyword != null) {
            return documentRepository.findByTitleContainingIgnoreCase(keyword);
        } else if (price != null) {
            return documentRepository.findByPriceLessThanEqual(price);
        } else {
            return (List<VideogameDocument>) documentRepository.findAll();
        }
    }

    public List<VideogameDocument> search(String keyword, Double maxPrice, Date releaseAfter) {
        try {
            List<Query> mustQueries = new ArrayList<>();
            List<Query> filterQueries = new ArrayList<>();

            if (keyword != null && !keyword.isBlank()) {
                mustQueries.add(MatchQuery.of(m -> m
                        .field("title")
                        .query(keyword)
                )._toQuery());
            }

            if (maxPrice != null) {
                filterQueries.add(MatchQuery.of(m -> m
                        .field("price")
                        .query(maxPrice)
                )._toQuery());
            }

            if (releaseAfter != null) {
                filterQueries.add(MatchQuery.of(m -> m
                        .field("releaseDate")
                        .query(releaseAfter.toString())
                )._toQuery());
            }

            BoolQuery boolQuery = BoolQuery.of(b -> b
                    .must(mustQueries)
                    .filter(filterQueries)
            );

            SearchResponse<VideogameDocument> response = client.search(s -> s
                            .index("videogames")
                            .query(q -> q.bool(boolQuery)),
                    VideogameDocument.class
            );

            return response.hits().hits().stream()
                    .map(hit -> hit.source())
                    .toList();

        } catch (IOException e) {
            throw new RuntimeException("Errore durante la ricerca in Elasticsearch", e);
        }
    }
}
