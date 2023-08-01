package com.Sk09Team.Patient.auth;


import com.Sk09Team.Patient.config.JwtService;
import com.Sk09Team.Patient.entity.Patient;
import com.Sk09Team.Patient.model.PatientRequest;
import com.Sk09Team.Patient.patientRepository.PatientRepository;
import com.Sk09Team.Patient.token.Token;
import com.Sk09Team.Patient.token.TokenRepository;
import com.Sk09Team.Patient.token.TokenType;
import com.fasterxml.jackson.databind.ObjectMapper;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpHeaders;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.io.IOException;

import static com.Sk09Team.Patient.entity.Role.PATIENT;


@Service
@RequiredArgsConstructor
public class AuthenticationService {
    private final PatientRepository repository;
    private final TokenRepository tokenRepository;
    private final PasswordEncoder passwordEncoder;
    private final JwtService jwtService;
    private final AuthenticationManager authenticationManager;

    public AuthenticationResponse register(PatientRequest request) {
        if (repository.existsByEmail(request.getEmail())) {
            throw new RuntimeException("Email already exists");
        }

            Patient patient = Patient.builder()
                    .firstName(request.getFirstName())
                    .lastName(request.getLastName())
//                    .gender(request.getGender())
                    .phone(request.getPhone())
//                    .birthDate(request.getBirthDate())
                    .CIN(request.getCin())
//                    .postCode(request.getPostCode())
                    .email(request.getEmail())
//                    .city(request.getCity())
//                    .address(request.getAddress())
                    .password(passwordEncoder.encode(request.getPassword()))
                    .role(PATIENT)
                    .build();

            repository.save(patient);


        var savedUser = repository.save(patient);
        var jwtToken = jwtService.generateToken(patient);
        var refreshToken = jwtService.generateRefreshToken(patient);
        saveUserToken(savedUser, jwtToken);
        return AuthenticationResponse.builder()
                .accessToken(jwtToken)
                .refreshToken(refreshToken)
                .build();
    }

    public AuthenticationResponse authenticate(AuthenticationRequest request) {
        authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(
                        request.getEmail(),
                        request.getPassword()
                )
        );
        var user = repository.findByEmail(request.getEmail())
                .orElseThrow();
        var jwtToken = jwtService.generateToken((UserDetails) user);
        var refreshToken = jwtService.generateRefreshToken((UserDetails) user);
        revokeAllUserTokens((Patient) user);
        saveUserToken((Patient) user, jwtToken);
        return AuthenticationResponse.builder()
                .accessToken(jwtToken)
                .refreshToken(refreshToken)
                .build();
    }

    private void saveUserToken(Patient user, String jwtToken) {
        var token = Token.builder()
                .user(user)
                .token(jwtToken)
                .tokenType(TokenType.BEARER)
                .expired(false)
                .revoked(false)
                .build();
        tokenRepository.save(token);
    }

    private void revokeAllUserTokens(Patient user) {
        var validUserTokens = tokenRepository.findAllValidTokenByUser((long) Math.toIntExact(user.getPatientId()));
        if (validUserTokens.isEmpty())
            return;
        validUserTokens.forEach(token -> {
            token.setExpired(true);
            token.setRevoked(true);
        });
        tokenRepository.saveAll(validUserTokens);
    }

    public void refreshToken(
            HttpServletRequest request,
            HttpServletResponse response
    ) throws IOException {
        final String authHeader = request.getHeader(HttpHeaders.AUTHORIZATION);
        final String refreshToken;
        final String userEmail;
        if (authHeader == null ||!authHeader.startsWith("Bearer ")) {
            return;
        }
        refreshToken = authHeader.substring(7);
        userEmail = jwtService.extractUsername(refreshToken);
        if (userEmail != null) {
            var user = this.repository.findByEmail(userEmail)
                    .orElseThrow();
            if (jwtService.isTokenValid(refreshToken, (UserDetails) user)) {
                var accessToken = jwtService.generateToken((UserDetails) user);
                revokeAllUserTokens((Patient) user);
                saveUserToken((Patient) user, accessToken);
                var authResponse = AuthenticationResponse.builder()
                        .accessToken(accessToken)
                        .refreshToken(refreshToken)
                        .build();
                new ObjectMapper().writeValue(response.getOutputStream(), authResponse);
            }
        }
    }
}
