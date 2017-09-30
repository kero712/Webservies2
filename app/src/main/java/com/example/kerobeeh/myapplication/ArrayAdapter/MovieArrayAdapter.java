package com.example.kerobeeh.myapplication.ArrayAdapter;

import android.content.Context;
import android.support.annotation.LayoutRes;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.kerobeeh.myapplication.DataModel.Datamodelmovie;
import com.example.kerobeeh.myapplication.R;
import com.squareup.picasso.Picasso;


public class MovieArrayAdapter extends ArrayAdapter<Datamodelmovie> {


    public MovieArrayAdapter(@NonNull Context context, @NonNull Datamodelmovie[] objects) {
        super(context, 0, objects);
    }


    @Override
    public View getView(int position, View convertView, ViewGroup parent) {

        // Check if an existing view is being reused, otherwise inflate the view
        if (convertView == null) {
            convertView = LayoutInflater.from(getContext()).inflate(R.layout.movie_rawitem,parent,false);
        }

        // Get the data item for this position
        Datamodelmovie currentmovie = getItem(position);


        ImageView imageView =(ImageView)convertView.findViewById(R.id.imageView);
        TextView txttitle = (TextView)convertView.findViewById(R.id.txttitle);
        TextView txtoverview =(TextView)convertView.findViewById(R.id.txtoveriew);

        txttitle.setText(currentmovie.getTitle());
        txtoverview.setText(currentmovie.getOverview());

        String url ="http://image.tmdb.org/t/p/w185"+currentmovie.getPosterPath();
        Picasso.with(getContext()).load(url).into(imageView);



        // Return the completed view to render on screen
        return convertView;
    }
}
