package com.iuh.se.videoSharingApp.service;

import com.iuh.se.videoSharingApp.dto.request.PermissionAssignmentRequest;
import com.iuh.se.videoSharingApp.entity.Role;

import java.util.List;

public interface RoleService {
    Role createRole(String name, String description);
    Role updateRole(String name, String newDescription);
    void deleteRole(String name);
    Role addPermissionToRole(String roleName, PermissionAssignmentRequest request);
    List<Role> getAllRoles();
}
