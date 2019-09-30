package com.lostpet.backend.rest;

import com.tudor.dto.UserDTO;
//import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RequestMapping("/user")
public interface UserRestApi {

    @GetMapping("/test")
    String testUser();

    @GetMapping("/{id}")
    UserDTO findById(@PathVariable("id") Long id);

    @GetMapping("/list")
    List<UserDTO> findAll();

    @PostMapping("/save")
    Long save(@RequestBody UserDTO userDTO);

    @PutMapping("/update")
    Long update(@RequestBody UserDTO userDTO);

    @GetMapping("/username/{username}")
    UserDTO findByUsername(@PathVariable("username") String username);

    @PostMapping("/login")
    UserDTO login(@RequestParam("username") String username, @RequestParam("password") String password);

    @DeleteMapping("/delete/{id}")
    void delete(@PathVariable("id") Long id);



}
