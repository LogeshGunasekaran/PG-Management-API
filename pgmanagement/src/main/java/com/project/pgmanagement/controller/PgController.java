package com.project.pgmanagement.controller;


import com.project.pgmanagement.model.Pg;
import com.project.pgmanagement.model.Room;
import com.project.pgmanagement.service.PgService;
import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.validation.Valid;
import org.apache.catalina.SessionEvent;
import org.apache.catalina.connector.Response;
import org.apache.catalina.session.StandardManager;
import org.apache.catalina.session.StandardSession;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;
import java.util.List;

@RestController
@RequestMapping("admin")
public class PgController {

    private PgService adminService;

    public PgController(PgService adminService) {
        this.adminService = adminService;
    }

    @PostMapping("/add/pg")
    public String addPg(@Valid @RequestBody Pg pg , @RequestParam("mail")String ownerMail) {
        return adminService.addPg(pg , ownerMail);
    }

    @PostMapping("add/pg/rooms")
    public String addRooms(@Valid@RequestBody List<Room> room , @RequestParam("pgname")String pgName)
    {
        return adminService.addRooms(pgName,room);
    }


}
