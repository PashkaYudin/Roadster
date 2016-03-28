package com.example.broso.roadster;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Toast;

import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.Marker;
import com.google.android.gms.maps.model.MarkerOptions;

public class MainActivity extends AppCompatActivity implements OnMapReadyCallback {

    Dummy data = new Dummy();
    double latitude;
    double longitude;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        SupportMapFragment mapFragment = (SupportMapFragment) getSupportFragmentManager()
                .findFragmentById(R.id.map);
        mapFragment.getMapAsync(this);

        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
//                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
//                        .setAction("Action", null).show();
                newEvent();

            }
        });

//        Bundle extras = getIntent().getExtras();
//        if (extras != null) {
//            getData(extras);
//        }

    }
//    private void getData(Bundle extras) {
//        double newLat = extras.getDouble("newLat");
//        double newLong = extras.getDouble("newLong");
//
//        String coords = new String(newLat +","+newLong);//
//        Toast.makeText(this, coords, Toast.LENGTH_SHORT).show();
////        LatLng coords = new LatLng(newLat, newLong);
////        map.markerMaker(coords);
//    }

    @Override
    public void onMapReady(GoogleMap map) {

        for (int i = 0; i < 3; i++)
        {
            LatLng sydney = new LatLng(data.lat[i], data.lon[i]);
            map.addMarker(new MarkerOptions().position(sydney).title("ДТП").snippet(data.addresses[i]));
        }

        map.setOnMarkerClickListener(new GoogleMap.OnMarkerClickListener() {
            @Override
            public boolean onMarkerClick(Marker marker) {
//                Toast.makeText(getApplicationContext(), "Ты нажал на маркер", Toast.LENGTH_SHORT).show();
//                marker.showInfoWindow();
                String place = marker.getSnippet();
                Intent details = new Intent(getApplicationContext(), DetailInfo.class);
                details.putExtra("address", place);
                startActivityForResult(details, 1);
                return true;
            }
        });
    }

    public void newEvent() {
        Intent newEvent = new Intent(this, NewEvent.class);
        startActivityForResult(newEvent, 1);
    }



    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        if (requestCode == 1 && resultCode == Activity.RESULT_OK) {
            latitude = data.getDoubleExtra("latitude", 0);
            longitude = data.getDoubleExtra("longitude", 0);

            Toast.makeText(this, latitude+" - "+longitude, Toast.LENGTH_SHORT).show();
//            MapFragment map = new MapFragment();
//            LatLng coords = new LatLng(latitude, longitude);
//            map.markerMaker(coords);
        }
    }



    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

}
