package com.b2w.starwarsapi.services;


import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest
@ActiveProfiles("test")
public class SwapiServiceIT {
	
	@Autowired
	private SwapiService swapiService;
	
	@Test
	public void searchForValidPlanetName() {
		
		// String validName = "Coruscant";
		// Optional<SearchPlanetResult> optional = swapiService.findPlanetByName(validName);
		
		// assertTrue(optional.isPresent());
		
		// SearchPlanetResult searchResult = optional.get();
		// System.out.println(searchResult);
		
		// assertTrue(searchResult.hasResults());
		
		// Planet planet = searchResult.getFirstResult();
		// System.out.println(planet);
		
		// assertThat(planet.getName(), equalTo(validName));
		
	}
	
	
	@Test
	public void searchForInvalidPlanetName() {
		
		// String invalidName = "Trapd56sdvsdkvd";
		// Optional<SearchPlanetResult> optional = swapiService.findPlanetByName(invalidName);
		
		// assertTrue( optional.isPresent() );
		
		// SearchPlanetResult searchResult = optional.get();
		// System.out.println(searchResult);
		
		// assertFalse(searchResult.hasResults());
	}

}
