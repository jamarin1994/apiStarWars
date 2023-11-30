package com.b2w.starwarsapi.models.dto;

import java.io.Serializable;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;



import org.bson.types.ObjectId;

import com.b2w.starwarsapi.models.Films;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.fasterxml.jackson.databind.ser.std.ToStringSerializer;

public class FilmsDto implements Serializable {
    private static final long serialVersionUID = 1L;

    	// Properties:
	
	@JsonSerialize(using = ToStringSerializer.class)
	private ObjectId id;

	private String episode_id;
	private String title;
	
	private String release_date;
	
	private Set<String> films = new LinkedHashSet<>();

    	// Constructors:
	public FilmsDto() {
	}

    	public FilmsDto(Films films) {
		this.setId(films.getId())
            .setEpisode_id(films.getEpisode_id())
			.setTitle(films.getTitle())
			.setRelease_date(films.getRelease_date());
			// .setFilms(films.getFilms());
	}

	// Getters and Setters:
	
	public ObjectId getId() {
		return id;
	}
	
	public FilmsDto setId(ObjectId id) {
		this.id = id;
		return this;
	}
	
	public String getEpisode_id() {
		return episode_id;
	}

    public FilmsDto setEpisode_id(String episode_id) {
        this.episode_id = episode_id;
        return this;
    }
	
	public FilmsDto setTitle(String title) {
        this.title = title;
		return this;
	}

	public String getTitle() {
		return title;
	}
	
	public String getRelease_date() {
        return release_date;
	}
	
	public FilmsDto setRelease_date(String release_date) {
        this.release_date = release_date;
        return this;
    }

	public Set<String> getFilms() {
		return films;
	}
	
	public FilmsDto setFilms(Set<String> films) {
		this.films = films;
		return this;
	}

	// End Getters and Setters:
	
	
	public static List<FilmsDto> convert(List<Films> films) {
		return films.stream()
				.map(FilmsDto::of)
				.collect(Collectors.toList());
	}

	public static FilmsDto of(Films films) {
		return new FilmsDto(films);
	}
	
	
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((episode_id == null) ? 0 : episode_id.hashCode());
		result = prime * result + ((films == null) ? 0 : films.hashCode());
		result = prime * result + ((id == null) ? 0 : id.hashCode());
		result = prime * result + ((title == null) ? 0 : title.hashCode());
		result = prime * result + ((release_date == null) ? 0 : release_date.hashCode());
		return result;
	}
	

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		FilmsDto other = (FilmsDto) obj;
		if (episode_id == null) {
			if (other.episode_id != null)
				return false;
		} else if (!episode_id.equals(other.episode_id))
			return false;
		if (films == null) {
			if (other.films != null)
				return false;
		} else if (!films.equals(other.films))
			return false;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		if (title == null) {
			if (other.title != null)
				return false;
		} else if (!title.equals(other.title))
			return false;
		if (release_date == null) {
			if (other.release_date != null)
				return false;
		} else if (!release_date.equals(other.release_date))
			return false;
		return true;
	}

}
