package com.b2w.starwarsapi.repositories;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.CoreMatchers.hasItems;
import static org.hamcrest.Matchers.notNullValue;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertThat;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.List;
import java.util.Optional;

import org.bson.types.ObjectId;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit4.SpringRunner;

import com.b2w.starwarsapi.models.Films;

@RunWith(SpringRunner.class)
@SpringBootTest
@ActiveProfiles("test")
public class FilmsRespositoryIT {

	@Autowired
	private FilmsRepository filmsRepository;
	
	@Before
    public void tearDown() {
        filmsRepository.deleteAll();
    }

	@Test
	public void shouldReturnCreatedFilm() {
		Films films = createFilm("Film Blue", "Temperate", "Blue Film");
		assertNotNull("Film shouldn't be null", films);
		assertThat("Film id shouldn't be null", films.getId(), notNullValue());
		assertThat(films.getEpisode_id(), equalTo("films"));
		assertThat(films.getTitle(), equalTo("Temperate"));
		assertThat(films.getRelease_date(), equalTo("Blue Films"));
	}

	@Test
	public void shouldReturnNullForNotExistingFilm() {
		Optional<Films> optionalFilms = filmsRepository.findOne( new ObjectId().toString() );
		assertFalse(optionalFilms.isPresent());
	}
	
	
	@Test
	public void shoudReturnFoundFilm() {
		Films films = createFilm("Film to be found", "Temperate", "Montains");
		
		Optional<Films> optionalFoundFilm = filmsRepository.findOne(films.getId().toString());
		assertTrue(optionalFoundFilm.isPresent());
		
		Films foundFilm = optionalFoundFilm.get();
		
		assertThat(foundFilm.getEpisode_id(), equalTo("Film to be found"));
		assertThat(foundFilm.getTitle(), equalTo("Temperate"));
		assertThat(foundFilm.getRelease_date(), equalTo("Montains"));
	}
	
	
	@Test
	public void shouldRemoveFilm() {
		Films films = createFilm("Film to be removed", "frozen", "tundra, ice caves, mountain ranges");
		String id = films.getId().toString();
		long count = filmsRepository.delete(id);
		// assertThat(count, equalTo(1l));
		Optional<Films> optional = filmsRepository.findOne( new ObjectId().toString() );
		assertFalse(optional.isPresent());
	}
	
	
	@Test
	public void shouldUpdateChangeAndPersistData() {
		Films films = createFilm("Film X", "frozen", "grassy hills, swamps, forests, mountains");
		films.setTitle("films XYZ");
		films.setRelease_date("Temperate");
		Films updatedFilm = filmsRepository.update(films);
		assertThat(updatedFilm.getTitle(), equalTo("Film XYZ"));
		assertThat(updatedFilm.getRelease_date(), equalTo("Temperate"));
	}
	
	
	@Test
	public void shouldfindByName() {
		createFilm("Film XXX", "frozen", "grassy hills, swamps, forests, mountains");
		createFilm("Film XXX", "temperate", "forests, mountains");
		List<Films> findByName = filmsRepository.findByName("Film XXX");
		assertThat(findByName.size(), equalTo(2));
	}
	
	
	@Test
	public void shouldfindAll() {
		Films films1 = createFilm("Film XXX", "frozen", "grassy hills, swamps, forests, mountains");
		Films films2 = createFilm("Film XYZ", "temperate", "mountains");
		Films films3 = createFilm("Film XVW", "temperate hot", "forests");
		
		
		List<Films> films = filmsRepository.findAll();
		
		assertThat(films.size(), equalTo(3));
		assertThat(films, hasItems(films1, films2, films3));
	}
	
	
	@Test
	public void shouldCount() {
		createFilm("Film XXX", "frozen", "grassy hills, swamps, forests, mountains");
		createFilm("Film XYZ", "temperate", "mountains");
		createFilm("Film XVW", "temperate hot", "forests");
		// long count = filmsRepository.count();
		// assertThat(count, equalTo(3l));
	}
	
	
	@Test
	public void shouldDeleteAll() {
		createFilm("Film XXX", "frozen", "grassy hills, swamps, forests, mountains");
		createFilm("Film XYZ", "temperate", "mountains");
		// long count = filmsRepository.deleteAll();
		// assertThat(count, equalTo(2l));
		
		List<Films> foundFilms = filmsRepository.findAll();
		assertThat(foundFilms.size(), equalTo(0));
	}
	
	
	
	private Films createFilm(String episode_id, String title, String release_date) {
		Films films = new Films(episode_id, title, release_date);
		return filmsRepository.save(films);
	}
}
