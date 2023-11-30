package com.b2w.starwarsapi.models;

import java.io.Serializable;

import org.bson.types.ObjectId;

public class Films implements Serializable {
    private static final long serialVersionUID = 1L;

    // Properties:
	private ObjectId id;
    private String episode_id;
	private String title;
	private String release_date;

    // Constructors:
	public Films() {	
	}
	public Films(String episode_id,String title, String release_date) {
		this.title = title;
		this.release_date = release_date;
	} 
	// Getters and Setters:
	
    public ObjectId getId() {
		return id;
	}
	
	public String toObjectIdString() {
		return id.toString();
	}
	
	public Films setId(ObjectId id) {
		this.id = id;
		return this;
	}

	public String getEpisode_id() {
		return episode_id;
	}

	public Films setEpisode_id(String episode_id) {
		this.episode_id = episode_id;
		return this;
	}

	public String getTitle() {
		return title;
	}

	public Films setTitle(String title) {
		this.title = title;
		return this;
	}

    public String getRelease_date() {
		return release_date;
	}

	public Films setRelease_date(String release_date) {
		this.release_date = release_date;
		return this;
	}
	
	// public Set<String> getFilms() {
	// 	return films;
	// }
	
	// public Films setFilms(Set<String> films) {
	// 	this.films = films;
	// 	return this;
	// }

	// End Getters and Setters:

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((id == null) ? 0 : id.hashCode());
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
		Films other = (Films) obj;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		return true;
	}
	

	// @Override
	// public String toString() {
	// 	return "Films [id=" + id + ", title=" + title + ", release_date=" + release_date + ", episode_id=" + episode_id + ", films=" + films + "]";
	// }
}
