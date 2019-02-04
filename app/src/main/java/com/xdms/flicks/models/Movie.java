package com.xdms.flicks.models;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

public class Movie
{
    String posterPath;
    String title;
    String overview;
    String release_date;
    String movie_id;
    String link;



    public Movie(JSONObject jsonObject) throws JSONException {
        posterPath = jsonObject.getString("poster_path");
        title = jsonObject.getString("title");
        overview = jsonObject.getString("overview");
        release_date = jsonObject.getString("release_date");
        movie_id = jsonObject.getString("id");
        link = toString().format("https://www.themoviedb.org/movie/%s", movie_id);

    }

    public static List<Movie> fromJsonArray(JSONArray movieJsonArray) throws JSONException {
        List<Movie> movies = new ArrayList<>();
        for (int i = 0; i < movieJsonArray.length(); i++)
        {
            movies.add(new Movie(movieJsonArray.getJSONObject(i)));
        }

        return movies;
    }

    public String getPosterPath() {
        return String.format("https://image.tmdb.org/t/p/w342/%s", posterPath);
    }

    public String getLink() {
        return link;

    }

    public String getTitle() {
        return title;
    }

    public String getOverview() {
        return overview;
    }

    public String getDate() { return release_date;
    }
}
