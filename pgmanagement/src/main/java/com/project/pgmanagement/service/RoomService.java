package com.project.pgmanagement.service;

import com.project.pgmanagement.dao.RoomRepositoy;
import com.project.pgmanagement.model.Room;
import lombok.NoArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service

public class RoomService {

    private RoomRepositoy roomRepositoy ;

    public RoomService(RoomRepositoy roomRepositoy) {
        this.roomRepositoy = roomRepositoy;
    }

    public  Optional<Room> findRoom(Integer roomno) {
        System.out.println("---------------"+roomRepositoy.findByroomno(roomno).get());
        return roomRepositoy.findByroomno(roomno);
    }



    /*public boolean updateRoomVacancy() {
        Room room = roomRepositoy.updateRoomVacancy(1).orElseThrow(() -> new RuntimeException("failure to update"));
        return room!=null? true: false;
    }*/
}
