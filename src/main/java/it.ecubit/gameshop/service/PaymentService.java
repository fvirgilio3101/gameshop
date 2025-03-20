package it.ecubit.gameshop.service;

import it.ecubit.gameshop.entity.Payment;

import java.util.List;

public interface PaymentService {

    List<Payment> readAll();

    Payment read(Payment payment);

    Payment save(Payment payment);

}
