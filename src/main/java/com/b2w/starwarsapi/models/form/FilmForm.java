package com.b2w.starwarsapi.models.form;
import java.io.Serializable;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;


public class FilmForm implements Serializable {
	private static final long serialVersionUID = 1L;

	// Properties:
	// @NotNull @NotEmpty
	// private String episode_id;
	
	// @NotNull @NotEmpty
	// private String title;
	
	// @NotNull @NotEmpty
	// private String release_date;
	
	@NotNull @NotEmpty
	private String id;

	// Constructors:
	public FilmForm() {
		
	}
	// public FilmForm(String episode_id, String title, String release_date) {
	// 	this.episode_id = episode_id;
	// 	this.title = title;
	// 	this.release_date = release_date;
	// }

		// Getters and Setters: 

		// public String getEpisode_id() {
		// 	return episode_id;
		// }
	
		// public void setEpisode_id(String episode_id) {
		// 	this.episode_id = episode_id;
		// }
	
		// public String getTitle() {
		// 	return title;
		// }
	
		// public void setTitle(String title) {
		// 	this.title = title;
		// }
	
		// public String getRelease_date() {
		// 	return release_date;
		// }
	
		// public void setRelease_date(String release_date) {
		// 	this.release_date = release_date;
		// }
		
		public FilmForm(String id) {
			this.id = id;
		}

		public String getId() {
			return id;
		}

		public void setId(String id) {
			this.id = id;
		}
}
