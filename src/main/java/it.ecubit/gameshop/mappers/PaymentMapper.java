package it.ecubit.gameshop.mappers;

import it.ecubit.gameshop.dto.PaymentDTO;
import it.ecubit.gameshop.entity.Payment;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface PaymentMapper {

    PaymentDTO paymentToPaymentDTO(Payment payment);

    Payment paymentDTOToPayment(PaymentDTO dto);
}
