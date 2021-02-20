package com.proyectociscu.tappa_restful.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import java.util.ArrayList;
import java.util.List;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.persistence.Table;
import javax.validation.constraints.NotBlank;

@Entity
@Table(name="comida")
public class Food {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    
    @Column(name="nombre")
    private String name;
    
    @Column(name="precio")
    private double price;
    
    @Column(name="image")
    private String image;
    
    @JsonIgnoreProperties(value = {"addedFood"}, allowSetters = true)
    @ManyToMany(mappedBy = "addedFood", cascade = {CascadeType.MERGE})
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

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
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
            List<Food> addedFood = p.getAddedFood();
            if(addedFood == null){
                addedFood = new ArrayList<>();
            }
            if(!addedFood.contains(this)){
                addedFood.add(this);
            }
        }
    }
    
}
