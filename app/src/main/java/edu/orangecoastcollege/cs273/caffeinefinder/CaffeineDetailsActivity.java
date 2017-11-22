package edu.orangecoastcollege.cs273.caffeinefinder;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.TextView;

import com.google.android.gms.maps.CameraUpdate;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.CameraPosition;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;


public class CaffeineDetailsActivity extends AppCompatActivity {

    private GoogleMap mMap;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_caffeine_details);

        SupportMapFragment mapFragment =
                (SupportMapFragment) getSupportFragmentManager().findFragmentById(R.id.locationDetailsMapFragment);
        mapFragment.getMapAsync((OnMapReadyCallback) this);

        TextView locationNameTextView = (TextView) findViewById(R.id.locationDetailsNameTextView);
        TextView locationAddressTextView = (TextView) findViewById(R.id.locationDetailsAddressTextView);
        TextView locationPhoneTextView = (TextView) findViewById(R.id.locationDetailsPhoneTextView);

        Location selectedLocation = getIntent().getExtras().getParcelable("SelectedLocation");

        LatLng locationPosition = new LatLng(selectedLocation.getLatitude(), selectedLocation.getLongitude());

        mMap.addMarker(new MarkerOptions().position(locationPosition).title(selectedLocation.getName()));
        CameraPosition cameraPosition = new CameraPosition.Builder()
                .target(locationPosition)
                .zoom(15.0f)
                .build();
        CameraUpdate cameraUpdate = CameraUpdateFactory.newCameraPosition(cameraPosition);
        mMap.moveCamera(cameraUpdate);

        locationNameTextView.setText(selectedLocation.getName());
        locationAddressTextView.setText(selectedLocation.getFullAddress());
        locationPhoneTextView.setText(selectedLocation.getPhone());

    }
}
