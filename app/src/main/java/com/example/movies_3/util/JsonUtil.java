package com.example.movies_3.util;

import android.content.Context;
import android.util.Log;

import com.example.movies_3.model.Movie;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;

// JSON utility class
public class JsonUtil {

    // Log tag
    private static final String TAG = "JsonUtil";

    // Load movie list
    public static ArrayList<Movie> loadMovies(Context context, int resourceId)
            throws IOException, JSONException {

        // Read file
        String jsonContent = readJsonFile(context, resourceId);

        // Convert to array
        JSONArray jsonArray = new JSONArray(jsonContent);

        // Result list
        ArrayList<Movie> movies = new ArrayList<>();

        // Process each item
        for (int i = 0; i < jsonArray.length(); i++) {
            try {

                // Get one object
                JSONObject movieObject = jsonArray.optJSONObject(i);


                // Invalid object
                if (movieObject == null) {
                    Log.e(TAG, "Bad object: " + i);
                    movies.add(new Movie());
                    continue;
                }

                // Get title safely
                String title = getSafeString(movieObject, "title", "No info");

                // Get year safely
                Integer year = getSafeYear(movieObject, "year");

                // Get genre safely
                String genre = getSafeString(movieObject, "genre", "No info");


                // Get poster safely
                String posterId = getSafeString(movieObject, "poster", "No info");

                // Create movie object
                movies.add(new Movie(title, year, genre, posterId));

            } catch (Exception e) {
                // One item failed
                Log.e(TAG, "Parse error: " + i, e);
                movies.add(new Movie());
            }
        }

        // Return list
        return movies;
    }

    // Read JSON text
    private static String readJsonFile(Context context, int resourceId) throws IOException {
        // Build text
        StringBuilder stringBuilder = new StringBuilder();

        try (InputStream inputStream = context.getResources().openRawResource(resourceId);
             BufferedReader reader = new BufferedReader(new InputStreamReader(inputStream))) {

            // Current line
            String line;

            // Read line by line
            while ((line = reader.readLine()) != null) {
                stringBuilder.append(line);
            }

        } catch (IOException e) {
            // Log error
            Log.e(TAG, "Read error", e);
            throw e;
        }

        // Return full text
        return stringBuilder.toString();
    }

    // Get string safely
    private static String getSafeString(JSONObject object, String key, String defaultValue) {
        // Field missing
        if (!object.has(key) || object.isNull(key)) {
            return defaultValue;
        }

        // Get raw value
        Object value = object.opt(key);

        // Is string
        if (value instanceof String) {
            String text = ((String) value).trim();

            // Non-empty usable
            if (!text.isEmpty()) {
                return text;
            }
        }

        // Return default
        return defaultValue;
    }

    // Get year safely
    private static Integer getSafeYear(JSONObject object, String key) {
        // Field missing
        if (!object.has(key) || object.isNull(key)) {
            return null;
        }

        // Get raw value
        Object value = object.opt(key);

        // Integer year
        if (value instanceof Integer) {
            int year = (Integer) value;
            return year > 0 ? year : null;
        }

        // Long year
        if (value instanceof Long) {
            long year = (Long) value;
            if (year > 0 && year <= Integer.MAX_VALUE) {
                return (int) year;
            }
            return null;
        }

        // Double year
        if (value instanceof Double) {
            double year = (Double) value;
            if (year > 0 && year == Math.floor(year)) {
                return (int) year;
            }
            return null;
        }

        // String year
        if (value instanceof String) {
            String text = ((String) value).trim();
            try {
                int year = Integer.parseInt(text);
                return year > 0 ? year : null;
            } catch (NumberFormatException e) {
                Log.e(TAG, "Bad year: " + text, e);
                return null;
            }
        }

        // Other invalid
        return null;
    }
}
