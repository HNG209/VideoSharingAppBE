package com.iuh.se.videoSharingApp.dto.request;

import lombok.*;
import lombok.experimental.FieldDefaults;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@FieldDefaults(level = AccessLevel.PRIVATE)
public class PermissionAssignmentRequest { // add permissions to role
    List<String> permissionNames;
}
