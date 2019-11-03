package com.main.persistance;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.main.domain.Hotel;

@Repository
public interface HotelRepository extends CrudRepository<Hotel, Long> {

    /**
     * See HotelPredicates.byLocation to see a query dsl alternative approach.
     *
     * Finds all hotels by state and suburb and postcode.
     *
     * <p>coalesce simplifies having multiple optional args.</p>
     *
     * <pre>
     *     1. Example: state = null
     *     where upper(h.address.state) = coalesce(null, upper(h.address.state))
     *     where upper(h.address.state) = upper(h.address.state) // -> row will match itself..
     *
     *     2. Example: state != null
     *     where upper(h.address.state) = coalesce(VIC, upper(h.address.state))
     *     where upper(h.address.state) = VIC // the state will now be used in the query.
     * </pre>
     */
    @Query("select h from Hotel h " +
            "where upper(h.address.city) = coalesce(upper(:city), upper(h.address.city)) " +
            "and h.address.postcode = coalesce(:postcode, h.address.postcode)"
    )
    Page<Hotel> findAllByLocation(@Param("city") String city,
                                  @Param("postcode") String postcode,
                                  Pageable pageable);
}
