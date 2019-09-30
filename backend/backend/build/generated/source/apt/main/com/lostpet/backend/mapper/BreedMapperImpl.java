package com.lostpet.backend.mapper;

import com.lostpet.backend.entity.Breed;
import com.tudor.dto.NameIdDTO;
import javax.annotation.Generated;
import org.springframework.stereotype.Component;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2019-07-18T20:15:08+0300",
    comments = "version: 1.2.0.Final, compiler: javac, environment: Java 9.0.1 (Oracle Corporation)"
)
@Component
public class BreedMapperImpl implements BreedMapper {

    @Override
    public NameIdDTO toDto(Breed breed) {
        if ( breed == null ) {
            return null;
        }

        NameIdDTO nameIdDTO = new NameIdDTO();

        nameIdDTO.setId( breed.getId() );
        nameIdDTO.setName( breed.getName() );

        return nameIdDTO;
    }

    @Override
    public Breed toEntity(NameIdDTO nameIdDTO) {
        if ( nameIdDTO == null ) {
            return null;
        }

        Breed breed = new Breed();

        breed.setId( nameIdDTO.getId() );
        breed.setName( nameIdDTO.getName() );

        return breed;
    }
}
