package it.ecubit.gameshop.service;

import it.ecubit.gameshop.document.PlatformDocument;
import it.ecubit.gameshop.dto.PlatformDTO;
import it.ecubit.gameshop.entity.Platform;
import it.ecubit.gameshop.mappers.PlatformMapper;
import it.ecubit.gameshop.repository.PlatformDocumentRepository;
import it.ecubit.gameshop.repository.PlatformRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class PlatformServiceImpl implements PlatformService{

    @Autowired
    PlatformRepository platformRepository;

    @Autowired
    PlatformMapper platformMapper;

    @Autowired
    PlatformDocumentRepository documentRepository;

    @Override
    public List<PlatformDTO> readAll() {
        try {
            List<Platform> platforms = this.platformRepository.findAll();
            List<PlatformDTO> dtos = platforms.stream()
                    .map(platformMapper :: platformToPlatformDTO)
                    .collect(Collectors.toList());

            return dtos;
        } catch (Exception e) {
            throw new RuntimeException("Errore durante il recupero delle piattaforme", e);
        }
    }

    @Override
    public PlatformDTO create(PlatformDTO dto) {
        try {
           Platform platform = this.platformRepository.save(this.platformMapper.platformDTOToPlatform(dto));
            PlatformDocument doc = new PlatformDocument();

            doc.setIdPlatform(platform.getIdPlatform());
            doc.setName(platform.getName());
            doc.setAbbreviation(platform.getAbbreviation());

            this.documentRepository.save(doc);

            return this.platformMapper.platformToPlatformDTO(platform);
        } catch (Exception e) {
            throw new RuntimeException("Errore durante il salvataggio della piattaforma", e);
        }
    }
}
