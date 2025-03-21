package it.ecubit.gameshop.service;

import it.ecubit.gameshop.entity.Payment;
import it.ecubit.gameshop.repository.PaymentRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PaymentServiceImpl implements PaymentService {
    private static final Logger log = LoggerFactory.getLogger(VideogameServiceImpl.class);

    @Autowired
    private PaymentRepository paymentRepository;

    @Override
    public List<Payment> readAll() {
        try {
            List<Payment> payments = this.paymentRepository.findAll();
            return payments;
        } catch (Exception e) {
            log.error("Errore durante la lettura dei pagamenti", e);
            throw new RuntimeException("Errore durante la lettura dei pagamenti", e);
        }
    }

    @Override
    public Payment read(Payment payment) {
        try {
            Payment foundPayment = this.paymentRepository.getReferenceById(payment.getIdPayment());
            return foundPayment;
        } catch (Exception e) {
            log.error("Errore durante la lettura del pagamento con id {}", payment.getIdPayment(), e);
            throw new RuntimeException("Errore durante la lettura del pagamento con id " + payment.getIdPayment(), e);
        }
    }


    @Override
    public Payment save(Payment payment) {
        try {
            Payment savedPayment = this.paymentRepository.save(payment);
            return savedPayment;
        } catch (Exception e) {
            log.error("Errore durante il salvataggio del pagamento", e);
            throw new RuntimeException("Errore durante il salvataggio del pagamento", e);
        }
    }
}
