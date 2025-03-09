package com.mysocialmedia.firebase.service.services.imp;

import com.mysocialmedia.firebase.service.exceptions.MyBadRequestException;
import com.mysocialmedia.firebase.service.models.dtos.*;
import com.mysocialmedia.firebase.service.models.entities.RoleEntity;
import com.mysocialmedia.firebase.service.models.entities.Sessions;
import com.mysocialmedia.firebase.service.models.entities.UserInfo;
import com.mysocialmedia.firebase.service.models.entities.Users;
import com.mysocialmedia.firebase.service.repositories.*;
import com.mysocialmedia.firebase.service.services.UserService;
import com.mysocialmedia.firebase.service.services.utils.JwtService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.HttpRequestHandler;
import org.springframework.web.multipart.MultipartFile;

import java.util.ArrayList;
import java.util.Date;
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
    @Autowired
    private UserInfoRepository userInfoRepository;
    @Autowired
    private FirebaseRepository firebaseRepository;

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
        UserInfo userInfo = UserInfo.builder().user(newUser).born(new Date())
                .description("").imageFileName("").description("").urlImage("").build();
        userInfoRepository.save(userInfo);
        return login(
                LoginDto.builder().username(newUser.getUsername()).password(registerDto.getPassword()).build()
        );
    }

    @Override
    @Transactional
    public ShowFullUserDto viewUserInfo() {
        Users user = getAuthenticationUser();
        UserInfo userInfo = user.getUserInfo();
        return new ShowFullUserDto(user, userInfo);
    }

    @Override
    @Transactional
    public ShowFullUserDto updateUserInfo(UpdateUserInfoDto userInfoDto) {
        Users user = getAuthenticationUser();
        UserInfo userInfo = user.getUserInfo();
        userInfo.setBorn(userInfoDto.getBorn());
        userInfo.setDescription(userInfoDto.getDescription());
        UserInfo newUserinfo = userInfoRepository.save(userInfo);
        return new ShowFullUserDto(user, newUserinfo);
    }

    @Override
    @Transactional
    public FirebaseDto updateProfilePicture(MultipartFile multipartFile) {
        Users user = getAuthenticationUser();
        UserInfo userInfo = user.getUserInfo();
        try{
            FirebaseDto firebaseDto = firebaseRepository.save(multipartFile);
            firebaseRepository.deleteImage(userInfo.getImageFileName());
            userInfo.setUrlImage(firebaseDto.getUrlImage());
            userInfo.setImageFileName(firebaseDto.getFilename());
            userInfoRepository.save(userInfo);
            return firebaseDto;
        }catch (Exception e){
            throw new MyBadRequestException(e.getMessage());
        }
    }


    @Override
    @Transactional
    public OnlyTitleUserDto viewUserHeader() {
        Users user = getAuthenticationUser();
        UserInfo userInfo = user.getUserInfo();
        return OnlyTitleUserDto.builder()
                .username(user.getUsername())
                .fullname(user.getFullname())
                .urlImage(userInfo.getUrlImage()).build();
    }

    @Override
    @Transactional
    public void logout(String token) {
        Users user = getAuthenticationUser();
        Sessions session = sessionRepository
                .findByUsernameAndToken(user.getUsername(), token)
                .orElseThrow(()-> new RuntimeException("Error con la authenticacion"));
        session.setState(false);
        sessionRepository.save(session);
    }

    Users getAuthenticationUser(){
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String username = (String) authentication.getPrincipal();
        return userRepository.findByUsername(username)
                .orElseThrow(()-> new RuntimeException("No existe el usuario autenticado"));
    }
}
