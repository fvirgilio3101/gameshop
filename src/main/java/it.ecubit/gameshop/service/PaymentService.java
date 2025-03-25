package it.ecubit.gameshop.service;

import it.ecubit.gameshop.dto.PaymentDTO;
import it.ecubit.gameshop.entity.Payment;

import java.util.List;

public interface PaymentService {

    List<PaymentDTO> readAll();

    PaymentDTO read(PaymentDTO dto);

    PaymentDTO save(PaymentDTO dto);

}
