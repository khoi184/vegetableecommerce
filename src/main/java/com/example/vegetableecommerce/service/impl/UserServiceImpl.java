package com.example.vegetableecommerce.service.impl;

import com.example.vegetableecommerce.auth.CustomUserDetails;
import com.example.vegetableecommerce.dto.SignUpDto;
import com.example.vegetableecommerce.entity.Role;
import com.example.vegetableecommerce.entity.User;
import com.example.vegetableecommerce.exception.UsernameExistedException;
import com.example.vegetableecommerce.repository.RoleRepository;
import com.example.vegetableecommerce.repository.UserRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

@Service
@Slf4j
public class UserServiceImpl implements UserDetailsService {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private RoleRepository roleRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Override
    public UserDetails loadUserByUsername(String username) {
        // Kiểm tra xem user có tồn tại trong database không?
        User user = userRepository.findByUsername(username);
        if (user == null) {
            throw new UsernameNotFoundException(username);
        }
        return new CustomUserDetails(user);
    }

    public User findByUsername(String username) {
        return userRepository.findByUsername(username);
    }

    @Transactional
    public UserDetails loadUserById(Long id) {
        User user = userRepository.findById(id).orElseThrow(
                () -> new UsernameNotFoundException("User not found with id : " + id)
        );

        return new CustomUserDetails(user);
    }

    public void registerUser(SignUpDto signUpDto) throws UsernameExistedException {
        // add check for username exists in a DB
        if(userRepository.existsByUsername(signUpDto.getUsername())){
           throw new UsernameExistedException();
        } else {
            Set<Role> roles= new HashSet<>();
            Role userRole= roleRepository.findByRoleName("ROLE_USER");
            roles.add(userRole);
            // create user object
            User user = new User();
            user.setUsername(signUpDto.getUsername());
            user.setEmail(signUpDto.getEmail());
            user.setPhone(signUpDto.getPhone());
            user.setAddress(signUpDto.getAddress());
            user.setPassword(passwordEncoder.encode(signUpDto.getPassword()));
            user.setRole(roles);
            userRepository.save(user);
            log.info(signUpDto.getUsername() +" user is added. " + signUpDto.getUsername() + " has User Role.");
        }
    }
}