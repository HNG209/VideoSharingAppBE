package com.iuh.se.videoSharingApp.service;

import com.iuh.se.videoSharingApp.dto.response.AuthResponse;
import com.iuh.se.videoSharingApp.dto.response.UserResponse;
import com.nimbusds.jose.JOSEException;
import com.nimbusds.jwt.SignedJWT;

import javax.security.auth.login.CredentialExpiredException;
import java.text.ParseException;

public interface AuthService {
    SignedJWT verify(String token) throws JOSEException, ParseException;
    AuthResponse login(String email, String password) throws JOSEException;
}
