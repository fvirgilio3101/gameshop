package it.ecubit.gameshop.repository;

import it.ecubit.gameshop.document.VideogameDocument;
import org.springframework.data.elasticsearch.repository.ElasticsearchRepository;

import java.util.List;

public interface VideogameDocumentRepository extends ElasticsearchRepository<VideogameDocument, Long> {

    List<VideogameDocument> findByTitleVideogameContainingIgnoreCase(String titleVideogame);

    List<VideogameDocument> findByPriceVideogameLessThanEqual(Double priceVideogame);

    List<VideogameDocument> findByTitleVideogameContainingIgnoreCaseAndPriceVideogameLessThanEqual(String titleVideogame, Double priceVideogame);

    List<VideogameDocument> findByDiscountGreaterThan(Double discount);

/*
    List<VideogameDocument> findBySalesGreaterThan(Integer sales);

 */
}
