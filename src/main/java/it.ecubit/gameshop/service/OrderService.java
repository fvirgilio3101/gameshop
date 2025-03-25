package it.ecubit.gameshop.service;

import it.ecubit.gameshop.dto.OrderDTO;
import it.ecubit.gameshop.entity.Order;
import it.ecubit.gameshop.entity.User;

import java.util.List;

public interface OrderService {

    List<OrderDTO> readAllByIdUserOrder(Long userId);
    OrderDTO read(OrderDTO order);

    OrderDTO save(OrderDTO order);

    OrderDTO addVideogame(Long orderId,List<Long> videogamesIds);
    void deleteOrder(OrderDTO order);

    void deleteAll();
}
