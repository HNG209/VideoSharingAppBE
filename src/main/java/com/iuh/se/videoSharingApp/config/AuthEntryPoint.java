package com.iuh.se.videoSharingApp.config;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.iuh.se.videoSharingApp.exception.ErrorCode;
import com.iuh.se.videoSharingApp.util.ApiResponse;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.http.MediaType;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.AuthenticationEntryPoint;

import java.io.IOException;

public class AuthEntryPoint implements AuthenticationEntryPoint {
    @Override
    public void commence(HttpServletRequest request, HttpServletResponse response, AuthenticationException authException) throws IOException, ServletException {
        ErrorCode code = ErrorCode.UNAUTHENTICATED;

        response.setStatus(code.getStatusCode().value());
        response.setContentType(MediaType.APPLICATION_JSON_VALUE);

        ApiResponse<Object> r = ApiResponse.builder()
                .code(code.getCode())
                .message(code.getMessage())
                .build();

        response.getWriter().write(new ObjectMapper().writeValueAsString(r));
        response.flushBuffer();
    }
}
