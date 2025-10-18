package com.iuh.se.videoSharingApp;

import com.iuh.se.videoSharingApp.entity.Role;
import com.iuh.se.videoSharingApp.entity.User;
import com.iuh.se.videoSharingApp.repository.RoleRepository;
import com.iuh.se.videoSharingApp.repository.UserRepository;
import jakarta.annotation.PostConstruct;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.bcrypt.BCrypt;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Set;

@Component
@RequiredArgsConstructor
public class DataInitializer {
    private final RoleRepository roleRepository;
    private final UserRepository userRepository;
    @PostConstruct
    public void init() {
        if (roleRepository.count() == 0) {
            Role adminRole = Role.builder()
                    .name("ROLE_ADMIN")
                    .description("Admin user")
                    .permissionNames(List.of())
                    .build();
            roleRepository.save(adminRole);

            String hashed = BCrypt.hashpw("29092004", BCrypt.gensalt());
            User admin = User.builder()
                    .username("admin")
                    .password(hashed)
                    .email("admin123@gmail.com")
                    .roleNames(List.of(adminRole.getName()))
                    .build();

            userRepository.save(admin);

            Role userRole = Role.builder()
                    .name("ROLE_USER")
                    .description("Default role for new users")
                    .permissionNames(List.of())
                    .build();
            roleRepository.save(userRole);


        }
    }
}
