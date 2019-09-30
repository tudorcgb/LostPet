package com.lostpet.backend.mapper;


import com.tudor.dto.CategoryDTO;
import com.lostpet.backend.entity.Category;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface CategoryMapper {

    CategoryDTO toDto(Category category);

    Category toEntity(CategoryDTO categoryDTO);
}
