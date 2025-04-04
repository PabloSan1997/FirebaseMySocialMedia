package com.mysocialmedia.firebase.service.models.dtos;

import com.mysocialmedia.firebase.service.models.entities.RoleEntity;
import lombok.Getter;
import lombok.Setter;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Collection;
import java.util.List;

@Setter
public class SecurityDto implements UserDetails {
    private String username;
    @Getter
    private String fullname;
    private String password;
    private Boolean enabled;
    private  Collection<? extends GrantedAuthority> authorities;

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return authorities;
    }

    @Override
    public String getPassword() {
        return password;
    }

    @Override
    public String getUsername() {
        return username;
    }

    @Override
    public boolean isEnabled() {
        return enabled;
    }

    public void setAuthoritiesAsRoles(List<RoleEntity> roles){
        authorities = roles.stream()
                .map(p -> new SimpleGrantedAuthority("ROLE_"+p.getName()))
                .toList();
    }
}
