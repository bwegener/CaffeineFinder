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

/**
 * The <code>CaffeineDetailsActivity</code> gets the location's map from googleMaps
 * and the name, address, and phone to inflate the layout with the location's information.
 *
 * @author Brian Wegener
 * @version 1.0
 *
 * Created by Brian Wegener 11/21/2017
 */
public class CaffeineDetailsActivity extends AppCompatActivity implements OnMapReadyCallback {


    TextView locationNameTextView;
    TextView locationAddressTextView;
    TextView locationPhoneTextView;

    private GoogleMap mMap;

    /**
     * The onCreate conectes the mapFragment with the location.
     * @param savedInstanceState
     */
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_caffeine_details);

        SupportMapFragment mapFragment =
                (SupportMapFragment) getSupportFragmentManager().findFragmentById(R.id.locationDetailsMapFragment);
        mapFragment.getMapAsync((OnMapReadyCallback) this);

        locationNameTextView = (TextView) findViewById(R.id.locationDetailsNameTextView);
        locationAddressTextView = (TextView) findViewById(R.id.locationDetailsAddressTextView);
        locationPhoneTextView = (TextView) findViewById(R.id.locationDetailsPhoneTextView);
    }

    /**
     * The <code>onMapReady</code> gets the position of the location and shows it in the googleMaps.
     * @param googleMap
     */
    @Override
    public void onMapReady(GoogleMap googleMap) {

        mMap = googleMap;

        Location selectedLocation = getIntent().getExtras().getParcelable("SelectedLocation");

        LatLng locationPosition = new LatLng(selectedLocation.getLatitude(), selectedLocation.getLongitude());

        mMap.addMarker(new MarkerOptions().position(locationPosition).title(selectedLocation.getName()));
        CameraPosition cameraPosition = new CameraPosition.Builder()
                .target(locationPosition)
                .zoom(18.0f)
                .build();
        CameraUpdate cameraUpdate = CameraUpdateFactory.newCameraPosition(cameraPosition);
        mMap.moveCamera(cameraUpdate);

        locationNameTextView.setText(selectedLocation.getName());
        locationAddressTextView.setText(selectedLocation.getFullAddress());
        locationPhoneTextView.setText(selectedLocation.getPhone());
    }
}
