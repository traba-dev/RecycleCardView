package com.example.recyclecardview.Adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.recyclecardview.Models.Movie;
import com.example.recyclecardview.R;
import com.squareup.picasso.Picasso;

import java.util.List;

public class MyAD extends RecyclerView.Adapter<MyAD.ViewHolder> {

    private List<Movie> movies;
    private int layout;
    private OnItemClickListener listener;
    private Context context;

    public MyAD (List<Movie> movies, int layout, OnItemClickListener listener) {
        this.movies = movies;
        this.layout = layout;
        this.listener = listener;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(this.layout,parent,false);
        context = parent.getContext();
        ViewHolder vh = new ViewHolder(v);
        return vh;
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        holder.bind(this.movies.get(position),this.listener);
    }

    @Override
    public int getItemCount() {
        return this.movies.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        public TextView name;
        public ImageView imageView_poster;


        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            this.name = (TextView) itemView.findViewById(R.id.text_view_title);
            this.imageView_poster = (ImageView) itemView.findViewById(R.id.image_card_view);
        }

        public void bind(final Movie movie, final OnItemClickListener listener) {
            this.name.setText(movie.getName());
            Picasso.with(context).load(movie.getPoster()).fit().into(imageView_poster);
            //this.imageView_poster.setImageResource(movie.getPoster());

            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    listener.onItemClick(movie,getAdapterPosition());
                }
            });
        }
    }

    public interface OnItemClickListener {
        void onItemClick(Movie movie, int position);
    }
}
