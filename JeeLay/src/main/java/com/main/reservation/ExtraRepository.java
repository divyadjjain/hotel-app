package com.main.reservation;

import java.util.List;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.main.domain.Extra;

@Repository
public interface ExtraRepository extends CrudRepository<Extra, Long> {
    List<Extra> findAllByTypeAndCategory(Extra.Type type, Extra.Category category);
}
