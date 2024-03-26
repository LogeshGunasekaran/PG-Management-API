package com.project.pgmanagement.security.accountconfig;

import com.project.pgmanagement.dao.AccountRepository;
import com.project.pgmanagement.dao.OwnerRepository;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
public class AccountManager implements UserDetailsService {

    private AccountRepository accountRepository;

    public AccountManager(AccountRepository accountRepository) {
        this.accountRepository = accountRepository;
    }

    @Override
        public UserDetails loadUserByUsername (String username) throws UsernameNotFoundException {
            return accountRepository.findByUsername(username)
                    .map(user -> new AccountDetails(user))
                    .orElseThrow(() -> new RuntimeException("usernot found---->"+username));
        }
    }