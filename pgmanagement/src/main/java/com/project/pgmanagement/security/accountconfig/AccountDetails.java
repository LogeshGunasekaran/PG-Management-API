package com.project.pgmanagement.security.accountconfig;

import com.project.pgmanagement.model.Owner;
import com.project.pgmanagement.model.constant.Account;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Collection;
import java.util.List;


public class AccountDetails implements UserDetails {

    private Account ownerCredential;

    public AccountDetails(Account ownerCredential) {
        this.ownerCredential = ownerCredential;
    }

    public Collection<? extends GrantedAuthority> getAuthorities()
    {
        return List.of(new SimpleGrantedAuthority(ownerCredential.getAuthority().toString()));
    }

    @Override
    public String getPassword() {
        return ownerCredential.getPassword();
    }

    @Override
    public String getUsername() {
        return ownerCredential.getUsername();
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
}