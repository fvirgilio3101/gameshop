package it.ecubit.gameshop.controller;

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
    public List<Payment> readAll(){
        return this.service.readAll();
    }
    @PostMapping()
    public Payment create(@RequestBody Payment toSave){
        return this.service.save(toSave);
    }
    @PutMapping()
    public Payment save(@RequestBody Payment toSave){
        return this.service.save(toSave);
    }
}
