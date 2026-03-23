package com.example.movies_3.adapter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.movies_3.R;
import com.example.movies_3.model.Movie;

import java.util.List;


// List adapter
public class MovieAdapter extends RecyclerView.Adapter<MovieViewHolder> {

    // Movie data
    private final List<Movie> movieList;

    // Receive data
    public MovieAdapter(List<Movie> movieList) {
        this.movieList = movieList;
    }

    // Create card
    @NonNull
    @Override
    public MovieViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.item_movie, parent, false);
        return new MovieViewHolder(view);
    }

    // Bind data
    @Override
    public void onBindViewHolder(@NonNull MovieViewHolder holder, int position) {
        holder.bind(movieList.get(position));
    }

    // Return count
    @Override
    public int getItemCount() {
        return movieList == null ? 0 : movieList.size();
    }
}
