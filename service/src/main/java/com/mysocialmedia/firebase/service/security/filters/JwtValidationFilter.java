package com.mysocialmedia.firebase.service.security.filters;

import com.mysocialmedia.firebase.service.models.dtos.SecurityDto;
import com.mysocialmedia.firebase.service.services.utils.JwtService;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.authentication.www.BasicAuthenticationFilter;

import java.io.IOException;

public class JwtValidationFilter extends BasicAuthenticationFilter {

    private JwtService jwtService;

    public JwtValidationFilter(AuthenticationManager authenticationManager, JwtService jwtService) {
        super(authenticationManager);
        this.jwtService = jwtService;
    }

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain chain) throws IOException, ServletException {
        String header = request.getHeader("Authorization");
        if(header == null || !header.startsWith("Bearer ")){
            chain.doFilter(request, response);
            return;
        }

        String token = header.replace("Bearer ", "");

        try{
            SecurityDto securityDto = jwtService.validateToken(token);

            Authentication authenticationToken = new UsernamePasswordAuthenticationToken(
                    securityDto.getUsername(),
                    null,
                    securityDto.getAuthorities()
            );
            SecurityContextHolder.getContext().setAuthentication(authenticationToken);
            chain.doFilter(request, response);
        }catch (Exception e){
            chain.doFilter(request, response);
        }
    }
}
