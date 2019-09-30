package com.lostpet.backend.service;


import com.tudor.dto.CategoryDTO;

import java.util.List;


public interface CategoryService {

    List<CategoryDTO> findAll();

    Long save(CategoryDTO category);

    CategoryDTO findById(Long ID);
}
