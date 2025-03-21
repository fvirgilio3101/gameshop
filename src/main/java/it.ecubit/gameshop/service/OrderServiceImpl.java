package it.ecubit.gameshop.service;

import it.ecubit.gameshop.entity.Order;
import it.ecubit.gameshop.entity.User;
import it.ecubit.gameshop.repository.OrderRepository;
import jakarta.persistence.EntityNotFoundException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class OrderServiceImpl implements OrderService {

    private static final Logger log = LoggerFactory.getLogger(OrderServiceImpl.class);

    @Autowired
    private OrderRepository orderRepository;

    @Override
    public List<Order> readAllByUser(User user) {
        log.info("Avvio raccolta di tutti gli ordini effettuati per l' utente con ID: "+user.getIdUser()+"...");
        try {
            List<Order> orders = this.orderRepository.findAllByUser(user);
            log.info("Gli ordini per l'utente con ID: "+ user.getIdUser() + "sono stati recuperati correttamente");
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
    public Order save(Order order) {
        log.info("Avvio salvataggio ordine " + order);
        try{
            Order entity = this.orderRepository.save(order);
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
