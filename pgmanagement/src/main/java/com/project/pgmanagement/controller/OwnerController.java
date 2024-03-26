package com.project.pgmanagement.controller;

import com.project.pgmanagement.model.Owner;
import com.project.pgmanagement.model.constant.Account;
import com.project.pgmanagement.service.OwnerService;
import jakarta.annotation.security.RolesAllowed;
import jakarta.validation.Valid;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("owner")
@Slf4j
public class OwnerController {

    private OwnerService ownerService;

    public OwnerController(OwnerService ownerService) {
        this.ownerService = ownerService;
    }


    @GetMapping("account/info")
    public ResponseEntity<Owner> getInfo(@RequestParam("mail") String mail) {
        return new ResponseEntity<>(ownerService.getOwnerInfoByName(mail) ,HttpStatus.OK);
    }

    @PostMapping("create/account")
    public ResponseEntity<String> createAccount(@Valid @RequestBody Owner owner) {
        return new ResponseEntity<>(ownerService.createAccount(owner) , HttpStatus.CREATED);
    }

    @DeleteMapping("account/delete")
    public String deleteAccount(@RequestParam("mail") String mail) {
        return ownerService.deleteMyAccount(mail);
    }


}
