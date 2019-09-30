//package com.lostpet.backend.service.impl;
//
//
//import com.lostpet.backend.service.CategoryService;
//import com.lostpet.backend.mapper.CategoryMapper;
//import com.lostpet.backend.repository.CategoryRepository;
//import com.tudor.dto.CategoryDTO;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.stereotype.Service;
//
//import java.util.List;
//import java.util.stream.Collectors;
//
//@Service
//public class CategoryServiceImpl implements CategoryService {
//
//    private final CategoryRepository categoryRepository;
//    private final CategoryMapper categoryMapper;
//
//    @Autowired
//    public CategoryServiceImpl(CategoryRepository categoryRepository, CategoryMapper categoryMapper) {
//        this.categoryRepository = categoryRepository;
//        this.categoryMapper = categoryMapper;
//    }
//
//
//    @Override
//    public List<CategoryDTO> findAll() {
//        return categoryRepository.findAll().stream().map(categoryMapper::toDto).collect(Collectors.toList());
//    }
//
//    @Override
//    public Long save(CategoryDTO category) {
//        return categoryRepository.save(categoryMapper.toEntity(category)).getId();
//    }
//
//    @Override
//    public CategoryDTO findById(Long id) {
//        return categoryMapper.toDto(categoryRepository.findById(id).get());
//    }
//
//}
