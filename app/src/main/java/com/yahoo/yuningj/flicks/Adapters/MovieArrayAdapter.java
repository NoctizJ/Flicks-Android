package com.yahoo.yuningj.flicks.Adapters;

import android.content.Context;
import android.content.res.Configuration;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;
import com.yahoo.yuningj.flicks.Models.Movie;
import com.yahoo.yuningj.flicks.R;

import java.util.List;

/**
 * Created by yuningj on 11/7/16.
 */

public class MovieArrayAdapter extends ArrayAdapter<Movie> {

    private static class ViewHolder {
        TextView movieTitle;
        TextView movieOverview;
        ImageView movieImage;
    }

    public MovieArrayAdapter(Context context, List<Movie> movies) {
        super(context, android.R.layout.simple_list_item_1, movies);
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        //get the data item for position
        Movie movie = getItem(position);
        //check the existing view being reused
        ViewHolder viewHolder;
        if (convertView == null) {
            viewHolder = new ViewHolder();

            LayoutInflater inflater = LayoutInflater.from(getContext());
            convertView = inflater.inflate(R.layout.item_movie, parent, false);

            viewHolder.movieTitle = (TextView) convertView.findViewById(R.id.tvTitle);
            viewHolder.movieOverview = (TextView) convertView.findViewById(R.id.tvOverview);
            viewHolder.movieImage = (ImageView) convertView.findViewById(R.id.ivMovieImage) ;

            convertView.setTag(viewHolder);
        } else {
            viewHolder = (ViewHolder) convertView.getTag();
        }

//        //find the image view
//        ImageView ivImage = (ImageView) convertView.findViewById(R.id.ivMovieImage);
//        //clear out image from convertView
//        ivImage.setImageResource(0);

//        TextView tvTitle = (TextView) convertView.findViewById(R.id.tvTitle);
//        TextView tvOverview = (TextView) convertView.findViewById(R.id.tvOverview);

        // populate data
        viewHolder.movieTitle.setText(movie.getOriginalTitle());
        viewHolder.movieOverview.setText(movie.getOverview());
        viewHolder.movieImage.setImageResource(0);
        Picasso.with(getContext()).load(movie.getPosterPath()).into(viewHolder.movieImage);


        int orientation = getContext().getResources().getConfiguration().orientation;
        if (orientation == Configuration.ORIENTATION_PORTRAIT) {
            viewHolder.movieTitle.setPadding(45, 10, 0, 0);
        } else if (orientation == Configuration.ORIENTATION_LANDSCAPE) {
            viewHolder.movieTitle.setPadding(100, 40, 0, 30);
            viewHolder.movieOverview.setTextSize(15);
            viewHolder.movieTitle.setTextSize(21);
        }

        return convertView;
    }
}
