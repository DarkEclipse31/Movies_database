package com.example.movies_3.model;

// Movie data class
public class Movie {

    // Movie title
    private String title;

    // Movie year
    private Integer year;

    // Movie genre
    private String genre;

    // Poster field
    private String posterId;

    // Default movie
    public Movie() {
        this("No info", null, "No info", "No info");
    }

    // Full constructor
    public Movie(String title, Integer year, String genre, String posterId) {
        setTitle(title);
        setYear(year);
        setGenre(genre);
        setPosterId(posterId);
    }

    // Get title
    public String getTitle() {
        return title;
    }

    // Set title
    public void setTitle(String title) {
        if (title == null || title.trim().isEmpty()) {
            this.title = "No info";
        } else {
            this.title = title.trim();
        }
    }

    // Get year
    public Integer getYear() {
        return year;
    }

    // Set year
    public void setYear(Integer year) {
        if (year != null && year > 0) {
            this.year = year;
        } else {
            this.year = null;
        }
    }

    // Get genre
    public String getGenre() {
        return genre;
    }

    // Set genre
    public void setGenre(String genre) {
        if (genre == null || genre.trim().isEmpty()) {
            this.genre = "No info";
        } else {
            this.genre = genre.trim();
        }
    }

    // Get poster field
    public String getPosterId() {
        return posterId;
    }

    // Set poster field
    public void setPosterId(String posterId) {
        if (posterId == null || posterId.trim().isEmpty()) {
            this.posterId = "No info";
        } else {
            this.posterId = posterId.trim();
        }
    }
}
