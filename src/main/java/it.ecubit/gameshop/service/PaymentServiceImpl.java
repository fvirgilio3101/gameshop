package it.ecubit.gameshop.service;

import it.ecubit.gameshop.dto.PaymentDTO;
import it.ecubit.gameshop.entity.Payment;
import it.ecubit.gameshop.mappers.PaymentMapper;
import it.ecubit.gameshop.repository.PaymentRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class PaymentServiceImpl implements PaymentService {
    private static final Logger log = LoggerFactory.getLogger(VideogameServiceImpl.class);

    @Autowired
    private PaymentRepository paymentRepository;

    private PaymentMapper paymentMapper;

    @Override
    public List<PaymentDTO> readAll() {
        try {
            List<Payment> payments = this.paymentRepository.findAll();
            List<PaymentDTO> dtos = payments.stream()
                    .map(paymentMapper::paymentToPaymentDTO)
                    .collect(Collectors.toList());
            return dtos;
        } catch (Exception e) {
            log.error("Errore durante la lettura dei pagamenti", e);
            throw new RuntimeException("Errore durante la lettura dei pagamenti", e);
        }
    }

    @Override
    public PaymentDTO read(PaymentDTO dto) {
        try {
            Payment payment = this.paymentMapper.paymentDTOToPayment(dto);
            Payment foundPayment = this.paymentRepository.getReferenceById(payment.getIdPayment());
            return this.paymentMapper.paymentToPaymentDTO(foundPayment);
        } catch (Exception e) {
            log.error("Errore durante la lettura del pagamento con id {}", dto.getIdPayment(), e);
            throw new RuntimeException("Errore durante la lettura del pagamento con id " + dto.getIdPayment(), e);
        }
    }


    @Override
    public PaymentDTO save(PaymentDTO dto) {
        try {
            Payment savedPayment = this.paymentRepository.save(this.paymentMapper.paymentDTOToPayment(dto));
            return this.paymentMapper.paymentToPaymentDTO(savedPayment);
        } catch (Exception e) {
            log.error("Errore durante il salvataggio del pagamento", e);
            throw new RuntimeException("Errore durante il salvataggio del pagamento", e);
        }
    }
}
