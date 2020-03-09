package com.glofox.studio.api;

import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import org.hamcrest.Matchers;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

@SpringBootTest
public class BookingControllerTest {
	
	private MockMvc mockMvc;	
	private String classJson;
	
	@InjectMocks
	private BookingController bookingController;
	
	
	@BeforeEach
	public void setUp() {
		classJson = "{\"name\":\"name\", \"capacity\":\"10\", \"startDate\":\"2020-03-03\", \"endDate\":\"2020-03-03\"}";
		mockMvc = MockMvcBuilders.standaloneSetup(bookingController)
				.build();
	}
	
	@Test
	public void testCreateClass() throws Exception {
		
		mockMvc.perform(MockMvcRequestBuilders
				.post("/classes")
				.contentType(MediaType.APPLICATION_JSON)
				.content(classJson))
				.andExpect(status().isOk())
				.andExpect(jsonPath("$.id").isNumber())
				.andExpect(jsonPath("$.name").value("name"))
				.andExpect(jsonPath("$.capacity").value("10"))
				.andExpect(jsonPath("$.startDate").value("2020-03-03"))
				.andExpect(jsonPath("$.endDate").value("2020-03-03"));
	}
	
	@Test
	public void testGetClasses() throws Exception {
		for (int i = 0; i < 2; i++) {
			mockMvc.perform(MockMvcRequestBuilders
					.post("/classes")
					.contentType(MediaType.APPLICATION_JSON)
					.content(classJson))
					.andExpect(status().isOk());
		}
		mockMvc.perform(MockMvcRequestBuilders
				.get("/classes"))
				.andExpect(status().isOk())
				.andExpect(jsonPath("$.*", Matchers.hasSize(2)));
	}
	
	@Test
	public void getAClass() throws Exception {
		mockMvc.perform(MockMvcRequestBuilders
				.post("/classes")
				.contentType(MediaType.APPLICATION_JSON)
				.content(classJson))
				.andExpect(status().isOk());
		mockMvc.perform(MockMvcRequestBuilders
				.get("/classes/{id}","1"))
				.andExpect(status().isOk())
				.andExpect(jsonPath("$.name").value("name"));
	}
	
	@Test
	public void creatBooking() throws Exception {
		classJson = "{\"name\":\"name\", \"capacity\":\"10\", \"startDate\":\"2020-03-03\", \"endDate\":\"2020-03-04\"}";
		mockMvc.perform(MockMvcRequestBuilders
				.post("/classes")
				.contentType(MediaType.APPLICATION_JSON)
				.content(classJson))
				.andExpect(status().isOk());
		String bookingJson = "{\"memberName\":\"memberName\", \"classId\":\"1\", \"date\":\"2020-03-03\"}";
		mockMvc.perform(MockMvcRequestBuilders
				.post("/bookings")
				.contentType(MediaType.APPLICATION_JSON)
				.content(bookingJson))
				.andExpect(status().isOk())
				.andExpect(jsonPath("$.id").isNumber());
		
	}
	
	@Test
	public void creatBookingOutOfDate() throws Exception {
		classJson = "{\"name\":\"name\", \"capacity\":\"10\", \"startDate\":\"2020-03-03\", \"endDate\":\"2020-03-04\"}";
		mockMvc.perform(MockMvcRequestBuilders
				.post("/classes")
				.contentType(MediaType.APPLICATION_JSON)
				.content(classJson))
				.andExpect(status().isOk());
		String bookingJson = "{\"memberName\":\"memberName\", \"classId\":\"1\", \"date\":\"2020-03-05\"}";
		mockMvc.perform(MockMvcRequestBuilders
				.post("/bookings")
				.contentType(MediaType.APPLICATION_JSON)
				.content(bookingJson))
				.andExpect(status().isPreconditionFailed());
	}
	
	@Test
	public void getBookings() throws Exception {
		classJson = "{\"name\":\"name\", \"capacity\":\"10\", \"startDate\":\"2020-03-03\", \"endDate\":\"2020-03-04\"}";
		mockMvc.perform(MockMvcRequestBuilders
				.post("/classes")
				.contentType(MediaType.APPLICATION_JSON)
				.content(classJson))
				.andExpect(status().isOk());
		String bookingJson = "{\"memberName\":\"memberName\", \"classId\":\"1\", \"date\":\"2020-03-03\"}";
		for (int i = 0; i < 2; i++) {
			mockMvc.perform(MockMvcRequestBuilders
					.post("/bookings")
					.contentType(MediaType.APPLICATION_JSON)
					.content(bookingJson))
					.andExpect(status().isOk());
		}
		mockMvc.perform(MockMvcRequestBuilders
				.get("/bookings"))
				.andExpect(status().isOk())
				.andExpect(jsonPath("$.*", Matchers.hasSize(2)));
	}
	
	
}
