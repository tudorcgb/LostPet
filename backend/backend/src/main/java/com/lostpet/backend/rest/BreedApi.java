package com.lostpet.backend.rest;

import com.lostpet.backend.entity.Breed;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RequestMapping("/breeds")
public interface BreedApi {

    @GetMapping("/list")
    List<Breed> findAll();

    @PostMapping("/save")
    Breed save(@RequestBody Breed breed);

    @DeleteMapping("/delete/{id}")
    void delete(@PathVariable("id") Long id);
}