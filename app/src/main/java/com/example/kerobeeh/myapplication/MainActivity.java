package com.example.kerobeeh.myapplication;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.Response.ErrorListener;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonArrayRequest;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.example.kerobeeh.myapplication.ArrayAdapter.MovieArrayAdapter;
import com.example.kerobeeh.myapplication.DataModel.Datamodelmovie;
import com.google.gson.Gson;
import com.google.gson.JsonObject;
import com.squareup.picasso.Downloader;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

public class MainActivity extends AppCompatActivity {

    Datamodelmovie datamodelmovie[];
    MovieArrayAdapter movieArrayAdapter;
    ListView listView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        listView=(ListView)findViewById(R.id.listmovies);

       // final TextView mTextView = (TextView) findViewById(R.id.text);
       final ImageView mImageView =(ImageView)findViewById(R.id.imageView);
       final Gson gson = new Gson();
        // Instantiate the RequestQueue.
        RequestQueue queue = Volley.newRequestQueue(this);
        String url = "http://api.themoviedb.org/3/discover/movie?api_key=882f561be016ad898b8e88fa110a9536";

        // Request a string response from the provided URL.
        StringRequest stringRequest = new StringRequest(Request.Method.GET, url,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {


                        try {
                            JSONObject jsonObject = new JSONObject(response);
                            JSONArray jsonArray = jsonObject.getJSONArray("results");

                            datamodelmovie=gson.fromJson(jsonArray.toString(),Datamodelmovie[].class);
                            movieArrayAdapter = new MovieArrayAdapter(getApplicationContext(),datamodelmovie);
                            listView.setAdapter(movieArrayAdapter);
                        } catch (JSONException e) {
                            e.printStackTrace();
                        }

                    }



                }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Toast.makeText(getApplicationContext(), " rferer ", Toast.LENGTH_SHORT).show();

                //  mTextView.setText("That didn't work!");
            }
        });
// Add the request to the RequestQueue.
        queue.add(stringRequest);




    }
}