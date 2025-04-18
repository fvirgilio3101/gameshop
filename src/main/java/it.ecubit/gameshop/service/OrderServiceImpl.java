package it.ecubit.gameshop.service;

import it.ecubit.gameshop.dto.OrderDTO;
import it.ecubit.gameshop.entity.Order;
import it.ecubit.gameshop.entity.User;
import it.ecubit.gameshop.entity.Videogame;
import it.ecubit.gameshop.mappers.OrderMapper;
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

    @Autowired
    private OrderMapper orderMapper;
    @Override
    public List<OrderDTO> readAllByIdUserOrder(Long userId) {
        log.info("Avvio raccolta di tutti gli ordini effettuati per l' utente con ID: "+userId+"...");
        try {
            List<Order> orders = this.orderRepository.findAllByIdUserOrder(userId);
            log.info("Gli ordini per l'utente con ID: "+ userId + "sono stati recuperati correttamente");
            return orders.stream()
                    .map(orderMapper::orderToOrderDTO)
                    .toList();
        }catch (Exception e){
            log.error("Errore durante la lettura di tutti gli ordini", e);
            throw new RuntimeException("Errore durante il recupero degli ordini", e);
        }

    }

    @Override
    public OrderDTO read(OrderDTO dto) {
        log.info("Avvio lettura ordine con ID: " + dto.getIdOrder());
        try{
            Order entity = this.orderRepository.getReferenceById(dto.getIdOrder());
            log.info("Ordine con ID: " + dto.getIdOrder() + "trovato correttamente" );
            return this.orderMapper.orderToOrderDTO(entity);
        }catch (Exception e){
            throw new EntityNotFoundException("Ordine con ID " + dto.getIdOrder() + " non trovato");
        }

    }

    @Override
    @Transactional
    public OrderDTO save(OrderDTO dto) {
        log.info("Avvio salvataggio ordine " + dto);
        try{
            /*if(order.getVideogames() !=null && !order.getVideogames().isEmpty()){
                List<Videogame> videogames = new ArrayList<Videogame>();
                for(Videogame videogame:videogames) {
                    Videogame persistVideogame = this.videogameRepository.getReferenceById(videogame.getIdVideogame());
                    videogames.add(persistVideogame);
                }

                order.setVideogames(videogames);
            }*/
            Order entity = this.orderRepository.save(this.orderMapper.orderDTOToOrder(dto));
            log.info("Videogames associati: {}", dto.getVideogames());
            return this.orderMapper.orderToOrderDTO(entity);
        }catch (DataIntegrityViolationException e) {
            log.error("Violazione dei vincoli di integrità per l'ordine: {} ", dto, e);
            throw new IllegalArgumentException("Dati non validi per l'utente", e);
        } catch (Exception e) {
            log.error("Errore durante il salvataggio dell'ordine: {}", dto, e);
            throw new RuntimeException("Errore durante il salvataggio dell'utente", e);
        }
    }

    @Override
    public OrderDTO addVideogame(Long orderId,List<Long> videogamesIds) {
        Order order =this.orderRepository.getReferenceById(orderId);
        List<Videogame> videogames = this.videogameRepository.findAllById(videogamesIds);
        order.getVideogames().addAll(videogames);
        this.orderRepository.save(order);
        return this.orderMapper.orderToOrderDTO(order);
    }

    @Override
    public void deleteOrder(OrderDTO dto) {
        log.info("Avvio eliminazione dell'ordine con ID: "+ dto.getIdOrder());
        try{
            this.orderRepository.delete(this.orderMapper.orderDTOToOrder(dto));
            log.info("Ordine eliminato correttamente");
        }catch (EmptyResultDataAccessException e) {
            log.warn("Tentativo di eliminare un ordine non esistente con ID {}", dto.getIdOrder());
            throw new EntityNotFoundException("Si è verificato un problema durante l'eliminazione dell' ordine");
        } catch (Exception e) {
            log.error("Errore durante l'eliminazione dell'ordine con ID {}", dto.getIdOrder(), e);
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
