package com.lostpet.backend.entity;

import com.tudor.dto.NameIdDTO;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

@Entity
public class Breed {

    @Id
    @GeneratedValue
    private Long id;
    private String name;

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

//    public Breed map(NameIdDTO nameIdDTO){
//        Breed breed = new Breed();
//        breed.setId(nameIdDTO.getId());
//        breed.setName(nameIdDTO.getName());
//        return breed;
//    }
}
