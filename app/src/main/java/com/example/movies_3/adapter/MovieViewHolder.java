package com.example.movies_3.adapter;

import android.view.View;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.movies_3.R;
import com.example.movies_3.model.Movie;

// Bind card views
public class MovieViewHolder extends RecyclerView.ViewHolder {

    // Title view
    private final TextView textViewTitle;

    // Year view
    private final TextView textViewYear;

    // Poster view
    private final TextView textViewPosterId;

    // Genre view
    private final TextView textViewGenre;

    // Find views
    public MovieViewHolder(@NonNull View itemView) {
        super(itemView);

        textViewTitle = itemView.findViewById(R.id.textViewTitle);
        textViewYear = itemView.findViewById(R.id.textViewYear);
        textViewPosterId = itemView.findViewById(R.id.textViewPosterId);
        textViewGenre = itemView.findViewById(R.id.textViewGenre);
    }

    // Bind one movie
    public void bind(Movie movie) {
        // Null fallback
        if (movie == null) {
            textViewTitle.setText("No info");
            textViewYear.setText("No info");
            textViewPosterId.setText("Poster id: No info");
            textViewGenre.setText("Genre: No info");
            return;
        }

        // Show title
        textViewTitle.setText(movie.getTitle());

        // Show year
        if (movie.getYear() == null) {
            textViewYear.setText("No info");
        } else {
            textViewYear.setText(String.valueOf(movie.getYear()));
        }

        // 显示海报字段
        // Show poster field
        textViewPosterId.setText("Poster id: " + movie.getPosterId());

        // 显示类型
        // Show genre
        textViewGenre.setText("Genre: " + movie.getGenre());
    }
}
