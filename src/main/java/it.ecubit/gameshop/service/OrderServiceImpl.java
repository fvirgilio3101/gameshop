package it.ecubit.gameshop.service;

import it.ecubit.gameshop.entity.Order;
import it.ecubit.gameshop.entity.User;
import it.ecubit.gameshop.entity.Videogame;
import it.ecubit.gameshop.repository.OrderRepository;
import it.ecubit.gameshop.repository.VideogameRepository;
import jakarta.persistence.EntityNotFoundException;
import jakarta.transaction.Transactional;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
public class OrderServiceImpl implements OrderService {

    private static final Logger log = LoggerFactory.getLogger(OrderServiceImpl.class);

    @Autowired
    private OrderRepository orderRepository;
    @Autowired
    private VideogameRepository videogameRepository;

    @Override
    public List<Order> readAllByIdUserOrder(Long userId) {
        log.info("Avvio raccolta di tutti gli ordini effettuati per l' utente con ID: "+userId+"...");
        try {
            List<Order> orders = this.orderRepository.findAllByIdUserOrder(userId);
            log.info("Gli ordini per l'utente con ID: "+ userId + "sono stati recuperati correttamente");
            return orders;
        }catch (Exception e){
            log.error("Errore durante la lettura di tutti gli ordini", e);
            throw new RuntimeException("Errore durante il recupero degli ordini", e);
        }

    }

    @Override
    public Order read(Order order) {
        log.info("Avvio lettura ordine con ID: " + order.getIdOrder());
        try{
            Order entity = this.orderRepository.getReferenceById(order.getIdOrder());
            log.info("Ordine con ID: " + order.getIdOrder() + "trovato correttamente" );
            return entity;
        }catch (Exception e){
            throw new EntityNotFoundException("Ordine con ID " + order.getIdOrder() + " non trovato");
        }

    }

    @Override
    @Transactional
    public Order save(Order order) {
        log.info("Avvio salvataggio ordine " + order);
        try{
            /*if(order.getVideogames() !=null && !order.getVideogames().isEmpty()){
                List<Videogame> videogames = new ArrayList<Videogame>();
                for(Videogame videogame:videogames) {
                    Videogame persistVideogame = this.videogameRepository.getReferenceById(videogame.getIdVideogame());
                    videogames.add(persistVideogame);
                }

                order.setVideogames(videogames);
            }*/
            Order entity = this.orderRepository.save(order);
            log.info("Videogames associati: {}", order.getVideogames());
            return entity;
        }catch (DataIntegrityViolationException e) {
            log.error("Violazione dei vincoli di integrità per l'ordine: {} ", order, e);
            throw new IllegalArgumentException("Dati non validi per l'utente", e);
        } catch (Exception e) {
            log.error("Errore durante il salvataggio dell'ordine: {}", order, e);
            throw new RuntimeException("Errore durante il salvataggio dell'utente", e);
        }
    }

    @Override
    public Order addVideogame(Long orderId,List<Long> videogamesIds) {
        Order order =this.orderRepository.getReferenceById(orderId);
        List<Videogame> videogames = this.videogameRepository.findAllById(videogamesIds);
        order.getVideogames().addAll(videogames);
        this.orderRepository.save(order);
        return order;
    }

    @Override
    public void deleteOrder(Order order) {
        log.info("Avvio eliminazione dell'ordine con ID: "+ order.getIdOrder());
        try{
            this.orderRepository.delete(order);
            log.info("Ordine eliminato correttamente");
        }catch (EmptyResultDataAccessException e) {
            log.warn("Tentativo di eliminare un ordine non esistente con ID {}", order.getIdOrder());
            throw new EntityNotFoundException("Si è verificato un problema durante l'eliminazione dell' ordine");
        } catch (Exception e) {
            log.error("Errore durante l'eliminazione dell'ordine con ID {}", order.getIdOrder(), e);
            throw new RuntimeException("Errore durante l'eliminazione dell'ordine", e);
        }

    }

    @Override
    public void deleteAll() {
        log.info("Avvio eliminazione di tutti gli ordini");
        try{
            this.orderRepository.deleteAll();
        }catch(Exception e){
            log.error("Errore durante l'eliminazione degli ordini", e);
            throw new RuntimeException("Errore durante l'eliminazione di tutti gli ordini", e);
        }
    }
}
