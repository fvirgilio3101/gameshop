package it.ecubit.gameshop.repository;

import it.ecubit.gameshop.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import it.ecubit.gameshop.entity.Order;

import java.util.List;

@Repository
public interface OrderRepository extends JpaRepository<Order,Long> {

    List<Order> findAllByIdUserOrder(Long userId);
}
