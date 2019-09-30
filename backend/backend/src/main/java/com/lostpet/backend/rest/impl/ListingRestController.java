package com.lostpet.backend.rest.impl;


import com.lostpet.backend.rest.ListingRestApi;
import com.tudor.dto.ListingDTO;
import com.tudor.dto.SearchParams;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.lostpet.backend.service.ListingService;

import java.util.List;

@RestController
public class ListingRestController implements ListingRestApi {

    private final ListingService listingService;

    @Autowired
    public ListingRestController(ListingService listingService) {
        this.listingService = listingService;
    }

    @Override
    public ListingDTO findById(@PathVariable("id") Long id) {
        System.out.println("Listing find by id:" + id + " fired");
        return listingService.findById(id);
    }

    @Override
    public void delete(@PathVariable("id") Long id) {
        listingService.delete(id);
    }

    @Override
    public List<ListingDTO> findByWriterId(@PathVariable("id") Long id) {
        System.out.println("Listing find by id:" + id + " fired");
//        NameIdDTO writer = new NameIdDTO();
//        writer.setId(id);
        return listingService.findByWriterId(id);
    }



    @Override
    public List<ListingDTO> findAll() {
        System.out.println("Listing api findAll  fired");
        return listingService.findAll();
    }

    @Override
    public Long save(@RequestBody ListingDTO listingDTO) {
        return listingService.save(listingDTO);
    }

    @Override
    public List<ListingDTO> findBySearchParams(@RequestBody SearchParams searchParams) {
        List<ListingDTO> listingDTOS = listingService.searchByParams(searchParams);
        return listingDTOS;
    }

    @Override
    public String test() {
        return "\"The rest API for listing is working\"";
    }
}
