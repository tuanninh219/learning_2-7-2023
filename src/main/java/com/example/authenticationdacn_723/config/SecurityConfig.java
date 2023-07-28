package com.example.authenticationdacn_723.config;

import com.example.authenticationdacn_723.repository.UserRepository;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpStatus;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.AuthenticationEntryPoint;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.access.AccessDeniedHandler;

import java.util.Map;

/*
Create By : ANHTUAN
*/
@Configuration
@EnableWebSecurity
public class SecurityConfig {

    private UserRepository userRepository;
    private Authentication authentication;
    @Autowired
    private CustomAuthenticationSuccessHandler customAuthenticationSuccessHandler;

    @Autowired
    ObjectMapper objectMapper;

    //authentication
    @Bean
    public UserDetailsService userDetailsService() {
        // memory users have info email, password, roles to authorize (SecurityContextHolder)
        return new UserDetailService_Security(userRepository);
    }

    @Autowired
    public SecurityConfig(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {

        http.
                csrf(csrf -> csrf.disable())
                .authorizeHttpRequests(authorize ->
                        authorize
                                .requestMatchers("/roles/save", "/users/register","/users/login").permitAll()
                                .requestMatchers("/users/{id}").hasAuthority("ROLE_USER")
                                .requestMatchers("/admin/**").hasAuthority("ROLE_ADMIN")

                                .anyRequest().authenticated()

                )
                .formLogin(Customizer.withDefaults());

        // Handel's error 403 and Return a text type json
        http.exceptionHandling(exceptionHandling ->
                exceptionHandling
                        .accessDeniedHandler(accessDeniedHandler())
//                        .authenticationEntryPoint(authenticationEntryPoint())
        );

//        http.formLogin(form -> form
//                .loginPage("/users/login") // use my form login (GET)
//                .permitAll()
//        );
        authentication = SecurityContextHolder.getContext().getAuthentication();

//        http.formLogin(form -> form
//                .loginProcessingUrl("/admin/login-auth") // the method to handle credentials (POST)
//                .successHandler(customAuthenticationSuccessHandler)
//                .permitAll()
//        );

//        http.formLogin(form -> form
//                .loginProcessingUrl("/users/login-auth")
//                .defaultSuccessUrl("/users/{id}")
//                .permitAll()
//        );
        return http.build();
    }

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    @Bean
    public AccessDeniedHandler accessDeniedHandler() {
        return (request, response, accessDeniedException) -> {
            response.setStatus(HttpStatus.FORBIDDEN.value());
            response.setContentType("application/json");
            response.getWriter().write(objectMapper.writeValueAsString(Map.of("error403", "YOU DON'T AUTHORIZE")));
        };
    }
    @Bean
    public AuthenticationEntryPoint authenticationEntryPoint() {
        return (request, response, accessDeniedException) -> {
            response.setStatus(HttpStatus.FORBIDDEN.value());
            response.setContentType("application/json");
            response.getWriter().write(objectMapper.writeValueAsString(Map.of("error401", "you unauthorized")));
        };
    }

    // handel successfully
}
