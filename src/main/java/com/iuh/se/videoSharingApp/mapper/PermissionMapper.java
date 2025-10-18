package com.iuh.se.videoSharingApp.mapper;

import com.iuh.se.videoSharingApp.dto.request.PermissionCreationRequest;
import com.iuh.se.videoSharingApp.dto.response.PermissionResponse;
import com.iuh.se.videoSharingApp.entity.Permission;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface PermissionMapper {
    Permission toPermission(PermissionCreationRequest request);
    PermissionResponse toPermissionResponse(Permission permission);
}
