package com.proyectociscu.tappa_restful.services;

import com.proyectociscu.tappa_restful.exceptions.RecordNotFoundException;
import com.proyectociscu.tappa_restful.model.Extra;
import com.proyectociscu.tappa_restful.repositories.ExtraRepository;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ExtraService {
    @Autowired
    ExtraRepository repository;
    
    public List<Extra> getAllExtras(){
        List<Extra> extraList = repository.findAll();
        
        if(extraList.size() > 0){
            return extraList;
        }else{
            return new ArrayList<>();
        }
    }
    
    public Extra getExtraById(Long id) throws RecordNotFoundException{
        Optional<Extra> extra = repository.findById(id);
        
        if(extra.isPresent()){
            return extra.get();
        }else{
            throw new RecordNotFoundException("No extra record exist for given id", id);
        }
    }
    
    public Extra createExtra(Extra entity){
        entity = repository.save(entity);
        return entity;
    }
    
    public Extra updateExtra(Extra entity) throws RecordNotFoundException{
        if(entity.getId()!=null){
            Optional<Extra> extra = repository.findById(entity.getId());
            
            if(extra.isPresent()){
                Extra newEntity = extra.get();
                newEntity.setName(entity.getName());
                
                newEntity = repository.save(newEntity);
                
                return newEntity;
            }else{
                throw  new RecordNotFoundException("Extra not found", entity.getId());
            }
        }else{
            throw new RecordNotFoundException("No id of extra given", 0l);
        }
    }
    
    public void deleteExtraById(Long id) throws RecordNotFoundException{
        Optional<Extra> extra = repository.findById(id);
        if(extra.isPresent()){
            repository.deleteById(id);
        }else{
            throw new RecordNotFoundException("No extra record exist for given id", id);
        }
    }
}
