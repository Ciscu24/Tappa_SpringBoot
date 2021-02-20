package com.proyectociscu.tappa_restful.services;

import com.proyectociscu.tappa_restful.exceptions.RecordNotFoundException;
import com.proyectociscu.tappa_restful.model.User;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.proyectociscu.tappa_restful.repositories.UserRepository;

@Service
public class UserService {
    @Autowired
    UserRepository repository;
    
    public List<User> getAllUsers(){
        List<User> userList = repository.findAll();
        
        if(userList.size() > 0){
            return userList;
        }else{
            return new ArrayList<>();
        }
    }
    
    public User getUserById(Long id) throws RecordNotFoundException{
        Optional<User> user = repository.findById(id);
        
        if(user.isPresent()){
            return user.get();
        }else{
            throw new RecordNotFoundException("No user record exist for given id", id);
        }
    }
    
    public User createUser(User entity){
        entity = repository.save(entity);
        return entity;
    }
    
    public User updateUser(User entity) throws RecordNotFoundException{
        if(entity.getId()!=null){
            Optional<User> user = repository.findById(entity.getId());
            
            if(user.isPresent()){
                User newEntity = user.get();
                newEntity.setName(entity.getName());
                newEntity.setSurnames(entity.getSurnames());
                newEntity.setShipping(entity.getShipping());
                newEntity.setCall(entity.getCall());
                newEntity.setEmail(entity.getEmail());
                newEntity.setPassword(entity.getPassword());
                newEntity.setImage(entity.getImage());
                newEntity.setOrders(entity.getOrders());
                
                newEntity = repository.save(newEntity);
                
                return newEntity;
            }else{
                throw  new RecordNotFoundException("User not found", entity.getId());
            }
        }else{
            throw new RecordNotFoundException("No id of user given", 0l);
        }
    }
    
    public User updateCallAndShipping(Long id, int shipping, int call) throws RecordNotFoundException{
        if(id!=null){
            Optional<User> user = repository.findById(id);
            
            if(user.isPresent()){
                User newEntity = user.get();
                newEntity.setShipping(shipping);
                newEntity.setCall(call);
                
                newEntity = repository.save(newEntity);
                
                return newEntity;
            }else{
                throw  new RecordNotFoundException("User not found", id);
            }
        }else{
            throw new RecordNotFoundException("No id of user given", 0l);
        }
    }
    
    public User updateImage(Long id, String image) throws RecordNotFoundException{
        if(id!=null){ 
            Optional<User> user = repository.findById(id);
            
            if(user.isPresent()){
                User newEntity = user.get();
                newEntity.setImage(image);
                
                newEntity = repository.save(newEntity);
                
                return newEntity;
            }else{
                throw  new RecordNotFoundException("User not found", id);
            }
        }else{
            throw new RecordNotFoundException("No id of user given", 0l);
        }
    }
    
    public void deleteUserById(Long id) throws RecordNotFoundException{
        Optional<User> user = repository.findById(id);
        if(user.isPresent()){
            repository.deleteById(id);
        }else{
            throw new RecordNotFoundException("No user record exist for given id", id);
        }
    }
}

