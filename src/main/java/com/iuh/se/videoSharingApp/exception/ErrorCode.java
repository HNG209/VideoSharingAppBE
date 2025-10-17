package com.iuh.se.videoSharingApp.exception;

import lombok.AllArgsConstructor;
import lombok.Getter;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;

@Getter
@AllArgsConstructor
public enum ErrorCode {
    UNCATEGORIZED(9999, "uncategorized exception", HttpStatus.INTERNAL_SERVER_ERROR),
    EMAIL_EXISTED(1001, "email existed", HttpStatus.BAD_REQUEST),
    EMAIL_NOT_FOUND(1002, "email not found", HttpStatus.NOT_FOUND),
    ROLE_NOT_FOUND(2002, "role not found", HttpStatus.NOT_FOUND),
    FIELD_BLANK(2003, "Field cannot be blank", HttpStatus.BAD_REQUEST),
    EMAIL_INVALID(3000, "invalid email", HttpStatus.BAD_REQUEST),
    PASSWORD_INVALID(3001, "invalid password", HttpStatus.UNAUTHORIZED),
    USERNAME_INVALID(3002, "invalid password", HttpStatus.BAD_REQUEST),
    KEY_INVALID(3003, "invalid enum key", HttpStatus.INTERNAL_SERVER_ERROR),
    BODY_NOT_SPECIFIED(3004, "body not specified", HttpStatus.BAD_REQUEST),
    UNAUTHENTICATED(4004, "unauthenticated", HttpStatus.UNAUTHORIZED),
    UNAUTHORIZED(4003, "access denied", HttpStatus.FORBIDDEN),
    INVALID_DOB(3003, "invalid date of birth", HttpStatus.BAD_REQUEST)
    ;

    private final int code;
    private final String message;
    private final HttpStatusCode statusCode;
}