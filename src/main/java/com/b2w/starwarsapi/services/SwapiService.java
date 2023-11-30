package com.b2w.starwarsapi.services;

import java.util.Arrays;
import java.util.Optional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestClientException;
import org.springframework.web.client.RestTemplate;

// import com.b2w.starwarsapi.models.SearchPlanetResult;
import com.b2w.starwarsapi.models.SearchFilmsResult;

/**
 * Service to manage request over SWAP API
 * 
 * @author Marcus Santos
 *
 */
@Service
public class SwapiService {
	
	// Static Members:
	private final static Logger LOGGER = LoggerFactory.getLogger(SwapiService.class);
	private final static String FMT_URL= "https://swapi.dev/api/films/";
	
	
	@Autowired
	private RestTemplate restTemplate;
	
	
	public Optional<SearchFilmsResult> findFilmById(String id) {
		try {
			Optional<SearchFilmsResult> result =  
             Optional.ofNullable(
            		restTemplate
	            		.exchange(FMT_URL + id, HttpMethod.GET, createHttEntityHeaderForUserAgent(), SearchFilmsResult.class)
	            		.getBody()
	            	);
			return result;
		}
		catch(RestClientException e) {
			LOGGER.error(e.getMessage(), e);
		}
		return Optional.empty();
	}


	private HttpEntity<String> createHttEntityHeaderForUserAgent() {
		HttpHeaders headers = new HttpHeaders();
		headers.setAccept(Arrays.asList(MediaType.APPLICATION_JSON));
		headers.add("user-agent", "Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/54.0.2840.99 Safari/537.36");
		HttpEntity<String> entity = new HttpEntity<String>("parameters", headers);
		return entity;
	}
	
	
}
