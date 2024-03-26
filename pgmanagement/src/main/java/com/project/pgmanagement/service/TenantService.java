package com.project.pgmanagement.service;

import com.project.pgmanagement.dao.AccountRepository;
import com.project.pgmanagement.dao.RoomRepositoy;
import com.project.pgmanagement.dao.TenantRepository;
import com.project.pgmanagement.exception.*;
import com.project.pgmanagement.model.Room;
import com.project.pgmanagement.model.Tenant;
import com.project.pgmanagement.model.constant.Account;
import jakarta.validation.Valid;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class TenantService {

    private TenantRepository tenantRepository;
    private RoomRepositoy roomRepositoy;
    private AccountRepository accountRepository;
    private BCryptPasswordEncoder passwordEncoder;

    public TenantService(TenantRepository tenantRepository, RoomRepositoy roomRepositoy, AccountRepository accountRepository, BCryptPasswordEncoder passwordEncoder) {
        this.tenantRepository = tenantRepository;
        this.roomRepositoy = roomRepositoy;
        this.accountRepository = accountRepository;
        this.passwordEncoder = passwordEncoder;
    }

    public String createAccount(Tenant tenant, Integer roomno) {

        Room room = roomRepositoy.findByroomno(roomno).orElse(null);
        if(room!=null){
            roomRepositoy.updateRoomVacancy(room.getRoomno(),1)
                    .orElseThrow(()->new VacancyUpdationException("Room Vacancy","Failed to update a vacancy of roo number "+room.getRoomno()));
       }
        tenant.setRoom(room);
        Account account = tenant.getAccount();
        account.setPassword(passwordEncoder.encode(account.getPassword()));
        tenant.setAccount(account);
        Tenant savedTenant = tenantRepository.save( tenant);

        if(savedTenant!=null)
            return "Account "+account.getUsername()+" Created successfully";
        else
            throw new AccountCreationException(account.getUsername() , "Failed to create an account");

    }


    public String getMyAccountDetail(String mail) {
        Tenant tenant = tenantRepository.findByMail(mail).orElseThrow(() -> new UserNotFoundException("Account", "Account with " + mail + " is not found"));
        return tenant.toString();
    }


    public String deleteMyAccount(String mail) {
        Tenant tenant = tenantRepository.findByMail(mail).orElseThrow(() -> new UserNotFoundException("Account", "Account with " + mail + " is not found"));
        if (!tenantRepository.deleteByMail(mail)) {
            roomRepositoy.updateRoomVacancy(tenant.getRoom().getRoomno(),-1);
            throw new AccountDeleteException("Tenant", "failed to delete " + tenant.getAccount().getUsername());
        }
        return "Account is deleted successfully";
    }
}
