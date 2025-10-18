package com.iuh.se.videoSharingApp.controller;

import com.iuh.se.videoSharingApp.dto.request.AuthenticationRequest;
import com.iuh.se.videoSharingApp.dto.request.UserRegistrationRequest;
import com.iuh.se.videoSharingApp.dto.response.AuthResponse;
import com.iuh.se.videoSharingApp.dto.response.UserResponse;
import com.iuh.se.videoSharingApp.entity.User;
import com.iuh.se.videoSharingApp.service.AuthService;
import com.iuh.se.videoSharingApp.service.UserService;
import com.iuh.se.videoSharingApp.util.ApiResponse;
import com.nimbusds.jose.JOSEException;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/auth")
@CrossOrigin(origins = "*")
public class AuthController {
    @Autowired
    private UserService userService;

    @Autowired
    private AuthService authService;

    @PostMapping("/register")
    public ApiResponse<UserResponse> register(@Valid @RequestBody UserRegistrationRequest request) {
        return ApiResponse.<UserResponse>builder()
                .result(userService.register(request))
                .build();
    }

    @PostMapping("/login")
    public ApiResponse<AuthResponse> login(@Valid @RequestBody AuthenticationRequest request) throws JOSEException {
        return ApiResponse.<AuthResponse>builder()
                .result(authService.login(request))
                .build();
    }
}
