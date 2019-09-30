package com.lostpet.backend.mapper;

import com.lostpet.backend.entity.Breed;
import com.lostpet.backend.entity.Listing;
import com.lostpet.backend.entity.User;
import com.tudor.dto.ListingDTO;
import com.tudor.dto.NameIdDTO;
import javax.annotation.Generated;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2019-07-18T20:15:08+0300",
    comments = "version: 1.2.0.Final, compiler: javac, environment: Java 9.0.1 (Oracle Corporation)"
)
@Component
public class ListingMapperImpl implements ListingMapper {

    @Autowired
    private UserMapper userMapper;
    @Autowired
    private CategoryMapper categoryMapper;

    @Override
    public ListingDTO toDto(Listing listing) {
        if ( listing == null ) {
            return null;
        }

        ListingDTO listingDTO = new ListingDTO();

        listingDTO.setId( listing.getId() );
        listingDTO.setTitle( listing.getTitle() );
        listingDTO.setAbstractField( listing.getAbstractField() );
        listingDTO.setLat_end( listing.getLat_end() );
        listingDTO.setLng_end( listing.getLng_end() );
        listingDTO.setLat( listing.getLat() );
        listingDTO.setLng( listing.getLng() );
        listingDTO.setWriter( userMapper.toNameIdDto( listing.getWriter() ) );
        listingDTO.setBreed( breedToNameIdDTO( listing.getBreed() ) );
        listingDTO.setBody( listing.getBody() );
        listingDTO.setImgUrl( listing.getImgUrl() );
        listingDTO.setPierdut( listing.isPierdut() );
        listingDTO.setAdress( listing.getAdress() );

        return listingDTO;
    }

    @Override
    public Listing toEntity(ListingDTO listingDTO) {
        if ( listingDTO == null ) {
            return null;
        }

        Listing listing = new Listing();

        listing.setLat( listingDTO.getLat() );
        listing.setLng( listingDTO.getLng() );
        listing.setId( listingDTO.getId() );
        listing.setTitle( listingDTO.getTitle() );
        listing.setAbstractField( listingDTO.getAbstractField() );
        listing.setWriter( nameIdDTOToUser( listingDTO.getWriter() ) );
        listing.setBody( listingDTO.getBody() );
        listing.setBreed( nameIdDTOToBreed( listingDTO.getBreed() ) );
        listing.setImgUrl( listingDTO.getImgUrl() );
        listing.setLat_end( listingDTO.getLat_end() );
        listing.setLng_end( listingDTO.getLng_end() );
        listing.setPierdut( listingDTO.isPierdut() );
        listing.setAdress( listingDTO.getAdress() );

        return listing;
    }

    protected NameIdDTO breedToNameIdDTO(Breed breed) {
        if ( breed == null ) {
            return null;
        }

        NameIdDTO nameIdDTO = new NameIdDTO();

        nameIdDTO.setId( breed.getId() );
        nameIdDTO.setName( breed.getName() );

        return nameIdDTO;
    }

    protected User nameIdDTOToUser(NameIdDTO nameIdDTO) {
        if ( nameIdDTO == null ) {
            return null;
        }

        User user = new User();

        user.setId( nameIdDTO.getId() );

        return user;
    }

    protected Breed nameIdDTOToBreed(NameIdDTO nameIdDTO) {
        if ( nameIdDTO == null ) {
            return null;
        }

        Breed breed = new Breed();

        breed.setId( nameIdDTO.getId() );
        breed.setName( nameIdDTO.getName() );

        return breed;
    }
}
