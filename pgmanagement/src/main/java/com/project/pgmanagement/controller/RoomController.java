package com.project.pgmanagement.controller;

import com.project.pgmanagement.service.RoomService;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class RoomController {

    private RoomService roomService;

    public RoomController(RoomService roomService) {
        this.roomService = roomService;
    }


}
