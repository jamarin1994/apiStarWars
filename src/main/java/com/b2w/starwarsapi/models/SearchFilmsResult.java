package com.b2w.starwarsapi.models;

public class SearchFilmsResult {
    
    //private static final long serialVersionUID = 1L;
	
	// Properties:
	private String title;
	private String episode_id;
	private String release_date;
	
	
	// Constructors:
	public SearchFilmsResult() {
		
	}
	
    // public SearchFilmsResult(SearchFilmsResult film) {
    //     this.setTitle(film.getTitle())
    //         .setEpisode_id(film.getEpisode_id())
    //         .setRelease_date(film.getRelease_date());
	// }

	// Getters and Setters
	
	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getEpisode_id() {
		return episode_id;
	}

	public void setEpisode_id(String episode_id) {
		this.episode_id = episode_id;
	}

	public String getRelease_date() {
		return release_date;
	}

	public void setRelease_date(String release_date) {
		this.release_date = release_date;
	}

    public boolean isValidResponse(){
        if (this.getTitle() != null && this.getEpisode_id() != null && this.getRelease_date() != null){
            return true;
        }
        else{
            return false;
        }
    }
	// public List<Planet> getResults() {
	// 	return results;
	// }

	// public void setResults(List<Planet> results) {
	// 	this.results = results;
	// }
	
	// public boolean hasResults() {
	// 	return (this.results.size() > 0);
	// }
	
	// End Getters and Setters
	
	
	// public Planet getFirstResult() {
	// 	return this.results.stream().findFirst().get();
	// }
	
	
	// @Override
	// public String toString() {
	// 	return "SearchPlanetResultDto [count=" + count + ", next=" + next + ", previous=" + previous + ", results="
	// 			+ results + "]";
	// }
}
