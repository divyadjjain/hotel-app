package com.main.controller;

import java.util.Optional;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;

import com.main.domain.Hotel;
import com.main.domain.Room;
import com.main.exception.NotFoundException;
import com.main.persistance.HotelRepository;
import com.main.persistance.RoomRepository;

@Controller
public class HotelSearchController {

    private HotelRepository hotelRepository;
    private RoomRepository roomRepository;

    public HotelSearchController(HotelRepository hotelRepository,
                                 RoomRepository roomRepository) {
        this.hotelRepository = hotelRepository;
        this.roomRepository = roomRepository;
    }

    @GetMapping(value = "/hotel/search")
    public String getHotels(@RequestParam(value = "state", required = false) String state,
                            @RequestParam(value = "suburb", required = false) String suburb,
                            @RequestParam(value = "postcode", required = false) String postcode,
                            Pageable pageable, Model model) {
        Page<Hotel> results = hotelRepository.findAllByLocation(state, postcode, pageable);
        model.addAttribute("hotels", results == null ? Page.empty() : results);
        return "/hotel-template/hotels";
    }

    @GetMapping(value = "/hotel/{id}/rooms")
    public String getHotelRooms(@PathVariable("id") Long id, Pageable pageable, Model model) throws NotFoundException {
        Hotel hotel = hotelRepository.findById(id).orElseThrow(NotFoundException::new);
        
        Optional<Room> availableRooms = roomRepository.findById(id);
        model.addAttribute("rooms", availableRooms);
        model.addAttribute("hotel", hotel);
        return "/hotel-template/rooms";
    }

	/*
	 * // TODO: for testing
	 * 
	 * @GetMapping(value = "/hotels") public String getHotels(Pageable pageable,
	 * Model model) { Page<Hotel> results = hotelRepository.findAll(pageable);
	 * model.addAttribute("hotels", results); return "/hotel-template/hotels"; }
	 */
}
