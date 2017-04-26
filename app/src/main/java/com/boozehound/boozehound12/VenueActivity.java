package com.boozehound.boozehound12;

import android.content.Intent;
import android.icu.text.SimpleDateFormat;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.RequiresApi;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Spinner;

import java.util.Calendar;
import java.util.Date;


/**
 * Created by Ryan on 4/1/2017.
 */

public class VenueActivity extends AppCompatActivity {


    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_venue);

        //btn to go to map view
        Button mapButton = (Button) findViewById(R.id.location);

        //btn to go back to list view
        Button backArrow = (Button) findViewById(R.id.arrowBack);

        //btn to call venue
        Button call = (Button) findViewById(R.id.phone);


        //Specials for venue
        String[] venues = {"$2 Domestic Bottles", "$3 Wells", "Import/ Prem. Bottles", "$4 Calls"};
        ArrayAdapter<String> adapter = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, venues);

        ListView listView = (ListView) findViewById(R.id.listView);
        listView.setAdapter(adapter);


        //day of the week spinner
        Spinner spinner = (Spinner) findViewById(R.id.dayOfThWeek);
        // Create an ArrayAdapter using the string array and a default spinner layout
        ArrayAdapter<CharSequence> spinadapter = ArrayAdapter.createFromResource(this,

                //*****days_array in values/strings******
                R.array.days_array, android.R.layout.simple_spinner_item);


        // Specify the layout to use when the list of choices appears
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
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

    public String getCurrentDay(){

        String daysArray[] = {"Sunday","Monday","Tuesday", "Wednesday","Thursday","Friday", "Saturday"};

        Calendar calendar = Calendar.getInstance();
        int day = calendar.get(Calendar.DAY_OF_WEEK);

        return daysArray[day];

    }


    public void OnVenueClick(View view) {
        String type = "venue click";
        BackgroundWorker backgroundWorker = new BackgroundWorker(this);
        backgroundWorker.execute(type);
    }

    }

