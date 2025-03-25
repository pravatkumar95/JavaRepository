package com.pks.config;

import com.pks.model.User;
import com.pks.model.UserRole;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;

public class UserDetailsModel implements UserDetails {

    private String name;
    private String password;
    private List<GrantedAuthority> authorities;
    public UserDetailsModel(User user){
        this.name=user.getName();
        this.password=user.getPassword();
        List<UserRole> roles=user.getRoles().stream().map(r->r.getRole()).collect(Collectors.toList());
        List<String> userRole=roles.stream().map(r->"ROLE_"+r.name()).collect(Collectors.toList());
       this.authorities=userRole.stream().map(SimpleGrantedAuthority::new).collect(Collectors.toList());
        //this.authorities=userRole.stream().map(role->new SimpleGrantedAuthority(String.valueOf(role))).collect(Collectors.toList());


    }
    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return this.authorities;
    }

    @Override
    public String getPassword() {
        return this.password;
    }

    @Override
    public String getUsername() {
        return this.name;
    }

    @Override
    public boolean isAccountNonExpired() {
        return false;
    }

    @Override
    public boolean isAccountNonLocked() {
        return false;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return false;
    }

    @Override
    public boolean isEnabled() {
        return false;
    }
}
