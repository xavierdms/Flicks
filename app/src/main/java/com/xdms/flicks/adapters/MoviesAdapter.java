package com.xdms.flicks.adapters;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.xdms.flicks.R;
import com.xdms.flicks.models.Movie;

import java.util.List;

import static android.support.v4.content.ContextCompat.startActivity;

public class MoviesAdapter extends RecyclerView.Adapter<MoviesAdapter.ViewHolder>
{
    Context context;
    List<Movie> movies;

    public MoviesAdapter(Context context, List<Movie> movies)
    {
        this.context = context;
        this.movies = movies;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType)
    {
        Log.d("smile", "onCreateViewHolder");
        View view = LayoutInflater.from(context).inflate(R.layout.item_movie, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position)
    {
        Log.d("smile", "onBindViewHolder: " + position);
        Movie movie = movies.get(position);
        // bind the movie data into the view holder
        holder.bind(movie);
    }

    @Override
    public int getItemCount() {
        return movies.size();
    }

    class ViewHolder extends RecyclerView.ViewHolder {

        TextView tvTitle;
        TextView tvOverview;
        ImageView ivPoster;
        TextView tvDate;
        Button btnLink;



        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            tvTitle = itemView.findViewById(R.id.tvTitle);
            tvOverview = itemView.findViewById(R.id.tvOverview);
            ivPoster = itemView.findViewById(R.id.ivPoster);
            tvDate = itemView.findViewById(R.id.tvDate);
            btnLink = itemView.findViewById(R.id.btnLink);


        }

        public void bind(final Movie movie)
        {
            tvTitle.setText(movie.getTitle());
            tvOverview.setText(movie.getOverview());
            tvDate.setText(movie.getDate());
            Glide.with(context).load(movie.getPosterPath()).into(ivPoster);
            btnLink.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    String url = movie.getLink();
                    Intent i = new Intent(Intent.ACTION_VIEW);
                    i.setData(Uri.parse(url));
                    startActivity(context, i, null);
                }
            });
        }
    }
}
