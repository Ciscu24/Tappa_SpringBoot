package com.proyectociscu.tappa_restful.services;

import com.proyectociscu.tappa_restful.exceptions.RecordNotFoundException;
import com.proyectociscu.tappa_restful.model.Food;
import com.proyectociscu.tappa_restful.model.Order;
import com.proyectociscu.tappa_restful.repositories.FoodRepository;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class FoodService {
    @Autowired
    FoodRepository repository;
    
    public List<Food> getAllFood(){
        List<Food> foodList = repository.findAll();
        
        if(foodList.size() > 0){
            return foodList;
        }else{
            return new ArrayList<>();
        }
    }
    
    public Food getFoodById(Long id) throws RecordNotFoundException{
        Optional<Food> food = repository.findById(id);
        
        if(food.isPresent()){
            return food.get();
        }else{
            throw new RecordNotFoundException("No food record exist for given id", id);
        }
    }
    
    public Food createFood(Food entity){
        entity = repository.save(entity);
        return entity;
    }
    
    public Food updateFood(Food entity) throws RecordNotFoundException{
        if(entity.getId()!=null){
            Optional<Food> food = repository.findById(entity.getId());
            
            if(food.isPresent()){
                Food newEntity = food.get();
                newEntity.setName(entity.getName());
                newEntity.setPrice(entity.getPrice());
                newEntity.setOrders(entity.getOrders());
                
                newEntity = repository.save(newEntity);
                
                return newEntity;
            }else{
                throw  new RecordNotFoundException("Food not found", entity.getId());
            }
        }else{
            throw new RecordNotFoundException("No id of food given", 0l);
        }
    }
    
    public void deleteFoodById(Long id) throws RecordNotFoundException{
        Optional<Food> food = repository.findById(id);
        if(food.isPresent()){
            repository.deleteById(id);
        }else{
            throw new RecordNotFoundException("No food record exist for given id", id);
        }
    }
}
