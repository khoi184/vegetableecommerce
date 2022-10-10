package com.example.vegetableecommerce.service.impl;

import com.example.vegetableecommerce.auth.CustomUserDetails;
import com.example.vegetableecommerce.dto.SignUpDto;
import com.example.vegetableecommerce.entity.User;
import com.example.vegetableecommerce.exception.UsernameExistedException;
import com.example.vegetableecommerce.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;

@Service
public class UserServiceImpl implements UserDetailsService {

    @Autowired
    private UserRepository userRepository;

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
                // create user object
                User user = new User();
                user.setUsername(signUpDto.getUsername());
                user.setPassword(passwordEncoder.encode(signUpDto.getPassword()));
                userRepository.save(user);
            }
    }

}