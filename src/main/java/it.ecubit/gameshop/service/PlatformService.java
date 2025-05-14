package it.ecubit.gameshop.service;

import it.ecubit.gameshop.dto.PlatformDTO;
import org.springframework.stereotype.Service;

import java.util.List;


public interface PlatformService {

    List<PlatformDTO> readAll();

    PlatformDTO create(PlatformDTO dto);

}
