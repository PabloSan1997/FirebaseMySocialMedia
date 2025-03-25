package com.mysocialmedia.firebase.service.services.utils;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.mysocialmedia.firebase.service.models.dtos.SecurityDto;
import com.mysocialmedia.firebase.service.models.entities.Sessions;
import com.mysocialmedia.firebase.service.repositories.SessionRepository;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.io.Decoders;
import io.jsonwebtoken.security.Keys;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.stereotype.Service;

import javax.crypto.SecretKey;
import java.util.Collection;
import java.util.Date;
import java.util.List;

@Service
public class JwtService {
    @Autowired
    private SessionRepository sessionRepository;

    @Value("${jwt.secret.key}")
    private String secretKey;

    private final ObjectMapper objectMapper = new ObjectMapper();

    public String generateToken(SecurityDto securityDto) throws JsonProcessingException {
        List<String> authoritiesNames = securityDto.getAuthorities().stream()
                .map(GrantedAuthority::getAuthority).toList();

        String authoritiesJson = objectMapper.writeValueAsString(authoritiesNames);

        Claims claims = Jwts.claims()
                .add("authorities", authoritiesJson)
                .add("nickname", securityDto.getFullname()).build();
        return Jwts.builder().signWith(getKey())
                .claims(claims)
                .subject(securityDto.getUsername())
                .issuedAt(new Date())
                .expiration(new Date(System.currentTimeMillis()+1000*60*60*24))
                .compact();

    }

    public SecurityDto validateToken(String token) throws JsonProcessingException {
        Claims claims = Jwts.parser().verifyWith(getKey()).build()
                .parseSignedClaims(token).getPayload();
        String username = claims.getSubject();
        Sessions session = sessionRepository.findByUsernameAndToken(username, token)
                .orElseThrow(RuntimeException::new);
        if(!session.getState())
            throw new RuntimeException();

        String fullname = (String) claims.get("nickname");
        String authoritiesJson = (String) claims.get("authorities");
        List<String> authoritiesNames = List.of(
                objectMapper.readValue(authoritiesJson, String[].class)
        );
        Collection<? extends GrantedAuthority> authorities = authoritiesNames.stream()
                .map(SimpleGrantedAuthority::new).toList();
        SecurityDto securityDto = new SecurityDto();
        securityDto.setAuthorities(authorities);
        securityDto.setEnabled(true);
        securityDto.setFullname(fullname);
        securityDto.setUsername(username);
        return securityDto;
    }

    private SecretKey getKey(){
        return Keys.hmacShaKeyFor(Decoders.BASE64URL.decode(secretKey));
    }
}
