package it.ecubit.gameshop.service;

import it.ecubit.gameshop.entity.Order;

import java.util.List;

public interface OrderService {

    List<Order> readAllByUserId(Long userId);

    Order read(Order order);

    Order save(Order order);

    void deleteOrder(Order order);

    void deleteAll();
}
