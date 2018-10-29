package id.ac.amikom.katalogfilm;

import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.google.gson.GsonBuilder;
import com.squareup.picasso.Picasso;
import com.squareup.picasso.Target;

import java.util.List;

public class Adapter extends RecyclerView.Adapter<Adapter.MovieHolder> {
    List<Result> results;

    public Adapter(List<Result> results) {
        this.results = results;
    }

    @NonNull
    @Override
    public MovieHolder onCreateViewHolder(@NonNull ViewGroup viewgroup, int i) {
        View view = LayoutInflater.from(viewgroup.getContext())
                .inflate(R.layout.list_film, viewgroup, false);
        return new MovieHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull final MovieHolder holder, final int position) {
        Picasso.with(holder.itemView.getContext())
                .load("https://image.tmdb.org/t/p/w600_and_h900_bestv2/"+results.get(position).getPosterPath())
                .into(holder.Poster);
        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Result data = results.get(position);
                Intent i = new Intent(holder.itemView.getContext(), DetailFilm.class);
                i.putExtra("film", new GsonBuilder().create().toJson(data));
                holder.itemView.getContext().startActivity(i);
            }
        });
    }

    public void setData(List<Result> results){
        this.results = results;
    }

    @Override
    public int getItemCount() {
        return results.size();
    }

    class MovieHolder extends RecyclerView.ViewHolder{
        ImageView Poster;
        public MovieHolder(@NonNull View itemView) {
            super(itemView);
            Poster = (ImageView)itemView.findViewById(R.id.poster);
        }
    }

}
