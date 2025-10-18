package com.iuh.se.videoSharingApp.service.impl;

import com.iuh.se.videoSharingApp.dto.request.PermissionCreationRequest;
import com.iuh.se.videoSharingApp.dto.response.PermissionResponse;
import com.iuh.se.videoSharingApp.entity.Permission;
import com.iuh.se.videoSharingApp.mapper.PermissionMapper;
import com.iuh.se.videoSharingApp.repository.PermissionRepository;
import com.iuh.se.videoSharingApp.repository.RoleRepository;
import com.iuh.se.videoSharingApp.service.PermissionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Service;

@Service
public class PermissionServiceImpl implements PermissionService {
    @Autowired
    private PermissionRepository permissionRepository;

    @Autowired
    private PermissionMapper permissionMapper;

    @Override
    @PreAuthorize("hasRole(\"ADMIN\")")
    public PermissionResponse createPermission(PermissionCreationRequest request) {
        return permissionMapper.toPermissionResponse(
                permissionRepository.save(permissionMapper.toPermission(request)));
    }
}
