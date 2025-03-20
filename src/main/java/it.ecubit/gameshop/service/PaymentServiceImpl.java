package it.ecubit.gameshop.service;

import it.ecubit.gameshop.entity.Payment;
import it.ecubit.gameshop.repository.PaymentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PaymentServiceImpl implements PaymentService {

    @Autowired
    private PaymentRepository paymentRepository;


    @Override
    public List<Payment> readAll() {
        return this.paymentRepository.findAll();
    }

    @Override
    public Payment read(Payment payment) {
        return this.paymentRepository.getReferenceById(payment.getIdPayment());
    }

    @Override
    public Payment save(Payment payment) {
        return this.paymentRepository.save(payment);
    }
}
