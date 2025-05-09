package it.ecubit.gameshop.service;

import it.ecubit.gameshop.document.VideogameDocument;
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

    public List<VideogameDocument> search(String title, Double price, Date releaseAfter) {
        Criteria criteria = new Criteria();

        if (title != null && !title.isEmpty()) {
            criteria = criteria.and(new Criteria("titleVideogame").contains(title));
        }

        if (price != null) {
            criteria = criteria.and(new Criteria("priceVideogame").greaterThanEqual(price));
        }

        if (releaseAfter != null) {
            criteria = criteria.and(new Criteria("releaseDateVideogame").greaterThanEqual(releaseAfter));
        }

        CriteriaQuery criteriaQuery = new CriteriaQuery(criteria);

        SearchHits<VideogameDocument> searchHits = elasticsearchOperations.search(criteriaQuery, VideogameDocument.class);

        return searchHits.stream()
                .map(hit -> hit.getContent())
                .toList();
    }
}
