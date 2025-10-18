package com.iuh.se.videoSharingApp.service.impl;

import com.iuh.se.videoSharingApp.dto.request.PermissionAssignmentRequest;
import com.iuh.se.videoSharingApp.entity.Permission;
import com.iuh.se.videoSharingApp.entity.Role;
import com.iuh.se.videoSharingApp.exception.AppException;
import com.iuh.se.videoSharingApp.exception.ErrorCode;
import com.iuh.se.videoSharingApp.repository.PermissionRepository;
import com.iuh.se.videoSharingApp.repository.RoleRepository;
import com.iuh.se.videoSharingApp.service.RoleService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class RoleServiceImpl implements RoleService {
    private final RoleRepository roleRepository;
    private final PermissionRepository permissionRepository;
    @Override
    @PreAuthorize("hasRole('ADMIN')")
    public Role createRole(String name, String description) {
        if (roleRepository.existsById(name))
            throw new AppException(ErrorCode.ROLE_EXISTED);

        Role role = Role.builder()
                .name(name)
                .description(description)
                .permissionNames(List.of())
                .build();
        return roleRepository.save(role);
    }

    @Override
    @PreAuthorize("hasRole('ADMIN')")
    public Role updateRole(String name, String newDescription) {
        Role role = roleRepository.findById(name)
                .orElseThrow(() -> new AppException(ErrorCode.ROLE_NOT_FOUND));

        role.setDescription(newDescription);
        return roleRepository.save(role);
    }

    @Override
    @PreAuthorize("hasRole('ADMIN')")
    public void deleteRole(String name) {
        Role role = roleRepository.findById(name)
                .orElseThrow(() -> new AppException(ErrorCode.ROLE_NOT_FOUND));
        roleRepository.delete(role);
    }

    @Override
    @PreAuthorize("hasRole('ADMIN')")
    public Role addPermissionToRole(String roleName, PermissionAssignmentRequest request) {
        Role role = roleRepository.findById(roleName)
                .orElseThrow(() -> new AppException(ErrorCode.ROLE_NOT_FOUND));

        List<Permission> permissions = permissionRepository.findAllById(request.getPermissionNames());
//        Permission permission = permissionRepository.findById(permissionName)
//                .orElseThrow(() -> new AppException(ErrorCode.PERMISSION_NOT_FOUND));

        permissions.forEach(p -> {
            if (!role.getPermissionNames().contains(p.getName()))
                role.getPermissionNames().add(p.getName());
        });

        return roleRepository.save(role);
    }

    @Override
    @PreAuthorize("hasAuthority('CREATE_VIDEO')")
    public List<Role> getAllRoles() {
        return roleRepository.findAll();
    }
}
