package com.example.vegetableecommerce.config;

import java.util.HashSet;
import java.util.Set;

import com.example.vegetableecommerce.entity.Role;
import com.example.vegetableecommerce.entity.User;
import com.example.vegetableecommerce.repository.RoleRepository;
import com.example.vegetableecommerce.repository.UserRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationListener;
import org.springframework.context.event.ContextRefreshedEvent;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

@Component
@Slf4j
public class DataSeeder implements ApplicationListener<ContextRefreshedEvent> {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private RoleRepository roleRepository;

    public BCryptPasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    @Override
    @Transactional
    public void onApplicationEvent(ContextRefreshedEvent arg0) {
        // vai tro
        Role role = new Role();
        if (roleRepository.findByRoleName("ROLE_ADMIN") == null) {
            role.setRoleName("ROLE_ADMIN");
            roleRepository.save(role);
        } else if (roleRepository.findByRoleName("ROLE_USER") == null) {
            role.setRoleName("ROLE_USER");
            roleRepository.save(role);
        }
        // Admin account
        if (userRepository.findByEmail("admin@gmail.com").isEmpty()) {
            Set<Role> roles = new HashSet<>();
            Role roleAdmin = roleRepository.findByRoleName("ROLE_ADMIN");
            roles.add(roleAdmin);

            User admin = new User();

            admin.setEmail("admin@gmail.com");
            admin.setPassword(passwordEncoder().encode("admin"));
            admin.setUsername("admin");
            admin.setPhone("123456789");
            admin.setRole(roles);
            userRepository.save(admin);
            log.info(admin.getUsername() +" user is added. " + admin.getUsername() + " has Admin Role.");
        }
    }
}