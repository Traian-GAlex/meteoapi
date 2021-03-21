package it.univpm.traianubertinivisi.meteoapi.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import it.univpm.traianubertinivisi.meteoapi.services.CityService;
import it.univpm.traianubertinivisi.openweather.city.DbCity;

@RestController
public class HomeRestController {
	
	@Autowired
	private CityService cityService;
	
	// valore caricato in automatico dal file application.properties
	@Value("${meteoapi.pwd}")
	private String pwd;
	
	@GetMapping("/")
	public String home() {
		// ha come risultato una stringa contenente il nome dell'applicativo ed il numero delle città
		return "<h1>Meteo Api Homepage</h1> <br/> \n\r <p> There are " + this.cityService.getCityCount() + " cities in database.</p>";
	}
	
	@GetMapping("/cities")
	public List<DbCity> cities(
			@RequestParam(name="city", required=false) String cityOrNull,
			@RequestParam(name="country", required=false) String country) throws Exception {
		// Richiede l'elenco delle città che rispettano i parametri dati
		return this.cityService.getCityList(cityOrNull, country);
	}
	
	@GetMapping("/cities/load/{pwd}")
	public String citiesLoad(
			@PathVariable(name = "pwd", required = true) String pwd
			) {
		// Carica la lista delle città messa a disposizione dal openweathermap.org e che si trova in
		// src\main\resources\static\openweather\city.list.json
		// Dev'essere passata nell'URL una stringa che fa da password (attualmente è secret)
		// Cè da troncare la tabella "cities" per evitare valori doppi.
		if (!this.pwd.equals(pwd)) return "Cannot load the cities!";
		this.cityService.startLoadingCities();
		return "Started to load cities...";
	}
	
	@GetMapping("/cities/stop/{pwd}")
	public String citiesStopLoad(
			@PathVariable(name = "pwd", required = true) String pwd
			) {
		// Ferma il caricamento della lista delle città messa a disposizione dal openweathermap.org 
		// Dev'essere passata nell'URL una stringa che fa da password (attualmente è secret)
		if (!this.pwd.equals(pwd)) return "Cannot stop loading the cities!";
		this.cityService.stopLoadingCities();
		return "Stop loading cities...";
	}
	
	
	
	
	
}
