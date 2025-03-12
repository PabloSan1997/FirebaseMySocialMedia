package com.mysocialmedia.firebase.service.security;


import com.mysocialmedia.firebase.service.security.filters.JwtValidationFilter;
import com.mysocialmedia.firebase.service.services.utils.InitialService;
import com.mysocialmedia.firebase.service.services.utils.JwtService;
import jakarta.annotation.PostConstruct;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
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
                .authorizeHttpRequests(getCustomizer()
                )
                .addFilter(new JwtValidationFilter(authenticationManager(), jwtService))
                .sessionManagement(s->s.sessionCreationPolicy(SessionCreationPolicy.STATELESS));
        return http.build();
    }


    @Bean
    CommandLineRunner commandLineRunner(InitialService initialService){
        return arg -> {
            initialService.generateInformation();
        };
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
                        "/api/image/user/{username}", "/api/image/user"
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
                .anyRequest().authenticated();
    }
}
