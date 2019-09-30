package com.lostpet.backend.service.impl;

import com.lostpet.backend.entity.Breed;
import com.lostpet.backend.mapper.BreedMapper;
import com.lostpet.backend.repository.BreedRepository;
import com.lostpet.backend.service.BreedService;
import com.tudor.dto.NameIdDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class BreedServiceImpl implements BreedService {


    private BreedRepository breedRepository;
    private BreedMapper breedMapper;

    @Autowired
    public BreedServiceImpl(BreedRepository breedRepository,BreedMapper breedMapper) {
        this.breedRepository = breedRepository;
        this.breedMapper = breedMapper;
    }

    @Override
    public List<Breed> findAll() {
        return breedRepository.findAll();
    }

    @Override
    public NameIdDTO findById(Long id) {
        return breedMapper.toDto(breedRepository.findById(id).get());
    }

    @Override
    public Breed save(Breed breed) {
        return breedRepository.save(breed);
    }

    @Override
    public void delete(Long id) {
        breedRepository.deleteById(id);
    }
}
