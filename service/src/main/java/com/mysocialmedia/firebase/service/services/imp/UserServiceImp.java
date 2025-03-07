package com.mysocialmedia.firebase.service.services.imp;

import com.mysocialmedia.firebase.service.exceptions.MyBadRequestException;
import com.mysocialmedia.firebase.service.models.dtos.LoginDto;
import com.mysocialmedia.firebase.service.models.dtos.RegisterDto;
import com.mysocialmedia.firebase.service.models.dtos.SecurityDto;
import com.mysocialmedia.firebase.service.models.dtos.TokenDto;
import com.mysocialmedia.firebase.service.models.entities.RoleEntity;
import com.mysocialmedia.firebase.service.models.entities.Sessions;
import com.mysocialmedia.firebase.service.models.entities.Users;
import com.mysocialmedia.firebase.service.repositories.RoleRespository;
import com.mysocialmedia.firebase.service.repositories.SessionRepository;
import com.mysocialmedia.firebase.service.repositories.UserRepository;
import com.mysocialmedia.firebase.service.services.UserService;
import com.mysocialmedia.firebase.service.services.utils.JwtService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class UserServiceImp implements UserService {
    @Autowired
    private UserRepository userRepository;
    @Autowired
    private RoleRespository roleRespository;
    @Autowired
    private PasswordEncoder passwordEncoder;
    @Autowired
    private AuthenticationManager authenticationManager;
    @Autowired
    private JwtService jwtService;
    @Autowired
    private SessionRepository sessionRepository;

    @Override
    @Transactional
    public TokenDto login(LoginDto loginDto) {
        Authentication authenticationToken = new UsernamePasswordAuthenticationToken(
                loginDto.getUsername(),
                loginDto.getPassword()
        );
        try{
            Authentication authentication = authenticationManager.authenticate(authenticationToken);
            SecurityDto securityDto = (SecurityDto) authentication.getPrincipal();
            Users user = userRepository.findByUsername(securityDto.getUsername())
                    .orElseThrow();
            String token = jwtService.generateToken(securityDto);
            Sessions sessions = Sessions.builder()
                    .user(user).token(token).build();
            sessionRepository.save(sessions);
            return TokenDto.builder().token(token).username(user.getUsername()).build();
        }catch (Exception e){
            throw  new MyBadRequestException("Username o password incorrectos");
        }
    }

    @Override
    @Transactional
    public TokenDto register(RegisterDto registerDto) {
        Optional<Users> oUser = userRepository.findByUsername(registerDto.getUsername());
        if (oUser.isPresent())
            throw new MyBadRequestException("Username ocupado");

        String password = passwordEncoder.encode(registerDto.getPassword());
        List<RoleEntity> roles = new ArrayList<>();
        RoleEntity role = roleRespository.findByName("USER")
                .orElseThrow(()-> new RuntimeException("No existe rol"));
        roles.add(role);
        Users users = Users.builder()
                .comments(new ArrayList<>())
                .imagenes(new ArrayList<>())
                .roles(roles)
                .username(registerDto.getUsername())
                .fullname(registerDto.getFullname())
                .likes(new ArrayList<>())
                .password(password).build();
        Users newUser = userRepository.save(users);

        return login(
                LoginDto.builder().username(newUser.getUsername()).password(registerDto.getPassword()).build()
        );
    }
}
