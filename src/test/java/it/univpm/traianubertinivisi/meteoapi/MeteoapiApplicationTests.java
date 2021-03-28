package it.univpm.traianubertinivisi.meteoapi;

import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import java.util.List;
import it.univpm.traianubertinivisi.meteoapi.controllers.HomeRestController;
import it.univpm.traianubertinivisi.meteoapi.services.*;
import it.univpm.traianubertinivisi.openweather.city.*;

@SpringBootTest
class MeteoapiApplicationTests {

	@Autowired
	HomeRestController homeRestController;
	
	@Autowired
	CityService cityService;
	
	@Test
	void nameTest (){
		City test = new City(); 
		 test.setName("Macerata");
		assertEquals("Macerata", test.toString());
	}

	@Test
	void homeTest() {
		assertTrue(this.homeRestController.home() instanceof String);
	}

	@Test
	void citiesLoadTest() {
		assertTrue(this.homeRestController.citiesLoad("00980018-f515-4390-9961-d865c629bcf9") instanceof String);
		assertTrue(this.homeRestController.citiesStopLoad("00980018-f515-4390-9961-d865c629bcf9") instanceof String);
	}

	@Test
	void citiesTest() throws Exception {
		assertTrue(this.homeRestController.cities("Macerata","It") instanceof List<?>);
	}

  }
