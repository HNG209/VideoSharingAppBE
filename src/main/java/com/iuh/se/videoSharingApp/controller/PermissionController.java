package com.iuh.se.videoSharingApp.controller;

import com.iuh.se.videoSharingApp.dto.request.PermissionCreationRequest;
import com.iuh.se.videoSharingApp.dto.request.UserRegistrationRequest;
import com.iuh.se.videoSharingApp.dto.response.PermissionResponse;
import com.iuh.se.videoSharingApp.dto.response.UserResponse;
import com.iuh.se.videoSharingApp.entity.Permission;
import com.iuh.se.videoSharingApp.service.PermissionService;
import com.iuh.se.videoSharingApp.util.ApiResponse;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/permissions")
@CrossOrigin(origins = "*")
public class PermissionController {
    @Autowired
    private PermissionService permissionService;

    @PostMapping
    public ApiResponse<PermissionResponse> createPermission(@Valid @RequestBody PermissionCreationRequest permission) {
        return ApiResponse.<PermissionResponse>builder()
                .result(permissionService.createPermission(permission))
                .build();
    }
}
