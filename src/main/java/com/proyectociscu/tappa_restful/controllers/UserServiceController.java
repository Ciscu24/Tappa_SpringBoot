package com.proyectociscu.tappa_restful.controllers;

import com.proyectociscu.tappa_restful.exceptions.RecordNotFoundException;
import com.proyectociscu.tappa_restful.model.User;
import com.proyectociscu.tappa_restful.services.UserService;
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
@RequestMapping("/user")
public class UserServiceController {
    
    @Autowired
    UserService service;

    @GetMapping
    public ResponseEntity<List<User>> getAllUsers() {
        List<User> list = service.getAllUsers();

        return new ResponseEntity<List<User>>(list, new HttpHeaders(), HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<User> getUserById(@PathVariable("id") Long id) throws RecordNotFoundException {
        User entity = service.getUserById(id);

        return new ResponseEntity<User>(entity, new HttpHeaders(), HttpStatus.OK);
    }

    /*@GetMapping("/search/{email}")
    public ResponseEntity<User> getUserByEmail(@PathVariable("email") String email) throws RecordNotFoundException {
        User entity = service.getUserByEmail(email);

        return new ResponseEntity<User>(entity, new HttpHeaders(), HttpStatus.OK);
    }*/

    @PostMapping
    public ResponseEntity<User> createUser(@Valid @RequestBody User myUser) {
        User entity = service.createUser(myUser);

        return new ResponseEntity<User>(entity, new HttpHeaders(), HttpStatus.OK);
    }

    @PutMapping
    public ResponseEntity<User> updateUser(@Valid @RequestBody User myUser) throws RecordNotFoundException {
        User entity = service.updateUser(myUser);

        return new ResponseEntity<User>(entity, new HttpHeaders(), HttpStatus.OK);
    }
    
    @PutMapping("/edit/{id}")
    public ResponseEntity<User> updateCallAndShipping(@PathVariable("id") Long id, @Valid @RequestBody int shipping, @Valid @RequestBody int call) throws RecordNotFoundException {
        User entity = service.updateCallAndShipping(id, shipping, call);

        return new ResponseEntity<User>(entity, new HttpHeaders(), HttpStatus.OK);
    }
    
    @DeleteMapping("/{id}")
    public HttpStatus deleteUserById(@PathVariable("id") Long id) throws RecordNotFoundException {
        service.deleteUserById(id);

        return HttpStatus.ACCEPTED;
    }

}
