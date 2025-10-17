package com.iuh.se.videoSharingApp.entity;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Data
@Document(collection = "users")
@NoArgsConstructor
@Builder
@AllArgsConstructor
public class User {
    @Id
    private String id;

    private String username;

    private String email;

    private String password;
}
