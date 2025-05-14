package it.ecubit.gameshop.controller;

import it.ecubit.gameshop.dto.PlatformDTO;
import it.ecubit.gameshop.service.PlatformService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("api/platform")
@CrossOrigin(origins = "http://localhost:4200")
public class PlatformController {

    @Autowired
    PlatformService platformService;

    @GetMapping
    public List<PlatformDTO> readAll(){
        return this.platformService.readAll();
    }

    @PostMapping
    public PlatformDTO save(PlatformDTO dto){
        return this.platformService.create(dto);
    }

}
