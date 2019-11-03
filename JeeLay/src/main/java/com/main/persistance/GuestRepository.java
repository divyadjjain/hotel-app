package com.main.persistance;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.main.domain.Guest;

@Repository
public interface GuestRepository extends CrudRepository<Guest, Long> {
}
