package com.lostpet.backend.rest.impl;

import com.lostpet.backend.rest.UserRestApi;
import com.lostpet.backend.service.UserService;
import com.tudor.dto.UserDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;

import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;

@RestController
public class UserRestController implements UserRestApi {

    private final UserService userService;

    @Autowired
    public UserRestController(UserService userService) {
        this.userService = userService;
    }

    @Override
    public String testUser() {
        return userService.testService() + " and rest controller";
    }


    @Override
    public UserDTO findById(@PathVariable("id") Long id) {
        return userService.findById(id);
    }

    @Override
    public List<UserDTO> findAll() {
        return userService.findAll();
    }

    @Override
    public Long save(@RequestBody UserDTO userDTO) {
        if(userService.findByUsername(userDTO.getUsername()) == null){
            return userService.save(userDTO);
        }else{
            throw new ResponseStatusException(
                    HttpStatus.IM_USED, "Username already used");
        }

    }

    @Override
    public Long update(@RequestBody UserDTO userDTO) {
        return userService.save(userDTO);
    }

    @Override
    public UserDTO findByUsername(@PathVariable("username") String username) {
//        UserDTO userDetails = userService.findByUsername(username);
//        UserDetails user =
//                User.withDefaultPasswordEncoder()
//                        .username(userDetails.getUsername())
//                        .password(userDetails.getPassword())
//                        .roles(String.valueOf(userDetails.getRole()))
//                        .build();
//        return userDetails;
        return null;
    }

    @Override
    public UserDTO login(String username, String password) {
        System.out.println("Login call with username: " + username + " and password: " + password);
        UserDTO userDTO = userService.login(username,password);
        return userDTO;
    }

    @Override
    public void delete(@PathVariable("id") Long id) {
        userService.delete(id);
    }


//    @Override
//    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
//        UserDTO userDetails = userService.findByUsername(username);
//        UserDetails user =
//                User.withDefaultPasswordEncoder()
//                        .username(userDetails.getUsername())
//                        .password(userDetails.getPassword())
//                        .roles(String.valueOf(userDetails.getRole()))
//                        .build();
//        return user;
//    }
}
