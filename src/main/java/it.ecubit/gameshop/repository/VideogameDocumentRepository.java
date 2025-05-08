package it.ecubit.gameshop.repository;

import it.ecubit.gameshop.document.VideogameDocument;
import org.springframework.data.elasticsearch.repository.ElasticsearchRepository;

import java.util.List;

public interface VideogameDocumentRepository extends ElasticsearchRepository<VideogameDocument, String> {
    List<VideogameDocument> findByTitleContainingIgnoreCase(String title);
    List<VideogameDocument> findByPriceLessThanEqual(Double price);
    List<VideogameDocument> findByTitleContainingIgnoreCaseAndPriceLessThanEqual(String title, Double price);

}
