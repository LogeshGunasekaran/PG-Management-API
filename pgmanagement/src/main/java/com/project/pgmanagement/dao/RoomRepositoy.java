package com.project.pgmanagement.dao;

import com.project.pgmanagement.model.Room;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

import java.util.Optional;

public interface RoomRepositoy extends JpaRepository<Room , Integer> {

    Optional<Room> findByroomno(Integer roomno);

    @Modifying
    @Query(value = "UPDATE room r SET r.vacancy=((SELECT rr.vacancy FROM room rr WHERE rr.roomno=:roomnum)+:update) where r.roomno=:roomnum",nativeQuery = true )
    Optional<Room> updateRoomVacancy(Integer roomnum,Integer update);
}
