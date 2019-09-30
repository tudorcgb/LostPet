package com.lostpet.backend.service.impl;

import com.lostpet.backend.entity.User;
import com.lostpet.backend.mapper.UserMapper;
import com.lostpet.backend.repository.UserRepository;
import com.lostpet.backend.service.UserService;
import com.tudor.dto.UserDTO;
import com.tudor.enumeration.UserRole;
import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.stereotype.Service;

import javax.persistence.EntityNotFoundException;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;
    private final UserMapper userMapper;

    @Autowired
    public UserServiceImpl(UserRepository userRepository, UserMapper userMapper) {
        this.userRepository = userRepository;
        this.userMapper = userMapper;
    }

    @Override
    public String testService() {
        return "Service is working";
    }

    @Override
    public UserDTO login(String username, String password) {
        User user = userRepository.findByUsername(username);
        UserDTO returnUser = new UserDTO();
        returnUser.setRole(user.getRole());
        if(user.getPassword().equals(password)) {
            returnUser.setId(user.getId());
            return returnUser;
        }
        else {
            returnUser.setId((long) -1);
            return returnUser;
        }
    }

    @Override
    public Long save(UserDTO userDTo) {
        User user = userMapper.toEntity(userDTo);
        user.setRole(UserRole.USER);
        return userRepository.save(user).getId();
    }

    @Override
    public UserDTO findById(Long id) {
        User entity = userRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Cannot find user with ID: " + id));
        UserDTO user = userMapper.toDto(entity);
        return user;
    }

    @Override
    public List<UserDTO> findAll() {
        return userRepository.findAll().stream().map(userMapper::toDto).collect(Collectors.toList());
    }
//    @Override
//    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
//
//        final User user = userRepository.findByUsername(username);
//
//        if (user == null) {
//            throw new UsernameNotFoundException(username + " does not exist");
//        }
//
//        final Set<GrantedAuthority> grantedAuthorities = new HashSet<>();
//        grantedAuthorities.add(new SimpleGrantedAuthority("ROLE_" + user.getRole().toString()));
//
//
//        return new org.springframework.security.core.userdetails.User(user.getUsername(), user.getPassword(), grantedAuthorities);
//    }

    @Override
    public UserDTO findByUsername(String username) {
//        UserDTO userDetails = userMapper.toDto(userRepository.findByUsername(username));
//        if(userDetails != null) {
//            UserDetails user =
//                    org.springframework.security.core.userdetails.User.withDefaultPasswordEncoder()
//                            .username(userDetails.getUsername())
//                            .password(userDetails.getPassword())
//                            .roles(String.valueOf(userDetails.getRole()))
//                            .build();
//            return userDetails;
//        }
        return null;
    }

    @Override
    public void delete(Long id) {
        userRepository.deleteById(id);
    }

}
