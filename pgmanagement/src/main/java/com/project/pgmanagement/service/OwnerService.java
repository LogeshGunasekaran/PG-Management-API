package com.project.pgmanagement.service;

import com.project.pgmanagement.dao.AccountRepository;
import com.project.pgmanagement.dao.OwnerRepository;
import com.project.pgmanagement.exception.AccountDeleteException;
import com.project.pgmanagement.exception.AdminCreationFailedException;
import com.project.pgmanagement.exception.CredentialCreationException;
import com.project.pgmanagement.exception.UserNotFoundException;
import com.project.pgmanagement.model.Owner;
import com.project.pgmanagement.model.constant.Account;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.util.Assert;

import javax.security.auth.login.CredentialException;
import java.util.Optional;

@Service
@Slf4j
public class OwnerService {

    private OwnerRepository ownerRepository;
    private AccountRepository accountRepository;
    private BCryptPasswordEncoder passwordEncoder;

    public OwnerService(OwnerRepository ownerRepository, AccountRepository accountRepository, BCryptPasswordEncoder passwordEncoder) {
        this.ownerRepository = ownerRepository;
        this.accountRepository = accountRepository;
        this.passwordEncoder = passwordEncoder;
    }

    public String createAccount(Owner owner) {
        Account account = owner.getAccount();
        account.setPassword(passwordEncoder.encode(account.getPassword()));
        owner.setAccount(account);

        Owner savedOwner = Optional.of(ownerRepository.save(owner))
                .orElseThrow(() -> new AdminCreationFailedException("Owner", "Failed to create an Account"));
        log.debug("Account {}",savedOwner.toString());
        return savedOwner!=null? "Admin "+account.getUsername()+" created" : "Failed to create an Account "+account.getUsername();
    }

    public String deleteMyAccount(String mail) {
        if (ownerRepository.deleteByMail(mail))
            return "Your Account deleted ";
        else
            throw new AccountDeleteException(mail, "Deletion Failed");

    }

    public Owner getOwnerInfoByName(String mail) {
        return ownerRepository.findByMail(mail).orElseThrow(() -> new UserNotFoundException(mail, "Not Found"));
    }
}
