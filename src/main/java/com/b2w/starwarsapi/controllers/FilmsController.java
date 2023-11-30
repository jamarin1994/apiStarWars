package com.b2w.starwarsapi.controllers;

import java.net.URI;
import java.util.List;
import java.util.Optional;

import javax.validation.Valid;

import org.bson.types.ObjectId;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.util.UriComponentsBuilder;

import com.b2w.starwarsapi.models.Films;
import com.b2w.starwarsapi.models.dto.FilmsDto;
import com.b2w.starwarsapi.models.form.FilmForm;
import com.b2w.starwarsapi.repositories.FilmsRepository;
import com.b2w.starwarsapi.services.SwapiService;

@CrossOrigin
@RestController
@RequestMapping("/films")
public class FilmsController {
    
    @Autowired
	private FilmsRepository filmsRepository;

    @Autowired
	private SwapiService swapiService;

   	@GetMapping
	public List<FilmsDto> list() {		
		return FilmsDto.convert( filmsRepository.findAll() );
	} 

    @PostMapping
	public ResponseEntity<?> save(@RequestBody @Valid FilmForm filmForm, UriComponentsBuilder uriBuilder) {
		Films films = new Films();

        // if ( !ObjectId.isValid( filmForm.getId() )) {
		// 	return ResponseEntity.badRequest().body("El id ingresado no es valido");
		// }

		swapiService
			.findFilmById( filmForm.getId() )
			.ifPresent(searchFilmResult -> {
				if( searchFilmResult.isValidResponse() ) {
					films.setEpisode_id(searchFilmResult.getEpisode_id());
					films.setTitle(searchFilmResult.getTitle());
					films.setRelease_date(searchFilmResult.getRelease_date());
				}
			});

		// Guardando la película, construye la ruta uri a la nueva película creada y envía la respuesta:
		Films filmsRes = filmsRepository.save( films );
		URI uri = uriBuilder.path("/films/{id}").buildAndExpand( filmsRes.toObjectIdString() ).toUri();
		
		return ResponseEntity.created(uri).body( FilmsDto.of(filmsRes) );
    }

	@GetMapping("/{id}")
	public ResponseEntity<?> getFilm(@PathVariable String id) {
		
		if ( !ObjectId.isValid(id) ) {
			return ResponseEntity.badRequest().body("El ID pasado no es un ObjectId válido.");
		}
		
		Optional<Films> filmOptional = filmsRepository.findOne(id);
		
		if( filmOptional.isPresent() ) {
			return ResponseEntity.ok( new FilmsDto( filmOptional.get() ) );
		}
		
		return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Las películas con identificación " + id + " no fue encontrado");
		
	}

	@DeleteMapping("/{id}")
	public ResponseEntity<?> remove(@PathVariable String id) {
		
		if ( !ObjectId.isValid(id) ) {
			return ResponseEntity.badRequest().body("El ID pasado no es un ObjectId válido.");
		}
		
		long deleteCount = filmsRepository.delete(id);
		
		if(deleteCount == 0) {
			return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Una película con identificación " + id + " no se encontró que fuera eliminado");
		}
		
		//return ResponseEntity.noContent().build();
		return ResponseEntity.ok().build();
	}

    // @PutMapping(value = "/films/{id}")
    // public ResponseEntity<Films> modifyFilmById(@PathVariable("id") ObjectId id, @Valid @RequestBody Films films) {
    //     films.setId(id);
    //     filmsRepository.save(films);
    //     return ResponseEntity.ok(films);
    // }

    // @GetMapping("/count")
	// public Long getCount() {
	// 	return filmsRepository.count();
	// }

	// @GetMapping("/{id}/films")
	// public ResponseEntity<?> findFilmsByFilmsId(@PathVariable String id) {
		
	// 	if ( !ObjectId.isValid(id) ) {
	// 		return ResponseEntity.badRequest().body("El ID pasado no es un ObjectId válido.");
	// 	}
		
	// 	Optional<Films> filmOptional = filmsRepository.findOne(id);
		
	// 	if( filmOptional.isPresent() ) {
	// 		return ResponseEntity.ok( new PlanetDto( filmOptional.get() ).getFilms() );
	// 	}
		
	// 	return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Una película con identificación " + id + " no fue encontrado");
	// }

	// @GetMapping("/{id}/films/count")
	// public ResponseEntity<?> getCountPlanetsFilmsByPlanetId(@PathVariable String id) {
		
	// 	if ( !ObjectId.isValid(id) ) {
	// 		return ResponseEntity.badRequest().body("The id passed is not a valid ObjectId");
	// 	}
		
	// 	Optional<Planet> filmOptional = filmsRepository.findOne(id);
		
	// 	if( filmOptional.isPresent() ) {
	// 		return ResponseEntity.ok( new FilmsDto( filmOptional.get() ).getFilms().size() );
	// 	}
		
	// 	return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Una película con identificación " + id + " no fue encontrado");
	// }
	
}
