package com.iuh.se.videoSharingApp.config;

import com.iuh.se.videoSharingApp.service.AuthService;
import com.iuh.se.videoSharingApp.util.JwtSecretReader;
import com.nimbusds.jose.JOSEException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.oauth2.jose.jws.MacAlgorithm;
import org.springframework.security.oauth2.jwt.Jwt;
import org.springframework.security.oauth2.jwt.JwtDecoder;
import org.springframework.security.oauth2.jwt.JwtException;
import org.springframework.security.oauth2.jwt.NimbusJwtDecoder;
import org.springframework.stereotype.Component;

import javax.crypto.spec.SecretKeySpec;
import java.text.ParseException;

@Component
public class CustomJwtDecoder implements JwtDecoder {
    private final AuthService authService;
    private final NimbusJwtDecoder nimbusJwtDecoder;

    @Autowired
    public CustomJwtDecoder(JwtSecretReader reader, AuthService authService) {
        SecretKeySpec secretKeySpec = new SecretKeySpec(reader.getSecret().getBytes(), "HS256");
        nimbusJwtDecoder = NimbusJwtDecoder
                .withSecretKey(secretKeySpec)
                .macAlgorithm(MacAlgorithm.HS256)
                .build();
        this.authService = authService;
    }

    @Override
    public Jwt decode(String token) throws JwtException {
        try {
            authService.verify(token);
        } catch (ParseException | JOSEException e) {
            throw new RuntimeException(e);
        }

        return nimbusJwtDecoder.decode(token);
    }
}
