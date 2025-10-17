package com.iuh.se.videoSharingApp.service;

import com.iuh.se.videoSharingApp.dto.response.UserResponse;
import com.iuh.se.videoSharingApp.entity.User;

import java.util.List;

public interface UserService {
    UserResponse register(String username, String email, String password);
    List<UserResponse> getAll();
//    UserResponse login(String email, String password);
}
