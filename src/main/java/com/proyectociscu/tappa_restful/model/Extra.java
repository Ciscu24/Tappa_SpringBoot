package com.proyectociscu.tappa_restful.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import java.util.List;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

@Entity
@Table(name="extra")
public class Extra {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    
    @Column(name="nombre")
    private String name;
    
    @JsonIgnoreProperties(value = {"extra"}, allowSetters = true)
    @OneToMany(mappedBy = "extra", cascade = {CascadeType.MERGE})
    private List<Food> food;
    
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

    public List<Food> getFood() {
        return food;
    }

    public void setFood(List<Food> food) {
        this.food = food;
        for(Food f: food){
            f.setExtra(this);
        }
    }

    @Override
    public String toString() {
        return "Extra{" + "id=" + id + ", name=" + name + '}';
    }
}
