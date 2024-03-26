package com.project.pgmanagement.controller;

import com.project.pgmanagement.model.Tenant;
import com.project.pgmanagement.model.constant.Account;
import com.project.pgmanagement.service.TenantService;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("tenant")
public class TenantController {

    private TenantService tenantService;

    public TenantController(TenantService tenantService) {
        this.tenantService = tenantService;
    }



    @PostMapping("create/account")
    public String createAccount(@Valid @RequestBody Tenant tenant, @RequestParam("roomno") Integer roomno) {
        return tenantService.createAccount(tenant, roomno);
    }

    @GetMapping("account/info")
    public String getMyAccount(@RequestParam("mail") String mail) {
        return tenantService.getMyAccountDetail(mail);
    }

    @DeleteMapping("account/delete")
    public String deleteAccount(@RequestParam("mail") String mail)
    {
        return tenantService.deleteMyAccount(mail);
    }
}
