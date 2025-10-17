package com.iuh.se.videoSharingApp.controller;

import com.iuh.se.videoSharingApp.dto.request.UserRegistrationRequest;
import com.iuh.se.videoSharingApp.dto.response.UserResponse;
import com.iuh.se.videoSharingApp.service.UserService;
import com.iuh.se.videoSharingApp.util.ApiResponse;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/users")
@CrossOrigin(origins = "*")
public class UserController {
    @Autowired
    private UserService userService;

    @GetMapping
    public ApiResponse<List<UserResponse>> getAll() {
        return ApiResponse.<List<UserResponse>>builder()
                .result(userService.getAll())
                .build();
    }
}
