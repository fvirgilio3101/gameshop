package it.ecubit.gameshop.service;
import it.ecubit.gameshop.document.VideogameDocument;
import it.ecubit.gameshop.entity.Videogame;
import it.ecubit.gameshop.repository.VideogameDocumentRepository;
import it.ecubit.gameshop.repository.VideogameRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class VideogameIndexService {

    @Autowired
    private VideogameRepository videogameRepository;

    @Autowired
    private VideogameDocumentRepository documentRepository;

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
}
