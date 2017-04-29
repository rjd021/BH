package com.boozehound.boozehound12;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.Spinner;

import com.amigold.fundapter.BindDictionary;
import com.amigold.fundapter.FunDapter;
import com.amigold.fundapter.extractors.StringExtractor;
import com.kosalgeek.android.json.JsonConverter;
import com.kosalgeek.genasync12.AsyncResponse;
import com.kosalgeek.genasync12.PostResponseAsyncTask;

import java.util.ArrayList;


/**
 * Created by Ryan on 4/1/2017.
 */

public class VenueActivity extends AppCompatActivity implements AsyncResponse {
    private ArrayList<GetVenue> venuelist;
    private ListView lvVenue;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_venue);
        //btn to go to map view
        Button mapButton = (Button) findViewById(R.id.location);

        //btn to go back to list view
        Button backArrow = (Button) findViewById(R.id.arrowBack);

        //btn to call venue
        Button call = (Button) findViewById(R.id.phone);


        PostResponseAsyncTask taskRead = new PostResponseAsyncTask(VenueActivity.this, this);
        taskRead.execute("http://ryandeal.me/getVenue.php");

        //Map button - open map page
        mapButton.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                startActivity(new Intent(VenueActivity.this, MapActivity.class));
            }
        });
        //day of the week spinner
        Spinner spinner = (Spinner) findViewById(R.id.dayOfThWeek);
        // Create an ArrayAdapter using the string array and a default spinner layout
        ArrayAdapter<CharSequence> spinadapter = ArrayAdapter.createFromResource(this,

                //*****days_array in values/strings******
                R.array.days_array, android.R.layout.simple_spinner_item);


        // Specify the layout to use when the list of choices appears
        spinadapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        // Apply the adapter to the spinner
        spinner.setAdapter(spinadapter);

        //Map button - open map page
        mapButton.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                startActivity(new Intent(VenueActivity.this, MapActivity.class));
            }
        });

        //back arrow btn - open main page
        backArrow.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                startActivity(new Intent(VenueActivity.this, MainMenu.class));
            }
        });

        //press btn to call venue
        call.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent callIntent = new Intent(Intent.ACTION_DIAL);
                callIntent.setData(Uri.parse("tel:8179753682"));
                startActivity(callIntent);
            }
        });

    }

    @Override
    public void processFinish(String s) {

        venuelist = new JsonConverter<GetVenue>().toArrayList(s, GetVenue.class);

        BindDictionary<GetVenue> dict = new BindDictionary<GetVenue>();
    /*    dict.addStringField(R.id.VenueID, new StringExtractor<GetVenue>(){
            @Override
            public String getStringValue(GetVenue venue, int position){
                return "" + venue.VenueID;
            }
       });*/

        dict.addStringField(R.id.VenueName, new StringExtractor<GetVenue>() {
            @Override
            public String getStringValue(GetVenue venue, int position) {
                return venue.VenueName;
            }
        });
      /*  dict.addStringField(R.id.Phone, new StringExtractor<GetVenue>() {
            @Override
            public String getStringValue(GetVenue venue, int position) {
                return venue.Phone;
            }
        });*/


        FunDapter<GetVenue> adapter = new FunDapter<>
                (VenueActivity.this, venuelist, R.layout.layout_main, dict);

        lvVenue = (ListView) findViewById(R.id.lvVenue);
        lvVenue.setAdapter(adapter);


    }
}
