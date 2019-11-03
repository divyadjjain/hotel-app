package com.main.persistance;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.main.domain.Room;

@Repository
public interface RoomRepository extends CrudRepository<Room, Long> {

}
