package com.iuh.se.videoSharingApp.service.impl;

import com.iuh.se.videoSharingApp.dto.response.UserResponse;
import com.iuh.se.videoSharingApp.entity.Role;
import com.iuh.se.videoSharingApp.entity.User;
import com.iuh.se.videoSharingApp.exception.AppException;
import com.iuh.se.videoSharingApp.exception.ErrorCode;
import com.iuh.se.videoSharingApp.mapper.UserMapper;
import com.iuh.se.videoSharingApp.repository.RoleRepository;
import com.iuh.se.videoSharingApp.repository.UserRepository;
import com.iuh.se.videoSharingApp.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.crypto.bcrypt.BCrypt;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class UserServiceImpl implements UserService {
    @Autowired
    private UserRepository userRepository;

    @Autowired
    private RoleRepository roleRepository;

    @Autowired
    private UserMapper userMapper;

    @Override
    public UserResponse register(String username, String email, String password) {
        if (userRepository.findByEmail(email).isPresent())
            throw new AppException(ErrorCode.EMAIL_EXISTED);

        Role defaultRole = roleRepository.findById("ROLE_USER")
                .orElseThrow(() -> new AppException(ErrorCode.ROLE_NOT_FOUND));

        String hashed = BCrypt.hashpw(password, BCrypt.gensalt());
        User user = User.builder()
                .username(username)
                .email(email)
                .password(hashed)
                .roleNames(List.of(defaultRole.getName()))
                .build();

        return userMapper.toUserResponse(userRepository.save(user));
    }

    @Override
    @PreAuthorize("hasRole(\"ADMIN1\")")
    public List<UserResponse> getAll() {
        return userRepository.findAll()
                .stream()
                .map(userMapper::toUserResponse)
                .toList();
    }
}
