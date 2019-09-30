package com.lostpet.backend.service;

import com.lostpet.backend.entity.Breed;
import com.tudor.dto.NameIdDTO;
import org.springframework.stereotype.Service;

import java.util.List;


public interface BreedService {

    List<Breed> findAll();

    NameIdDTO findById(Long id);

    Breed save(Breed breed);


    void delete(Long id);
}
