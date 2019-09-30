package com.lostpet.backend.service;


import com.tudor.dto.ListingDTO;
import com.tudor.dto.NameIdDTO;
import com.tudor.dto.SearchParams;

import java.util.List;


public interface ListingService {

    ListingDTO findById(Long id);

    //List<ListingDTO> findByCategories(List<CategoryDTO> categoryDTOS);

    List<ListingDTO> findByWriter(NameIdDTO writer);

    List<ListingDTO> findByWriterId(Long writer);

    List<ListingDTO> findAll();

    Long save(ListingDTO listingDTO);


    void delete(Long id);

    List<ListingDTO> searchByParams(SearchParams searchParams);
}
