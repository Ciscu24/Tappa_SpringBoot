package com.proyectociscu.tappa_restful.controllers;

import com.proyectociscu.tappa_restful.exceptions.RecordNotFoundException;
import com.proyectociscu.tappa_restful.model.Food;
import com.proyectociscu.tappa_restful.services.FoodService;
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
@RequestMapping("/food")
public class FoodServiceController {
    
    @Autowired
    FoodService service;

    @GetMapping
    public ResponseEntity<List<Food>> getAllFood() {
        List<Food> list = service.getAllFood();

        return new ResponseEntity<List<Food>>(list, new HttpHeaders(), HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Food> getFoodById(@PathVariable("id") Long id) throws RecordNotFoundException {
        Food entity = service.getFoodById(id);

        return new ResponseEntity<Food>(entity, new HttpHeaders(), HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity<Food> createFood(@Valid @RequestBody Food myFood) {
        Food entity = service.createFood(myFood);

        return new ResponseEntity<Food>(entity, new HttpHeaders(), HttpStatus.OK);
    }

    @PutMapping
    public ResponseEntity<Food> updateFood(@Valid @RequestBody Food myFood) throws RecordNotFoundException {
        Food entity = service.updateFood(myFood);

        return new ResponseEntity<Food>(entity, new HttpHeaders(), HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    public HttpStatus deleteFoodById(@PathVariable("id") Long id) throws RecordNotFoundException {
        service.deleteFoodById(id);

        return HttpStatus.ACCEPTED;
    }

}
