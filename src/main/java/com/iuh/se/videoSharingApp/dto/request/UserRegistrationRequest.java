package com.iuh.se.videoSharingApp.dto.request;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.*;
import lombok.experimental.FieldDefaults;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@FieldDefaults(level = AccessLevel.PRIVATE)
public class UserRegistrationRequest {
    @NotBlank(message = "FIELD_BLANK")
    @Size(min = 3, max = 20, message = "USERNAME_INVALID")
    private String username;

    @NotBlank(message = "FIELD_BLANK")
    @Email(message = "EMAIL_INVALID")
    private String email;

    @NotBlank(message = "FIELD_BLANK")
    @Size(min = 6, message = "PASSWORD_INVALID")
    private String password;
}
