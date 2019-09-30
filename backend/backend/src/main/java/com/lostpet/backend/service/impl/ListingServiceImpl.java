package com.lostpet.backend.service.impl;


import com.lostpet.backend.mapper.BreedMapper;
import com.lostpet.backend.mapper.ListingMapper;
import com.lostpet.backend.service.ListingService;
import com.lostpet.backend.repository.ListingRepository;
import com.tudor.dto.ListingDTO;
import com.tudor.dto.NameIdDTO;
import com.lostpet.backend.entity.Listing;
import com.tudor.dto.SearchParams;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.persistence.EntityNotFoundException;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class ListingServiceImpl implements ListingService {

    private final ListingRepository listingRepository;
    private final ListingMapper listingMapper;
    //private final CategoryMapper categoryMapper;
    private final BreedMapper breedMapper;


    @Autowired
    public ListingServiceImpl(ListingRepository listingRepository,
                              ListingMapper listingMapper, BreedMapper breedMapper) {
        this.listingRepository = listingRepository;
        this.listingMapper = listingMapper;
        this.breedMapper = breedMapper;
    }

    @Override
    public ListingDTO findById(Long id) {
        Listing listing = listingRepository.findById(id).orElseThrow(() ->new EntityNotFoundException("Cannot find listing with ID: " + id));
        return listingMapper.toDto(listing);

    }

//    @Override
//    public List<ListingDTO> findByCategories(List<CategoryDTO> categoryDTOS) {
//        return listingRepository.findByCategories(categoryDTOS.stream().map(categoryMapper::toEntity).collect(Collectors.toList()))
//                .stream()
//                .map(listingMapper::toDto)
//                .collect(Collectors.toList());
//}


    @Override
    public List<ListingDTO> findByWriter(NameIdDTO writer) {
//        articleRepository.findByWriter(u).stream().map(listingMapper
//        ::toDto).collect(Collectors.toList());
        return null;
    }
    @Override
    public List<ListingDTO> findByWriterId(Long writer) {
        return listingRepository.findByWriterId(writer).stream()
                .map(listingMapper::toDto)
                .collect(Collectors.toList());

    }

    @Override
    public List<ListingDTO> findAll() {

        return listingRepository.findAll()
                .stream()
                .map(listingMapper::toDto)
                .collect(Collectors.toList());
    }

    @Override
    public Long save(ListingDTO listingDTO) {
        return listingRepository.save(listingMapper.toEntity(listingDTO)).getId();
    }

    @Override
    public void delete(Long id) {
        listingRepository.deleteById(id);
    }

    @Override
    public List<ListingDTO> searchByParams(SearchParams searchParams) {
        return listingRepository.findBySearchParams(searchParams.getBreed(), searchParams.isPierdut(), searchParams.getSearchString()
                , searchParams.getLat(), searchParams.getLng(), searchParams.getLat_end(), searchParams.getLng_end())
                .stream()
                .map(listingMapper::toDto)
                .collect(Collectors.toList());
    }
}
