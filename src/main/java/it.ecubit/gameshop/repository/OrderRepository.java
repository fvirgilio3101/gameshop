package it.ecubit.gameshop.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import it.ecubit.gameshop.entity.Order;
@Repository
public interface OrderRepository extends JpaRepository<Order,Long> {
}
