package com.example.ryan.login;


import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.Spinner;


/**
 * Created by Ryan on 4/1/2017.
 */

public class VenueActivity extends LoginActivity {


    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_venue);

        Spinner spinner = (Spinner) findViewById(R.id.dayOfThWeek);
// Create an ArrayAdapter using the string array and a default spinner layout
        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(this,
                R.array.days_array, android.R.layout.simple_spinner_item);
// Specify the layout to use when the list of choices appears
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
// Apply the adapter to the spinner
        spinner.setAdapter(adapter);



    }
}
