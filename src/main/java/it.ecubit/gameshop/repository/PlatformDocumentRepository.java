package it.ecubit.gameshop.repository;

import it.ecubit.gameshop.document.PlatformDocument;
import org.springframework.data.elasticsearch.repository.ElasticsearchRepository;

public interface PlatformDocumentRepository extends ElasticsearchRepository<PlatformDocument,Long> {
}
