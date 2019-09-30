//package com.lostpet.backend.rest;
//
//import com.tudor.dto.CategoryDTO;
//import org.springframework.web.bind.annotation.*;
//
//import java.util.List;
//
//@RequestMapping("/category")
//public interface CategoryRestApi {
//
//    @GetMapping("/list")
//    List<CategoryDTO> findAll();
//
//    @PostMapping("/save")
//    Long save(@RequestBody CategoryDTO categoryDTO);
//
//    @GetMapping("/{id}")
//    CategoryDTO findById(@PathVariable("id") Long id);
//}
