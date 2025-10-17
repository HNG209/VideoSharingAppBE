package com.iuh.se.videoSharingApp.service.impl;

import com.iuh.se.videoSharingApp.dto.response.AuthResponse;
import com.iuh.se.videoSharingApp.dto.response.UserResponse;
import com.iuh.se.videoSharingApp.entity.User;
import com.iuh.se.videoSharingApp.exception.AppException;
import com.iuh.se.videoSharingApp.exception.ErrorCode;
import com.iuh.se.videoSharingApp.repository.UserRepository;
import com.iuh.se.videoSharingApp.service.AuthService;
import com.iuh.se.videoSharingApp.util.JwtSecretReader;
import com.nimbusds.jose.*;
import com.nimbusds.jose.crypto.MACSigner;
import com.nimbusds.jose.crypto.MACVerifier;
import com.nimbusds.jwt.JWTClaimsSet;
import com.nimbusds.jwt.SignedJWT;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.CredentialsExpiredException;
import org.springframework.security.crypto.bcrypt.BCrypt;
import org.springframework.stereotype.Service;

import javax.security.auth.login.CredentialExpiredException;
import java.nio.charset.StandardCharsets;
import java.text.ParseException;
import java.time.Instant;
import java.time.temporal.ChronoUnit;
import java.util.Date;
import java.util.Optional;
import java.util.UUID;

@Service
public class AuthServiceImpl implements AuthService {
    private final UserRepository userRepository;
    private final String SECRET_KEY;

    @Autowired
    public AuthServiceImpl(JwtSecretReader reader, UserRepository userRepository) {
        SECRET_KEY = reader.getSecret();
        this.userRepository = userRepository;
    }

    @Override
    public SignedJWT verify(String token) throws JOSEException, ParseException {
        JWSVerifier verifier = new MACVerifier(SECRET_KEY.getBytes());
        SignedJWT signedJWT = SignedJWT.parse(token);
        Date exp = signedJWT.getJWTClaimsSet().getExpirationTime();
        boolean expired = exp.before(new Date());

        if(expired)
            throw new CredentialsExpiredException("Session expired");

        boolean verified = signedJWT.verify(verifier);

        if(!verified)
            throw new BadCredentialsException("Bad token");

        return signedJWT;
    }

    public String generateToken(User user) throws JOSEException {
        JWSHeader header = new JWSHeader(JWSAlgorithm.HS256);

        JWTClaimsSet jwtClaimsSet = new JWTClaimsSet.Builder()
                .subject(user.getUsername())
                .issuer("hng209")
                .issueTime(new Date())
//                .claim("scope", buildScope(user))
                .jwtID(UUID.randomUUID().toString())
                .expirationTime(new Date(
                        Instant.now().plus(1, ChronoUnit.HOURS).toEpochMilli()
                ))
                .build();
        Payload payload = new Payload(jwtClaimsSet.toJSONObject());
        JWSObject jwsObject = new JWSObject(header, payload);
        jwsObject.sign(new MACSigner(SECRET_KEY.getBytes(StandardCharsets.UTF_8)));

        return jwsObject.serialize();
    }

    @Override
    public AuthResponse login(String email, String password) throws JOSEException {
        Optional<User> userOpt = userRepository.findByEmail(email);
        if (userOpt.isEmpty())
            throw new AppException(ErrorCode.EMAIL_NOT_FOUND);

        if (!BCrypt.checkpw(password, userOpt.get().getPassword())) {
            throw new AppException(ErrorCode.PASSWORD_INVALID);
        }

        String token = generateToken(userOpt.get());

        return AuthResponse.builder()
                .token(token)
                .build();
    }

//    private String buildScope(User user) {
//        StringJoiner joiner = new StringJoiner(" ");
//        if(!user.getRoles().isEmpty())
//            user.getRoles().forEach(role -> {
//                        joiner.add("ROLE_" + role.getName());
//
//                        role.getPermissions()
//                                .stream()
//                                .map(Permission::getName)
//                                .forEach(joiner::add);
//                    }
//            );
//
//        return joiner.toString();
//    }
}
