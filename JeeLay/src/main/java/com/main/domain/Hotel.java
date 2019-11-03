package com.main.domain;

import java.math.BigDecimal;
import java.time.LocalTime;
import java.time.temporal.ChronoUnit;
import java.util.HashSet;
import java.util.List;
import java.util.Objects;
import java.util.Set;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;

import org.springframework.format.annotation.DateTimeFormat;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.main.domain.location.Address;

@Entity
public class Hotel{

	@Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @Column(nullable = false)
    private String hotelName;

    @Column(nullable = false)
    private Address address;

    @Column(nullable = false)
    private int stars;

    @Column(nullable = false)
    private String email;

  
	@OneToMany(mappedBy = "hotel", cascade = CascadeType.ALL)
    // Stop bidirectional relationship which cause a cycle.
    @JsonIgnore
    private Set<Room> rooms;

    @Column(nullable = false)
    @DateTimeFormat(pattern = "yyyy.MM.dd HH:mm:ss")
    private LocalTime earliestCheckInTime;

    @Column(nullable = false)
    @DateTimeFormat(pattern = "yyyy.MM.dd HH:mm:ss")
    private LocalTime latestCheckInTime;

    @Column(nullable = false)
    @DateTimeFormat(pattern = "yyyy.MM.dd HH:mm:ss")
    private LocalTime standardCheckOutTime;

    @Column(nullable = false)
    @DateTimeFormat(pattern = "yyyy.MM.dd HH:mm:ss")
    private LocalTime latestCheckOutTime;

    @Column(nullable = false)
    private BigDecimal lateCheckoutFee;

    private final static LocalTime DEFAULT_EARLIEST_CHECK_IN = LocalTime.of(7, 0);
    private final static LocalTime DEFAULT_LATEST_CHECK_IN = LocalTime.of(22, 0);
    private final static LocalTime DEFAULT_STANDARD_CHECKOUT = LocalTime.of(11, 0);
    private final static LocalTime DEFAULT_LATEST_CHECKOUT = LocalTime.of(22, 0);
    private final static BigDecimal DEFAULT_LATE_CHECKOUT_FEE = BigDecimal.valueOf(15.95);

    public Hotel(String hotelName, Address address, int stars, String email) {
        this(hotelName, address, stars, email,
                DEFAULT_EARLIEST_CHECK_IN,
                DEFAULT_LATEST_CHECK_IN,
                DEFAULT_STANDARD_CHECKOUT,
                DEFAULT_LATEST_CHECKOUT,
                DEFAULT_LATE_CHECKOUT_FEE);
    }

    public Hotel(String hotelName, Address address, int stars, String email,
                 LocalTime earliestCheckInTime,
                 LocalTime latestCheckInTime,
                 LocalTime standardCheckOutTime,
                 LocalTime latestCheckOutTime,
                 BigDecimal lateCheckoutFee) {
        this.hotelName = hotelName;
        this.address = address;
        this.stars = stars;
        this.email = email;
        this.rooms = new HashSet<>();
        this.earliestCheckInTime = earliestCheckInTime;
        this.latestCheckInTime = latestCheckInTime;
        this.standardCheckOutTime = standardCheckOutTime;
        this.latestCheckOutTime = latestCheckOutTime;
        this.lateCheckoutFee = lateCheckoutFee;
    }

    public Hotel() {
    }

    /**
     * Adds the {@code Room} to this {@code Hotel} and sets the bidirectional relationship
     * of the {@code Room}.
     */
    public void addRoom(Room room) {
        rooms.add(room);
        room.setHotel(this);
    }

    public String gethotelName() {
        return hotelName;
    }

    public void sethotelName(String hotelName) {
        this.hotelName = hotelName;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Address getAddress() {
        return address;
    }

    public int getStars() {
        return stars;
    }

    public void setStars(int stars) {
        this.stars = stars;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public Set<Room> getRooms() {
        return rooms;
    }

    public void setRooms(Set<Room> rooms) {
        this.rooms = rooms;
    }

    public LocalTime getEarliestCheckInTime() {
        return earliestCheckInTime;
    }

    public void setEarliestCheckInTime(LocalTime earliestCheckInTime) {
        this.earliestCheckInTime = earliestCheckInTime;
    }

    public LocalTime getLatestCheckInTime() {
        return latestCheckInTime;
    }

    public void setLatestCheckInTime(LocalTime latestCheckInTime) {
        this.latestCheckInTime = latestCheckInTime;
    }

    public LocalTime getStandardCheckOutTime() {
        return standardCheckOutTime;
    }

    public void setStandardCheckOutTime(LocalTime standardCheckOutTime) {
        this.standardCheckOutTime = standardCheckOutTime;
    }

    public LocalTime getLatestCheckOutTime() {
        return latestCheckOutTime;
    }

    public void setLatestCheckOutTime(LocalTime latestCheckOutTime) {
        this.latestCheckOutTime = latestCheckOutTime;
    }

    public BigDecimal getLateCheckoutFee() {
        return lateCheckoutFee;
    }

    public void setLateCheckoutFee(BigDecimal lateCheckoutFee) {
        this.lateCheckoutFee = lateCheckoutFee;
    }
    public void setAddress(Address address) {
  		this.address = address;
  	}


    /**
     * Generates range of allowable check in times spanning from the earliest to latest times for this hotel.
     * <p>This provides an estimated check in time to help staff organise the room.</p>
     */
    public List<LocalTime> allowableCheckInTimes() {
        long span = ChronoUnit.HOURS.between(earliestCheckInTime, latestCheckInTime) + 1;

        return Stream.iterate(earliestCheckInTime, time -> time.plusHours(1))
                .limit(span)
                .collect(Collectors.toList());
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Hotel hotel = (Hotel) o;
        return Objects.equals(address, hotel.address) &&
                Objects.equals(email, hotel.email);
    }

    @Override
    public int hashCode() {

        return Objects.hash(address, email);
    }

    @Override
    public String toString() {
        return "Hotel{" +
                "id=" + id +
                ", address=" + address +
                ", stars=" + stars +
                ", email='" + email + '\'' +
                '}';
    }
}
