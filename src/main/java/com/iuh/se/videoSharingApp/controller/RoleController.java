package com.iuh.se.videoSharingApp.controller;

import com.iuh.se.videoSharingApp.dto.request.PermissionAssignmentRequest;
import com.iuh.se.videoSharingApp.entity.Role;
import com.iuh.se.videoSharingApp.service.RoleService;
import com.iuh.se.videoSharingApp.util.ApiResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/roles")
@RequiredArgsConstructor
public class RoleController {

    private final RoleService roleService;

    @PostMapping
    public ApiResponse<Role> createRole(@RequestParam String name,
                                        @RequestParam String description) {
        return ApiResponse.<Role>builder()
                .result(roleService.createRole(name, description))
                .build();
    }

    @PutMapping("/{name}")
    public ApiResponse<Role> updateRole(@PathVariable String name,
                                        @RequestParam String description) {
        return ApiResponse.<Role>builder()
                .result(roleService.updateRole(name, description))
                .build();
    }

    @DeleteMapping("/{name}")
    public ApiResponse<Void> deleteRole(@PathVariable String name) {
        roleService.deleteRole(name);
        return ApiResponse.<Void>builder().message("Deleted successfully").build();
    }

    @PostMapping("/{roleName}/permissions")
    public ApiResponse<Role> addPermissionToRole(@PathVariable String roleName,
                                                 @RequestBody PermissionAssignmentRequest request) {
        return ApiResponse.<Role>builder()
                .result(roleService.addPermissionToRole(roleName, request))
                .build();
    }

    @GetMapping
    public ApiResponse<List<Role>> getAllRoles() {
        return ApiResponse.<List<Role>>builder()
                .result(roleService.getAllRoles())
                .build();
    }
}
