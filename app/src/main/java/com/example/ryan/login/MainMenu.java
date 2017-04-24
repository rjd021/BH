package com.boozehound.boozehound12;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.webkit.WebView;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.Toast;

public class MainMenu extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_menu);
        Button mapButton = (Button) findViewById(R.id.location);


        //venue strings
        String[] venues = {"12th Street Bar", "Carbonero Rotisserie", "Chili's", "Mama Juanita's", "Los Pericos", "Los Ponchitos", "Yummy Yummy",};
        ArrayAdapter<String> adapter = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, venues);

        final ListView listView = (ListView) findViewById(R.id.listView);
        listView.setAdapter(adapter);


        // On click of venue String, open Venue page
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            public void onItemClick(AdapterView<?> parent, View view,
                                    int position, long id) {
                Intent appInfo = new Intent(MainMenu.this, VenueActivity.class);
                startActivity(appInfo);
            }
        });

        //Map button - open map page
        mapButton.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                startActivity(new Intent(MainMenu.this, MapActivity.class));
            }
        });





    }




}

