package com.example.kerobeeh.myapplication;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.RadioButton;
import android.widget.RadioGroup;
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
    String sort_By_Varible;
   // RadioGroup btnsearch;
    final String ReleaseDatedesc = "release_date.desc";
    final String Popularitydesc = "popularity.desc";
    final String ReleaseDateAsc = "release_date.asc";
    final String PopularityAsc = "popularity.asc";
     RadioGroup radioGroup;
    RadioButton btn1 ;
    RadioButton btn2 ;
    RadioButton btn3 ;
    RadioButton btn4 ;
    Button search;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        listView=(ListView)findViewById(R.id.listmovies);
        radioGroup = (RadioGroup) findViewById(R.id.radioGroup);
         btn1 = (RadioButton) findViewById(R.id.rad1);
         btn2 = (RadioButton) findViewById(R.id.rad2);
         btn3 = (RadioButton) findViewById(R.id.rad3);
         btn4 = (RadioButton) findViewById(R.id.rad4);
         search  = (Button) findViewById(R.id.btnsearch);
        search.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View view) {
                switch (radioGroup.getCheckedRadioButtonId()) {
                    case R.id.rad1:
                        sort_By_Varible = "popularity.desc";
                        break;
                    case R.id.rad2:
                        sort_By_Varible = "release_date.desc";
                        break;
                    case R.id.rad3:
                        sort_By_Varible = "release_date.asc";
                        break;
                    case R.id.rad4:
                        sort_By_Varible = "popularity.asc";
                        break;
                }
                String url = "http://api.themoviedb.org/3/discover/movie?api_key=ab71bd22b9e87623e779800766e9ef81&sort_by="+ sort_By_Varible ;

                executeWebService(url);

            }



        });
    }

    private void executeWebService(String url) {

       // final TextView mTextView = (TextView) findViewById(R.id.text);
       final ImageView mImageView =(ImageView)findViewById(R.id.imageView);
       final Gson gson = new Gson();
        // Instantiate the RequestQueue.
        RequestQueue queue = Volley.newRequestQueue(this);
        String s = "http://api.themoviedb.org/3/discover/movie?api_key=882f561be016ad898b8e88fa110a9536";

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
                Toast.makeText(getApplicationContext(), error.getMessage(), Toast.LENGTH_SHORT).show();

                //  mTextView.setText("That didn't work!");
            }
        });
// Add the request to the RequestQueue.
        queue.add(stringRequest);




    }
}