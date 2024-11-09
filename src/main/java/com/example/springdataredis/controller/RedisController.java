package com.example.springdataredis.controller;

import com.example.springdataredis.dao.OrderDao;
import com.example.springdataredis.model.Order;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/order")
public class RedisController {
    private final OrderDao orderDao;

    public RedisController(OrderDao orderDao) {
        this.orderDao = orderDao;
    }

    @PostMapping
    public ResponseEntity<Order> save(@RequestBody Order order){
        return new ResponseEntity<>(orderDao.save(order), HttpStatus.CREATED);
    }

    @GetMapping
    public ResponseEntity<List<Order>> getAllOrders(){
        return ResponseEntity.ok(orderDao.findAll());
    }

    @GetMapping("/{id}")
    public ResponseEntity<Order> getOrderById(@PathVariable("id") Integer id){
        return ResponseEntity.ok(orderDao.findById(id));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteOrderById(@PathVariable("id") Integer id){
        return ResponseEntity.ok(orderDao.deleteById(id));
    }


}
