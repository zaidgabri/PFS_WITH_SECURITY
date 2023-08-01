package com.Sk09Team.Doctor.config;

import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.security.web.authentication.logout.LogoutHandler;

import static com.Sk09Team.Doctor.entity.Permission.*;
import static com.Sk09Team.Doctor.entity.Role.DOCTOR;
import static org.springframework.http.HttpMethod.*;

@Configuration
@EnableWebSecurity
@RequiredArgsConstructor
@EnableMethodSecurity
public class SecurityConfiguration {

    private final JwtAuthenticationFilter jwtAuthFilter;
    private final AuthenticationProvider authenticationProvider;
    private final LogoutHandler logoutHandler;

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        http
                .csrf()
                .disable()
                .authorizeHttpRequests()
                .requestMatchers(
                        "/api/doctor/auth/**",
                        "/DoctorPatientConsultation/**",
                        "/PatientDoctorConsultation/**"



                )
                .permitAll()


                .requestMatchers("/doctor/**").hasAnyRole(DOCTOR.name())


                .requestMatchers(GET, "/doctor/**").hasAnyAuthority(DOCTOR_READ.name())
                .requestMatchers(POST, "/doctor/**").hasAnyAuthority(DOCTOR_CREATE.name())
                .requestMatchers(PUT, "/doctor/**").hasAnyAuthority(DOCTOR_UPDATE.name())
                .requestMatchers(DELETE, "/doctor/**").hasAnyAuthority(DOCTOR_DELETE.name())




                .anyRequest()
                .authenticated()
                .and()
                .sessionManagement()
                .sessionCreationPolicy(SessionCreationPolicy.STATELESS)
                .and()
                .authenticationProvider(authenticationProvider)
                .addFilterBefore(jwtAuthFilter, UsernamePasswordAuthenticationFilter.class)
                .logout()
                .logoutUrl("/api/v1/auth/logout")
                .addLogoutHandler(logoutHandler)
                .logoutSuccessHandler((request, response, authentication) -> SecurityContextHolder.clearContext())
        ;
        http.cors();

        return http.build();
    }



}
