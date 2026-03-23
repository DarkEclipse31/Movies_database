package com.example.movies_3.util;

import android.content.Context;
import android.content.res.Resources;
import android.util.Log;
import android.widget.Toast;

import org.json.JSONException;

import java.io.IOException;

// Error handler class
public class ErrorHandle {

    // Log tag
    private static final String TAG = "MovieAppError";

    // Show message directly
    public static void showError(Context context, String message) {
        Log.e(TAG, message);
        Toast.makeText(context, message, Toast.LENGTH_LONG).show();
    }

    // Show exception message
    public static void showError(Context context, Exception e) {
        String message = getUserFriendlyMessage(e);
        Log.e(TAG, message, e);
        Toast.makeText(context, message, Toast.LENGTH_LONG).show();
    }

    // Convert to simple message
    public static String getUserFriendlyMessage(Exception e) {
        if (e instanceof Resources.NotFoundException) {
            return "movies.json not found";
        }

        if (e instanceof JSONException) {
            return "Invalid JSON format";
        }

        if (e instanceof IOException) {
            return "Error reading file";
        }

        return "Unexpected error";
    }
}
