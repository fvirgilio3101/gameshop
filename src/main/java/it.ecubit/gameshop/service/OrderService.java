package it.ecubit.gameshop.service;

import it.ecubit.gameshop.entity.Order;
import it.ecubit.gameshop.entity.User;

import java.util.List;

public interface OrderService {

    List<Order> readAllByIdUserOrder(Long userId);
    Order read(Order order);

    Order save(Order order);

    Order addVideogame(Long orderId,List<Long> videogamesIds);
    void deleteOrder(Order order);

    void deleteAll();
}
