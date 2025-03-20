package it.ecubit.gameshop.service;

import it.ecubit.gameshop.entity.Order;
import it.ecubit.gameshop.entity.User;

import java.util.List;

public interface OrderService {

    List<Order> readAllByUser(User user);

    Order read(Order order);

    Order save(Order order);

    void deleteOrder(Order order);

    void deleteAll();
}
