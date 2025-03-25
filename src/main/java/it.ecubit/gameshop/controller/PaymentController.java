package it.ecubit.gameshop.controller;

import it.ecubit.gameshop.dto.PaymentDTO;
import it.ecubit.gameshop.entity.Payment;
import it.ecubit.gameshop.service.PaymentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("api/payment")
public class PaymentController {
    @Autowired
    private PaymentService service;
    @GetMapping
    public List<PaymentDTO> readAll(){
        return this.service.readAll();
    }
    @PostMapping()
    public PaymentDTO create(@RequestBody PaymentDTO toSave){
        return this.service.save(toSave);
    }
    @PutMapping()
    public PaymentDTO save(@RequestBody PaymentDTO toSave){
        return this.service.save(toSave);
    }
}
