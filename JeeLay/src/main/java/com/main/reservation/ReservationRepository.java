package com.main.reservation;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.main.domain.Reservation;

@Repository
public interface ReservationRepository extends CrudRepository<Reservation, Long> {
}
