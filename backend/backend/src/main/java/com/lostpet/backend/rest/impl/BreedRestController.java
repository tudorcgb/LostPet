package com.lostpet.backend.rest.impl;

import com.lostpet.backend.entity.Breed;
import com.lostpet.backend.rest.BreedApi;
import com.lostpet.backend.service.BreedService;
import com.lostpet.backend.service.impl.BreedServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class BreedRestController implements BreedApi {

    @Autowired
    BreedServiceImpl breedService;

    @Override
    public List<Breed> findAll() {
        return breedService.findAll();
    }

    @Override
    public Breed save(@RequestBody Breed breed) {
        return breedService.save(breed);
    }

    @Override
    public void delete(@PathVariable("id") Long id) {
        breedService.delete(id);
    }
}
