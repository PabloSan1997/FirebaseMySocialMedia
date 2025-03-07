package com.mysocialmedia.firebase.service.services.imp;

import com.mysocialmedia.firebase.service.models.dtos.SecurityDto;
import com.mysocialmedia.firebase.service.models.entities.Users;
import com.mysocialmedia.firebase.service.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class UserServiceDetailsImp implements UserDetailsService {

    @Autowired
    private UserRepository userRepository;

    @Override
    @Transactional
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        Users user = userRepository.findByUsername(username)
                .orElseThrow(()-> new UsernameNotFoundException(""));
        SecurityDto securityDto = new SecurityDto();

        securityDto.setUsername(username);
        securityDto.setFullname(user.getFullname());
        securityDto.setEnabled(user.getEnabled());
        securityDto.setPassword(user.getPassword());
        securityDto.setAuthoritiesAsRoles(user.getRoles());

        return securityDto;
    }
}
