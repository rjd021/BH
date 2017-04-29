package com.boozehound.boozehound12;


import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.ListView;

import com.amigold.fundapter.BindDictionary;
import com.amigold.fundapter.FunDapter;
import com.amigold.fundapter.extractors.StringExtractor;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;
import com.kosalgeek.android.json.JsonConverter;
import com.kosalgeek.genasync12.AsyncResponse;
import com.kosalgeek.genasync12.PostResponseAsyncTask;

import java.util.ArrayList;


public class MapActivity extends AppCompatActivity  implements OnMapReadyCallback, AsyncResponse {
    private ArrayList<GetLocation> locationlist = new ArrayList<GetLocation>();
    String latit;
    String longit;
    private ListView lvMap;
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_map);

        //making AsyncTast reading from web url to get php file
        PostResponseAsyncTask taskRead = new PostResponseAsyncTask(MapActivity.this, this);
        taskRead.execute("http://ryandeal.me/getLocation.php");

        /*Button backArrow = (Button) findViewById(R.id.arrowBack);
        Log.d("maps", "content view set");

        backArrow.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                startActivity(new Intent(MapActivity.this, MainMenu.class));
            }
        }); */


        // Get the SupportMapFragment and request notification
        // when the map is ready to be used.
        SupportMapFragment mapFragment = (SupportMapFragment) getSupportFragmentManager()
                .findFragmentById(R.id.map);
        mapFragment.getMapAsync(this);
    }
    @Override

    public void onMapReady(GoogleMap map) {
        //process query results
        String type = "map load";
        int numBars = locationlist.size();
        for(int i=0; i<numBars; i++) {
            //parse string
            longit = locationlist.get(i).toString();
            latit = locationlist.get(i).toString();

            //add markers
            AddMarker(longit, latit, map, Integer.toString(i));
            //AddMarker("-95.549686", "30.723162", map, "12th street bar");
        }
        //set camera
        map.moveCamera(CameraUpdateFactory.newLatLngZoom(new LatLng(30.723526, -95.550777), 14.0f));
    }


    public void AddMarker(String Long, String Lat, GoogleMap map, String Name){
        Double Longit = Double.parseDouble(Long);
        Double Latit = Double.parseDouble(Lat);
        map.addMarker(new MarkerOptions().position(new LatLng(Latit, Longit)).title(Name));
    }

    public void processFinish(String s) {

        locationlist = new JsonConverter<GetLocation>().toArrayList(s, GetLocation.class);

        BindDictionary<GetLocation> dict = new BindDictionary<GetLocation>();
        /*dict.addStringField(R.id.VenueID, new StringExtractor<Product>(){
            @Override
            public String getStringValue(Product product, int position){
                return "" + product.VenueID;
            }
       });

        /*dict.addStringField(R.id.City, new StringExtractor<Product>() {
            @Override
            public String getStringValue(Product product, int position) {
                return product.City;
            }
        });
        dict.addStringField(R.id.State, new StringExtractor<Product>() {
            @Override
            public String getStringValue(Product product, int position) {
                return product.State;
            }
        });
        dict.addStringField(R.id.ZIP, new StringExtractor<Product>() {
            @Override
            public String getStringValue(Product product, int position) {
                return "" + product.ZIP;
            }
        });*/
        dict.addStringField(R.id.Longitude, new StringExtractor<GetLocation>() {
            @Override
            public String getStringValue(GetLocation location, int position) {
                return location.Longitude;
            }
        });
        dict.addStringField(R.id.Latitude, new StringExtractor<GetLocation>() {
            @Override
            public String getStringValue(GetLocation location, int position) {
                return location.Latitude;
            }
        });
        FunDapter<GetLocation> adapter = new FunDapter<>
                (MapActivity.this, locationlist, R.layout.layout_map, dict);

        lvMap = (ListView) findViewById(R.id.lvMap);
        lvMap.setAdapter(adapter);


    }
}

