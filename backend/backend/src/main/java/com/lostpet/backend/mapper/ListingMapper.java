package com.lostpet.backend.mapper;


import com.tudor.dto.ListingDTO;
import com.lostpet.backend.entity.Listing;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring", uses = {UserMapper.class , CategoryMapper.class})
public interface ListingMapper {

    ListingDTO toDto(Listing listing);

    Listing toEntity(ListingDTO listingDTO);
}
