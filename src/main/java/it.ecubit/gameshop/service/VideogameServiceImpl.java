package it.ecubit.gameshop.service;

import it.ecubit.gameshop.entity.Videogame;
import it.ecubit.gameshop.repository.VideogameRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Example;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class VideogameServiceImpl implements VideogameService{

    @Autowired
    private VideogameRepository videogameRepository;
    @Override
    public List<Videogame> readAll() {
        return this.videogameRepository.findAll();
    }

    @Override
    public Videogame read(Videogame videogame) {
        return videogameRepository.getReferenceById(videogame.getIdVideogame());
    }

    @Override
    public List<Videogame> readByFilter(Videogame videogame) {
        return this.videogameRepository.findAll(Example.of(videogame));
    }

    @Override
    public Videogame save(Videogame videogame) {
        return this.videogameRepository.save(videogame);
    }

    @Override
    public void deleteVideogame(Videogame videogame) {
       this.videogameRepository.delete(videogame);
    }

    @Override
    public void deleteAll() {
        this.videogameRepository.deleteAll();
    }
}
