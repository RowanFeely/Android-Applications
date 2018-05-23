package edu.monash.fit2081.countryinfo;

import android.content.Intent;
import android.location.Address;
import android.location.Geocoder;
import android.os.Bundle;
import android.support.design.widget.Snackbar;
//import android.support.v4.app.FragmentActivity;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Toast;

import com.akexorcist.googledirection.GoogleDirection;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.UiSettings;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.Marker;
import com.google.android.gms.maps.model.MarkerOptions;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

public class MapsActivity extends AppCompatActivity implements
        GoogleMap.OnMarkerClickListener, OnMapReadyCallback {

    private Geocoder geocoder;
    private UiSettings mUiSettings;

    SupportMapFragment mapFragment;

    private static final LatLng PERTH = new LatLng(-31.952854, 115.857342);
    private static final LatLng SYDNEY = new LatLng(-33.87365, 151.20689);
    private static final LatLng BRISBANE = new LatLng(-27.47093, 153.0235);
    private static final LatLng ADELAIDE = new LatLng(-34.928889, 138.601111);
    private static final LatLng CANBERRA = new LatLng(-35.3075, 149.124417);
    private static final LatLng DARWIN = new LatLng(-12.45, 130.833333);

    private Marker mPerth;
    private Marker mSydney;
    private Marker mBrisbane;
    private Marker mAdelaide;
    private Marker mCanberra;
    private Marker mDarwin;

    String serverKey = "AIzaSyCKay-wFLpWT9e3SD_64mPg5LmzgRWX2D0";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_maps);

        getSupportActionBar().setTitle(R.string.title_activity_maps);

        geocoder = new Geocoder(this, Locale.getDefault());

        mapFragment = (SupportMapFragment) getSupportFragmentManager().findFragmentById(R.id.map);
        mapFragment.getMapAsync(this);

    }

    /**
     * Manipulates the map once available.
     * This callback is triggered when the map is ready to be used.
     * This is where we can add markers or lines, add listeners or move the camera.
     * If Google Play services is not installed on the device, the user will be prompted to install
     * it inside the SupportMapFragment. This method will only be triggered once the user has
     * installed Google Play services and returned to the app.
     */
    @Override
    public void onMapReady(GoogleMap googleMap) {
        mUiSettings = googleMap.getUiSettings();
        mUiSettings.setZoomGesturesEnabled(true);
        mUiSettings.setZoomControlsEnabled(true);
        mUiSettings.setMyLocationButtonEnabled(true);

        mPerth = googleMap.addMarker(new MarkerOptions()
                .position(PERTH)
                .snippet("The capital city of Western Australia")
                .title("Perth"));
        mPerth.setTag(0);
        mSydney = googleMap.addMarker(new MarkerOptions()
                .position(SYDNEY)
                .snippet("The capital city of NSW")
                .title("Sydney"));
        mSydney.setTag(0);
        mBrisbane = googleMap.addMarker(new MarkerOptions()
                .position(BRISBANE)
                .snippet("The capital city of Queensland")
                .title("Brisbane"));
        mBrisbane.setTag(0);
        mCanberra = googleMap.addMarker(new MarkerOptions()
                .position(CANBERRA)
                .snippet("The capital city of Australia")
                .title("Canberra"));
        mCanberra.setTag(0);
        mDarwin = googleMap.addMarker(new MarkerOptions()
                .position(DARWIN)
                .snippet("The capital city of the Northern Territory")
                .title("Darwin"));
        mDarwin.setTag(0);
        mAdelaide = googleMap.addMarker(new MarkerOptions()
                .position(DARWIN)
                .snippet("The capital city of South Australia")
                .title("Adelaide"));
        mAdelaide.setTag(0);
        googleMap.setMapType(GoogleMap.MAP_TYPE_HYBRID);
        googleMap.getUiSettings().setMapToolbarEnabled(true);
        LatLng melbourne = new LatLng(-37.814, 144.96332);
        googleMap.addMarker(new MarkerOptions().position(melbourne).title
                ("Melbourne"));
        googleMap.moveCamera(CameraUpdateFactory.newLatLng(melbourne));
        googleMap.setOnMarkerClickListener((GoogleMap.OnMarkerClickListener) this);
        googleMap.setOnMapClickListener(new GoogleMap.OnMapClickListener() {

            @Override
            public void onMapClick(LatLng point) {
                //save current location
                String msg;
                boolean actionFlag;
                String selectedCountry = "";


                List<Address> addresses = new ArrayList<>();
                try {
                    //The results of getFromLocation are a best guess and are not guaranteed to be meaningful or correct.
                    // It may be useful to call this method from a thread separate from your primary UI thread.
                    addresses = geocoder.getFromLocation(point.latitude, point.longitude, 1); //last param means only return the first address object
                } catch (IOException e) {
                    e.printStackTrace();
                }

                if (addresses.size() == 0) {
                    msg = "No Country at this location!! Sorry";
                    actionFlag = false;
                } else {
                    android.location.Address address = addresses.get(0);
                    selectedCountry = address.getCountryName();
                    msg = "Do you want more details about " + address.getCountryName() + "?";
                    actionFlag = true;
                }

                Snackbar.make(mapFragment.getView(), msg, Snackbar.LENGTH_LONG).setAction("Details", (actionFlag) ? (new ActionOnClickListener(selectedCountry)) : null).show();
            }
        });
    }

    @Override
    public boolean onMarkerClick(Marker marker) {
        // grab and store Lat Lng from current location on click
        // directions API to determine distance between the two points
        Toast.makeText(MapsActivity.this, "You are __ from this location.",
                Toast.LENGTH_SHORT)
                .show();
        return false;
    }

    //Custom onclicklistener to accept 'selectedcountry' as parameter
    public class ActionOnClickListener implements View.OnClickListener {

        String country;

        public ActionOnClickListener(String country) {
            this.country = country; //this refers to the nested class's instance not the an instance of the enclosing class
        }

        @Override
        public void onClick(View v) {
            Intent intent = new Intent(mapFragment.getContext(), CountryDetails.class);
            intent.putExtra("country", country);
            startActivity(intent);
        }
    }
}