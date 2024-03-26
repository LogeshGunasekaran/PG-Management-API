package com.project.pgmanagement.service;

import com.project.pgmanagement.dao.OwnerRepository;
import com.project.pgmanagement.dao.PgRepository;
import com.project.pgmanagement.exception.PgCreationException;
import com.project.pgmanagement.exception.RoomCreationException;
import com.project.pgmanagement.exception.UserNotFoundException;
import com.project.pgmanagement.model.Owner;
import com.project.pgmanagement.model.Pg;
import com.project.pgmanagement.model.Room;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class PgService {

    private PgRepository adminRepository;
    private OwnerRepository ownerRepository;

    public PgService(PgRepository adminRepository, OwnerRepository ownerRepository) {
        this.adminRepository = adminRepository;
        this.ownerRepository = ownerRepository;
    }

    public String addPg(Pg pg ,String ownerMail) {
        Owner owner = ownerRepository.findByMail(ownerMail)
                                     .orElseThrow(() -> new UserNotFoundException("Owner", "Owner not found with " + ownerMail));
        pg.setOwner(owner);

        if (adminRepository.save(pg) != null) return pg.getPgName() + " welcome to the PG world of management";
        else throw new PgCreationException(pg.getPgName(), "Failed add your PG");
    }

    public String addRooms(String pgName, List<Room> rooms) {
        Pg roomCreation = adminRepository.findByPgName(pgName)
                .map(pg -> {
                    pg.setRooms(rooms);
                    return adminRepository.save(pg);
              }).orElseThrow(() -> new RoomCreationException(pgName, "Failed to save rooms"));

        if (roomCreation.equals(null)) return "Failed to add rooms in your pg : "+pgName;
        else return "Rooms added into your pg : "+pgName;

    }
}
