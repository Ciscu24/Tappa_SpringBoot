package com.proyectociscu.tappa_restful.controllers;

import com.proyectociscu.tappa_restful.exceptions.RecordNotFoundException;
import com.proyectociscu.tappa_restful.model.Order;
import com.proyectociscu.tappa_restful.services.OrderService;
import java.util.List;
import javax.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/order")
public class OrderServiceController {
    
    @Autowired
    OrderService service;

    @GetMapping
    public ResponseEntity<List<Order>> getAllOrders() {
        List<Order> list = service.getAllOrders();

        return new ResponseEntity<List<Order>>(list, new HttpHeaders(), HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Order> getOrderById(@PathVariable("id") Long id) throws RecordNotFoundException {
        Order entity = service.getOrderById(id);

        return new ResponseEntity<Order>(entity, new HttpHeaders(), HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity<Order> createOrder(@Valid @RequestBody Order myOrder) {
        Order entity = service.createOrder(myOrder);

        return new ResponseEntity<Order>(entity, new HttpHeaders(), HttpStatus.OK);
    }

    @PutMapping
    public ResponseEntity<Order> updateOrder(@Valid @RequestBody Order myOrder) throws RecordNotFoundException {
        Order entity = service.updateOrder(myOrder);

        return new ResponseEntity<Order>(entity, new HttpHeaders(), HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    public HttpStatus deleteOrderById(@PathVariable("id") Long id) throws RecordNotFoundException {
        service.deleteOrderById(id);

        return HttpStatus.ACCEPTED;
    }
    
    @GetMapping("/users/{id}")
    public ResponseEntity<List<Order>> getItemsByTitle(@PathVariable("id") Long id){
        List<Order> list = service.getOrderByUserId(id);
        
        return new ResponseEntity<>(list, new HttpHeaders(), HttpStatus.OK);
    }
    
    @GetMapping("/price/{id}")
    public double getAllPriceForOrder(@PathVariable("id") Long id){
        return service.getAllPriceForOrder(id);
    }

}
