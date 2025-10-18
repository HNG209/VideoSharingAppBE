package com.iuh.se.videoSharingApp.service;

import com.iuh.se.videoSharingApp.dto.request.PermissionCreationRequest;
import com.iuh.se.videoSharingApp.dto.response.PermissionResponse;
import com.iuh.se.videoSharingApp.entity.Permission;

public interface PermissionService {
    PermissionResponse createPermission(PermissionCreationRequest request);
}
