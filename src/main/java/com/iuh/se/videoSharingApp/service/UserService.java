package com.iuh.se.videoSharingApp.service;

import com.iuh.se.videoSharingApp.dto.request.UserRegistrationRequest;
import com.iuh.se.videoSharingApp.dto.response.UserResponse;
import com.iuh.se.videoSharingApp.entity.User;

import java.util.List;

public interface UserService {
    UserResponse register(UserRegistrationRequest request);
    List<UserResponse> getAll();
//    UserResponse login(String email, String password);
}
