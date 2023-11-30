package com.b2w.starwarsapi.controllers;

import static org.junit.jupiter.api.Assertions.assertNotNull;

import java.util.Arrays;
import java.util.List;
import static org.assertj.core.api.Assertions.assertThat;

import org.bson.types.ObjectId;
import org.junit.Test;
import org.junit.jupiter.api.DisplayName;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import com.b2w.starwarsapi.models.form.FilmForm;
import com.b2w.starwarsapi.models.form.FilmForm;

import com.b2w.starwarsapi.models.Films;
import com.b2w.starwarsapi.models.dto.FilmsDto;

public class FilmsControllerIT extends AbstractControllerIT {

    @DisplayName("GET /films with 2 films")
	@Test
	public void shouldGetFilms() {
		// GIVEN
		Films film1 = createFilm("Film 1", "Tropical", "Rocks");
		Films film2 = createFilm("Film 2", "Temperate", "Rain Forest");
		List<FilmsDto> filmsDtos = Arrays.asList(new FilmsDto(film1), new FilmsDto(film2));
		// WHEN
		ResponseEntity<List<FilmsDto>> result = rest.exchange(URL + "/films", HttpMethod.GET, null, new ParameterizedTypeReference<List<FilmsDto>>() {});
		// THEN
		assertThat(result.getStatusCode()).isEqualTo(HttpStatus.OK);
		assertThat(result.getBody()).containsExactlyInAnyOrderElementsOf(filmsDtos);
	}

    @DisplayName("GET /films?title=Film+1 by Title with 2 films")
	@Test
	public void shouldGetPlanetsByName() {
		// GIVEN
		Films film1 = createFilm("Flim", "Tropical", "Rocks");
		Films film2 = createFilm("Flim", "Temperate", "Rain Forest");
		List<FilmsDto> filmsDtos = Arrays.asList(new FilmsDto(film1), new FilmsDto(film2));
		// WHEN
		ResponseEntity<List<FilmsDto>> result = rest.exchange(URL + "/films?title=Flim", HttpMethod.GET, null, new ParameterizedTypeReference<List<FilmsDto>>() {});
		// THEN
		assertThat(result.getStatusCode()).isEqualTo(HttpStatus.OK);
		assertThat(result.getBody()).containsExactlyInAnyOrderElementsOf(filmsDtos);
	}

    @DisplayName("GET /person/{id}")
	@Test
	public void shouldGetFilmById() {
		// GIVEN
		Films filmInserted = createFilm("films", "Temperate", "Rain Forest");
		ObjectId idInserted = filmInserted.getId();
		// WHEN
		ResponseEntity<FilmsDto> result = rest.getForEntity(URL + "/films/" + idInserted, FilmsDto.class);
		// THEN
		assertThat(result.getStatusCode()).isEqualTo(HttpStatus.OK);
        assertThat(result.getBody()).isEqualTo(new FilmsDto(filmInserted));
	}

    // @DisplayName("POST /films 1 film")
	// @Test
	// public void postFilm() {
	// 	// WHEN
	// 	ResponseEntity<FilmsDto> result = rest.postForEntity(URL + "/films", new FilmForm("Film Blue", "Temperate", "Forest"), FilmsDto.class);
	// 	// THEN
	// 	assertThat(result.getStatusCode()).isEqualTo(HttpStatus.CREATED);
	// 	FilmsDto filmsDto = result.getBody();
	// 	assertNotNull(filmsDto.getId());
	// 	assertThat(filmsDto.getEpisode_id()).isEqualTo("Film");
	// 	assertThat(filmsDto.getRelease_date()).isEqualTo("Temperate");
	// 	assertThat(filmsDto.getTitle()).isEqualTo("Forest");
	// }

	// @DisplayName("POST /films 1 invalid film")
	// @Test
	// public void postInvalidFilm() {
	// 	// WHEN
	// 	System.out.println("rest" + rest);
	// 	ResponseEntity<?> result = rest.postForEntity(URL + "/films", new FilmForm(null, "Temperate", "Forest"), Object.class);
	// 	// THEN
	// 	assertThat(result.getStatusCode()).isEqualTo(HttpStatus.BAD_REQUEST);
	// 	//Object body = result.getBody();
	// }
	
	
	@DisplayName("DELETE /films/{id}")
    @Test
    public void deletePersonById() {
		// GIVEN
		Films films = createFilm("Film to be deleted", "Tropical", "Rocks");
		ObjectId idInserted = films.getId();
		// WHEN
		ResponseEntity<Long> result = rest.exchange(URL + "/films/" + idInserted.toString(), HttpMethod.DELETE, null, new ParameterizedTypeReference<Long>() {});
		// THEN
		assertThat(result.getStatusCode()).isEqualTo(HttpStatus.OK);
		// assertThat(filmsRepository.count()).isEqualTo(0L);
	}
	
	
	@DisplayName("GET /films")
    @Test
    public void shouldGetPlanetsCount() {
		// GIVEN
		createFilm("Film 1", "Tropical", "Rocks");
		createFilm("Film 2", "Temperate", "Rain Forest");
		createFilm("Film 3", "Desert", "Desert and Sand");
		// WHEN
		ResponseEntity<Long> result = rest.getForEntity(URL + "/films", Long.class);
		// THEN
		assertThat(result.getStatusCode()).isEqualTo(HttpStatus.OK);
        assertThat(result.getBody()).isEqualTo(3L);
	}

    private Films createFilm(String episode_id, String title, String release_date) {
		Films films = new Films(episode_id, title, release_date);
		return filmsRepository.save(films);
	}
}
