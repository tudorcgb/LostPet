package com.lostpet.backend.mapper;

import com.lostpet.backend.entity.Breed;
import com.tudor.dto.NameIdDTO;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface BreedMapper {

    NameIdDTO toDto(Breed breed);

    Breed toEntity(NameIdDTO nameIdDTO);

}
