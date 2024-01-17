package Avocado.main_project.Login;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;

import Avocado.main_project.Entities.user.Users;

import java.util.Collection;
import java.util.Collections;


public class CustomUserDetails implements UserDetails {

    private Users user;

    public CustomUserDetails(Users user) {
        super();
        this.user = user;
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return Collections.singleton(new SimpleGrantedAuthority(user.getRole()));
    }

    @Override
    public String getPassword() {
        return user.getPassword();
    }

    @Override
    public String getUsername() {
        return user.getUsername();
    }
    
    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return true;
    }
    
    public String getName() {
        return user.getName();
    }
    
    public String getEmail() {
        return user.getEmail();
    }
    
    public String getRole() {
        return user.getRole();
    }

    public Long getId() {return user.getId();}
}
