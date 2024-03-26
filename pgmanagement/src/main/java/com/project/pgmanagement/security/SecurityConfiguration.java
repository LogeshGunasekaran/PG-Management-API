package com.project.pgmanagement.security;

import com.project.pgmanagement.model.Tenant;
import com.project.pgmanagement.model.constant.Authority;
import com.project.pgmanagement.security.accountconfig.AccountManager;
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.ProviderManager;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.core.Authentication;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;

import java.util.List;

@Configuration
@EnableWebSecurity
public class SecurityConfiguration  {

    private AccountManager accountManager;

    public SecurityConfiguration(com.project.pgmanagement.security.accountconfig.AccountManager accountManager) {
        this.accountManager = accountManager;
    }

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity httpSecurity) throws Exception {

        return httpSecurity.csrf(csrfConfig -> csrfConfig.disable())
                .authorizeHttpRequests(auth -> {
                        auth.requestMatchers("tenant/create/account","owner/create/account").permitAll();
                        auth.requestMatchers("owner/**","admin/**").hasAuthority(Authority.ADMIN.name()).requestMatchers("owner/**","admin/**").permitAll();
                        auth.requestMatchers("tenant/**").hasAnyAuthority(Authority.ADMIN.name(),Authority.USER.name()).requestMatchers("tenant/**").permitAll();
                })
                .userDetailsService(accountManager)
                .sessionManagement(sessionConfig -> sessionConfig.sessionCreationPolicy(SessionCreationPolicy.STATELESS))
                .httpBasic(Customizer.withDefaults())
                .build();
    }

    @Bean
    @ConditionalOnMissingBean
    public BCryptPasswordEncoder encoder() {
        return new BCryptPasswordEncoder();
    }

    @Bean
    @ConditionalOnMissingBean
    public Tenant tenantBean()
    {
        return  new Tenant();
    }








}
