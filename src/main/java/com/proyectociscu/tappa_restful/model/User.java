package com.proyectociscu.tappa_restful.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import java.util.ArrayList;
import java.util.List;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.Table;
import javax.validation.constraints.NotBlank;

@Entity
@Table(name="usuario")
public class User {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    
    @Column(name="nombre")
    private String name;
    
    @Column(name="apellidos")
    private String surnames;
    
    @Column(name="envio")
    private int shipping;
    
    @Column(name="llamada")
    private int call;
    
    @Column(name="email")
    private String email;
    
    @Column(name="contrasena")
    private String password;
    
    @Column(name="imagen")
    private String image;
    
    @JsonIgnoreProperties(value = {"users"}, allowSetters = true)
    @ManyToMany(mappedBy = "users",cascade = {CascadeType.MERGE})
    private List<Order> orders;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getSurnames() {
        return surnames;
    }

    public void setSurnames(String surnames) {
        this.surnames = surnames;
    }

    public int getShipping() {
        return shipping;
    }

    public void setShipping(int shipping) {
        this.shipping = shipping;
    }

    public int getCall() {
        return call;
    }

    public void setCall(int call) {
        this.call = call;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }
    
    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }
    
    public List<Order> getOrders() {
        return orders;
    }

    public void setOrders(List<Order> orders) {
        this.orders = orders;
        for(Order p : orders){
            List<User> users = p.getUsers();
            if(users == null){
                users = new ArrayList<>();
            }
            if(!users.contains(this)){
                users.add(this);
            }
        }
    }

    @Override
    public String toString() {
        return "User{" + "id=" + id + ", name=" + name + ", surnames=" + surnames + ", shipping=" + shipping + ", call=" + call + ", gmail=" + email + ", orders=" + orders + '}';
    }
    
}
