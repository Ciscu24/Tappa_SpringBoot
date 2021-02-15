package com.proyectociscu.tappa_restful.repositories;

import com.proyectociscu.tappa_restful.model.Food;
import org.springframework.data.jpa.repository.JpaRepository;

public interface FoodRepository extends JpaRepository<Food, Long>{
    
}
