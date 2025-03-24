package it.ecubit.gameshop.controller;

import it.ecubit.gameshop.entity.Order;
import it.ecubit.gameshop.entity.User;
import it.ecubit.gameshop.service.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("api/order")
public class OrderController {
    @Autowired
    private OrderService service;

    @GetMapping(value = "/{userId}")
    public List<Order> readAll(@PathVariable("userId") Long userId){
        return this.service.readAllByIdUserOrder(userId);
    }

    @PostMapping()
    public Order create(@RequestBody Order toSave){
        return this.service.save(toSave);
    }

    @PutMapping()
    public Order save(@RequestBody Order toSave){
        return this.service.save(toSave);
    }

    @PutMapping(value="/{orderId}/videogames")
    public Order addVideogames(@PathVariable("orderId")Long orderId,@RequestBody List<Long> videogameIds){
        return this.service.addVideogame(orderId,videogameIds);
    }
    @DeleteMapping
    public void delete(@RequestBody Order toDelete){
        this.service.deleteOrder(toDelete);
    }
}
