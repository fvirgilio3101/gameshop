package it.ecubit.gameshop.mappers;

import it.ecubit.gameshop.dto.OrderDTO;
import it.ecubit.gameshop.entity.Order;
import org.mapstruct.Mapper;

@Mapper
public interface OrderMapper {

    OrderDTO orderToOrderDTO(Order order);

    Order orderDTOToOrder(OrderDTO dto);
}
