package com.example.ryan.login;


import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;


/**
 * Created by Ryan on 4/1/2017.
 */

public class ListActivity extends LoginActivity {


    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list);
        Button menuButton = (Button) findViewById(R.id.menu);


        menuButton.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                startActivity(new Intent(ListActivity.this, DrawerActivity.class));
            }
        });



    }
}
