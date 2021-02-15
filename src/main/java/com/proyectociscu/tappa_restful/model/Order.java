package com.proyectociscu.tappa_restful.model;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import java.sql.Date;
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
@Table(name="pedido")
public class Order {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    
    @Column(name="calle")
    private String street;
    
    @Column(name="fecha")
    private Date date;
    
    @JsonIgnoreProperties(value = {"orders"}, allowSetters = true)
    @ManyToMany(cascade = {CascadeType.MERGE})
    @JoinTable(name = "usuario_pedido", joinColumns = @JoinColumn(name = "id_pedido"), inverseJoinColumns = @JoinColumn(name = "id_usuario"))
    private List<User> users;
    
    @JsonIgnoreProperties(value = {"orders"}, allowSetters = true)
    @JoinTable(name = "pedido_comida", joinColumns = @JoinColumn(name = "id_pedido"), inverseJoinColumns = @JoinColumn(name = "id_comida"))
    @ManyToMany(cascade = {CascadeType.MERGE})
    private List<Food> addedFood;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getStreet() {
        return street;
    }

    public void setStreet(String street) {
        this.street = street;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }
    
    public List<User> getUsers() {
        return users;
    }

    public void setUsers(List<User> users) {
        this.users = users;
        for(User u : users){
            List<Order> orders = u.getOrders();
            if(orders == null){
                orders = new ArrayList<>();
            }
            if(!orders.contains(this)){
                orders.add(this);
            }
        }
    }

    public List<Food> getAddedFood() {
        return addedFood;
    }

    public void setAddedFood(List<Food> addedFood) {
        this.addedFood = addedFood;
        for(Food c : addedFood){
            List<Order> orders = c.getOrders();
            if(orders == null){
                orders = new ArrayList<>();
            }
            if(!orders.contains(this)){
                orders.add(this);
            }
        }
    }
}