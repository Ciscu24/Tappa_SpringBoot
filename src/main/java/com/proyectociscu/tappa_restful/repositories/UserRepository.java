package com.proyectociscu.tappa_restful.repositories;

import com.proyectociscu.tappa_restful.model.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User, Long>{
    
}
