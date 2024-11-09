package com.example.springdataredis.dao;

import com.example.springdataredis.model.Order;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class OrderDao {

    private static final String HASH_KEY="Order";

    private final RedisTemplate template;

    public OrderDao(RedisTemplate template) {
        this.template = template;
    }

    public Order save(Order order){
        template.opsForHash().put(HASH_KEY,order.getId(),order);
        return order;
    }

    public List<Order> findAll(){
        return template.opsForHash().values(HASH_KEY);
    }

    public Order findById(int id){
        return (Order) template.opsForHash().get(HASH_KEY,id);
    }

    public String deleteById(int id){
        template.opsForHash().delete(HASH_KEY,id);
        return "Order deleted successfully";
    }
}
