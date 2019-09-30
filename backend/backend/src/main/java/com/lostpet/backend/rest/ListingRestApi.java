package com.lostpet.backend.rest;

import com.tudor.dto.ListingDTO;
import com.tudor.dto.SearchParams;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RequestMapping("/listing")
public interface ListingRestApi {

    @GetMapping("/{id}")
    ListingDTO findById(@PathVariable ("id") Long id);

    @DeleteMapping("/delete/{id}")
    void delete(@PathVariable ("id") Long id);

    @GetMapping("/writer/{id}")
    List<ListingDTO> findByWriterId(@PathVariable ("id") Long id);

    @GetMapping("/list")
    List<ListingDTO> findAll();

    @PostMapping("/save")
    Long save(@RequestBody ListingDTO listingDTO);

    @PostMapping("/search")
    List<ListingDTO> findBySearchParams(@RequestBody SearchParams searchParams);

    @GetMapping("/test")
    String test();
}
