package com.example.movies_3;

import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.movies_3.adapter.MovieAdapter;
import com.example.movies_3.model.Movie;
import com.example.movies_3.util.ErrorHandle;
import com.example.movies_3.util.JsonUtil;

import java.util.ArrayList;

// Main controller
public class MainActivity extends AppCompatActivity {

    // RecyclerView
    private RecyclerView recyclerViewMovies;

    // Adapter
    private MovieAdapter movieAdapter;

    // Movie data
    private ArrayList<Movie> movieList;

    // Screen starts
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        // Load layout
        setContentView(R.layout.activity_main);

        // Find list
        recyclerViewMovies = findViewById(R.id.recyclerViewMovies);

        // Set layout manager
        recyclerViewMovies.setLayoutManager(new LinearLayoutManager(this));

        // Create empty list
        movieList = new ArrayList<>();

        // Create adapter
        movieAdapter = new MovieAdapter(movieList);

        // Attach adapter
        recyclerViewMovies.setAdapter(movieAdapter);

        // Load movies
        loadMovies();
    }

    // Read movies
    private void loadMovies() {
        try {
            // Clear old data
            movieList.clear();

            // Load JSON
            movieList.addAll(JsonUtil.loadMovies(this, R.raw.movies));

            // Refresh screen
            movieAdapter.notifyDataSetChanged();

            // Empty list warning
            if (movieList.isEmpty()) {
                ErrorHandle.showError(this, "No movies found");
            }

        } catch (Exception e) {
            // Unified error handling
            ErrorHandle.showError(this, e);
        }
    }
}