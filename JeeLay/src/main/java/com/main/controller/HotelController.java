package com.main.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.main.domain.Hotel;
import com.main.domain.location.Address;
import com.main.persistance.HotelRepository;

@Controller
public class HotelController {

	@Autowired
	HotelRepository HotelRepository;
	
	@RequestMapping("/home")
	public String searchForHotel(Model model) {
		return "hotel-template/index.html";
	}
	
	@RequestMapping("/bookingForm")
	public String bookingTicketForm() {
		return "hotel-template/bookingForm.html";
	}
	
	@RequestMapping(value="/hotel/addHotel", method = RequestMethod.GET)
    public String addHotelForm(Address address, Model model) {
		Hotel hotel = new Hotel();
		hotel.setAddress(address);
		model.addAttribute("hotel", hotel);
    	return "hotel-template/addHotel";
    }

    @RequestMapping(value="/hotel/addHotel", method = RequestMethod.POST)
    public String addHotel(@ModelAttribute("hotel") Hotel hotel, Model model) {
    	HotelRepository.save(hotel);
    	return "redirect:/hotel/addHotel";
    }
}
