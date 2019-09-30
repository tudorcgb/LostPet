package com.lostpet.backend.service;

import com.tudor.dto.UserDTO;


import java.util.List;


public interface UserService {

    String testService();

    UserDTO login(String username, String password);

    Long save(UserDTO userDTo);

    UserDTO findById(Long id);

    List<UserDTO> findAll();

    //UserDetails loadUserByUsername(String username) throws UsernameNotFoundException;

    UserDTO findByUsername(String username);

    void delete(Long id);
}
