package com.proyectociscu.tappa_restful.controllers;

import com.proyectociscu.tappa_restful.exceptions.RecordNotFoundException;
import com.proyectociscu.tappa_restful.model.Extra;
import com.proyectociscu.tappa_restful.services.ExtraService;
import java.util.List;
import javax.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@CrossOrigin(origins = "*", maxAge = 3600)
@RequestMapping("/extra")
public class ExtraServiceController {
    
    @Autowired
    ExtraService service;

    @GetMapping
    public ResponseEntity<List<Extra>> getAllExtras() {
        List<Extra> list = service.getAllExtras();

        return new ResponseEntity<List<Extra>>(list, new HttpHeaders(), HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Extra> getExtraById(@PathVariable("id") Long id) throws RecordNotFoundException {
        Extra entity = service.getExtraById(id);

        return new ResponseEntity<Extra>(entity, new HttpHeaders(), HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity<Extra> createExtra(@Valid @RequestBody Extra myExtra) {
        Extra entity = service.createExtra(myExtra);

        return new ResponseEntity<Extra>(entity, new HttpHeaders(), HttpStatus.OK);
    }

    @PutMapping
    public ResponseEntity<Extra> updateExtra(@Valid @RequestBody Extra myExtra) throws RecordNotFoundException {
        Extra entity = service.updateExtra(myExtra);

        return new ResponseEntity<Extra>(entity, new HttpHeaders(), HttpStatus.OK);
    }
    
    @DeleteMapping("/{id}")
    public HttpStatus deleteExtraById(@PathVariable("id") Long id) throws RecordNotFoundException {
        service.deleteExtraById(id);

        return HttpStatus.ACCEPTED;
    }

}
