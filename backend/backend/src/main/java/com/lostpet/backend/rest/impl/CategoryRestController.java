//package com.lostpet.backend.rest.impl;
//
//import com.lostpet.backend.service.CategoryService;
//import com.lostpet.backend.rest.CategoryRestApi;
//import com.tudor.dto.CategoryDTO;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.web.bind.annotation.PathVariable;
//import org.springframework.web.bind.annotation.RequestBody;
//import org.springframework.web.bind.annotation.RestController;
//
//import java.util.List;
//
//@RestController
//public class CategoryRestController implements CategoryRestApi {
//
//    private final CategoryService categoryService;
//
//    @Autowired
//    public CategoryRestController(CategoryService categoryService) {
//        this.categoryService = categoryService;
//    }
//
//    @Override
//    public List<CategoryDTO> findAll() {
//        return categoryService.findAll();
//    }
//
//    @Override
//    public Long save(@RequestBody CategoryDTO categoryDTO) {
//        return categoryService.save(categoryDTO);
//    }
//
//    @Override
//    public CategoryDTO findById(@PathVariable("id") Long id) {
//        return categoryService.findById(id);
//    }
//
//
//}
