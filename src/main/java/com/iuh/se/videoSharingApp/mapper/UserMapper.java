package com.iuh.se.videoSharingApp.mapper;

import com.iuh.se.videoSharingApp.dto.response.UserResponse;
import com.iuh.se.videoSharingApp.entity.User;
import org.mapstruct.Builder;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring", builder = @Builder(disableBuilder = true))
public interface UserMapper {
    UserResponse toUserResponse(User user);
}
