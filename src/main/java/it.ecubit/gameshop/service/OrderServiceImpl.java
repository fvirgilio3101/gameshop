package it.ecubit.gameshop.service;

import it.ecubit.gameshop.entity.Order;
import it.ecubit.gameshop.entity.User;
import it.ecubit.gameshop.repository.OrderRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class OrderServiceImpl implements OrderService {
    @Autowired
    private OrderRepository orderRepository;
    @Override
    public List<Order> readAllByUser(User user) {
        return this.orderRepository.findAllByUser(user) ;
    }

    @Override
    public Order read(Order order) {
        return this.orderRepository.getReferenceById(order.getIdOrder());
    }

    @Override
    public Order save(Order order) {
        return this.orderRepository.save(order);
    }

    @Override
    public void deleteOrder(Order order) {
        this.orderRepository.delete(order);
    }

    @Override
    public void deleteAll() {
        this.orderRepository.deleteAll();
    }
}
