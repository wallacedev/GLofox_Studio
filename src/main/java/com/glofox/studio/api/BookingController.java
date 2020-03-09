package com.glofox.studio.api;

import java.util.HashMap;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;

import com.glofox.studio.model.Book;
import com.glofox.studio.model.Class;

@RestController
public class BookingController {

	HashMap<Integer, Class> classes;
	Integer id_class;
	HashMap<Integer, Book> booking;
	Integer id_book;
	
	private final String ERROR_MESSAGE_CLASS_NOT_EXIST = "The informed class does not exist.";
	private final String ERROR_MESSAGE_DATE_BOOKING_OUT_OF_DATE = "Date informed no avaliable in the class.";
	
	public BookingController() {
		classes = new HashMap<Integer, Class>();
		id_class = 0;
		booking = new HashMap<Integer, Book>();
		id_book = 0;
	}

	@PostMapping("/classes")
	public Class createClass(@RequestBody Class object) {
		object.setId(id_class+1);
		id_class+=1;
		classes.put(object.getId(), object);
		return object;
	}

	@GetMapping("/classes")
	public HashMap<Integer, Class> getClasses() {
		return classes;
	}

	@GetMapping("/classes/{id}")
	public Class getClass(@PathVariable Integer id) {
		if(classes.containsKey(id)) {
			return classes.get(id);
		}
		else {
			throw new ResponseStatusException(HttpStatus.PRECONDITION_FAILED, ERROR_MESSAGE_CLASS_NOT_EXIST);
		}
	}
	
	@PostMapping("/bookings")
	public Book createBook(@RequestBody Book object) {
		if(!classes.containsKey(object.getClassId())){
			throw new ResponseStatusException(HttpStatus.PRECONDITION_FAILED, ERROR_MESSAGE_CLASS_NOT_EXIST);
		}
		if(object.getDate().compareTo(classes.get(object.getClassId()).getStartDate())<0 || classes.get(object.getClassId()).getStartDate().compareTo(object.getDate())<0) {
			throw new ResponseStatusException(HttpStatus.PRECONDITION_FAILED, ERROR_MESSAGE_DATE_BOOKING_OUT_OF_DATE);
		}
		object.setId(id_book+1);
		id_book+=1;
		booking.put(object.getId(), object);
		return object;
	}
	
	@GetMapping("/bookings")
	public HashMap<Integer, Book> getBookings(){
		return booking;
	}
}
