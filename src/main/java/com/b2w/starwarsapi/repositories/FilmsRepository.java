package com.b2w.starwarsapi.repositories;

import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Repository;

import com.b2w.starwarsapi.models.Films;

@Repository
public interface FilmsRepository {

    Films save(Films films);
	
	List<Films> findAll();
	
	List<Films> findByName(String name);
	
	Optional<Films> findOne(String episode_id);
	
	// long episode_id();

    long delete(String episode_id);

    long deleteAll();

    Films update(Films person); 
}
