package com.mysocialmedia.firebase.service.security;


import com.mysocialmedia.firebase.service.security.filters.JwtValidationFilter;
import com.mysocialmedia.firebase.service.services.utils.InitialService;
import com.mysocialmedia.firebase.service.services.utils.JwtService;
import jakarta.annotation.PostConstruct;
import jakarta.servlet.MultipartConfigElement;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.web.servlet.MultipartConfigFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configurers.AuthorizeHttpRequestsConfigurer;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.util.unit.DataSize;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;

import java.util.List;

@Configuration
public class SecurityConfig {

    @Autowired
    private AuthenticationConfiguration authenticationConfiguration;

    @Bean
    AuthenticationManager authenticationManager() throws Exception {
        return authenticationConfiguration.getAuthenticationManager();
    }

    @Bean
    PasswordEncoder passwordEncoder(){
        return new BCryptPasswordEncoder();
    }

    @Bean
    SecurityFilterChain securityFilterChain(HttpSecurity http, JwtService jwtService) throws Exception {
        http.csrf(c->c.disable())
                .authorizeHttpRequests(getCustomizer())
                .cors(c -> c.configurationSource(corsConfigurationSource()))
                .addFilter(new JwtValidationFilter(authenticationManager(), jwtService))
                .sessionManagement(s->s.sessionCreationPolicy(SessionCreationPolicy.STATELESS));
        return http.build();
    }

    @Bean
    UrlBasedCorsConfigurationSource corsConfigurationSource() {
        CorsConfiguration configuration = new CorsConfiguration();
        configuration.setAllowedHeaders(List.of("*"));
        configuration.setAllowedMethods(List.of("GET", "POST", "DELETE", "PATCH", "PUT"));
        configuration.setAllowedOriginPatterns(List.of("*"));
        configuration.setAllowCredentials(true);

        UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();
        source.registerCorsConfiguration("/**", configuration);
        return source;
    }

    @Bean
    CommandLineRunner commandLineRunner(InitialService initialService){
        return arg -> {
            initialService.generateInformation();
        };
    }

    @Bean
    public MultipartConfigElement multipartConfigElement() {
        MultipartConfigFactory factory = new MultipartConfigFactory();
        factory.setMaxFileSize(DataSize.ofMegabytes(10));
        factory.setMaxRequestSize(DataSize.ofMegabytes(10));
        return factory.createMultipartConfig();
    }

    private static Customizer<AuthorizeHttpRequestsConfigurer<HttpSecurity>.AuthorizationManagerRequestMatcherRegistry> getCustomizer() {
        return auth -> auth
                .requestMatchers(
                        HttpMethod.POST,
                        "/api/user/imageprofile",
                        "/api/user/profile",
                        "/api/friend/{username}",
                        "/api/image",
                        "/api/interaction/like/{idImage}",
                        "/api/interaction/comment/{idImage}"
                ).hasRole("USER")
                .requestMatchers(
                        HttpMethod.GET,
                        "/api/user/userinfo",
                        "/api/user/headeruser",
                        "/api/friend/mainfollows",
                        "/api/friend/followsfriend/{username}",
                        "/api/friend/followers/{username}",
                        "/api/friend/followings/{username}",
                        "/api/friend/viewfollow/{username}",
                        "/api/image", "/api/image/{id}",
                        "/api/image/user/{username}", "/api/image/user", "/api/image/following"
                ).hasRole("USER")
                .requestMatchers(
                        HttpMethod.DELETE,
                        "/api/image/{id}",
                        "/api/interaction/comment/{idComment}"
                ).hasRole("USER")
                .requestMatchers(
                        HttpMethod.POST,
                        "/api/user/login",
                        "/api/user/register"
                ).permitAll()
                .requestMatchers(
                        HttpMethod.GET,
                        "/", "index.html", "logo.svg", "/assets", "/assets/**"
                ).permitAll()
                .anyRequest().authenticated();
    }
}
