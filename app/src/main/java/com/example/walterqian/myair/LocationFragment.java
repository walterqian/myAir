package com.example.walterqian.myair;

import android.app.Fragment;
import android.content.Context;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.os.Bundle;
import android.util.Log;

import org.json.JSONObject;
import org.json.JSONTokener;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

/**
 * Created by walterqian on 2/18/15.
 */
public class LocationFragment extends Fragment{

    LocationManager locationManager;
    Location location;
    // returns zip code based on default location

    public LocationFragment(){
    }



    LocationListener locationListener = new LocationListener() {
        @Override
        public void onLocationChanged(Location location) {
            getLocation(getActivity());
        }

        public void onStatusChanged(String provider, int status, Bundle extras) {}

        public void onProviderEnabled(String provider) {}

        public void onProviderDisabled(String provider) {}
    };

    public double[] getLocation(Context context){

        Globals globals = new Globals();
        locationManager = (LocationManager) context.getSystemService(Context.LOCATION_SERVICE);
        locationManager.requestLocationUpdates(LocationManager.GPS_PROVIDER, 1000, 10, locationListener);

        location = locationManager.getLastKnownLocation(locationManager.GPS_PROVIDER);

        double[] latlng = new double[2];
        int zip;
        if (location!=null){
            latlng[0] = location.getLatitude();
            latlng[1] = location.getLongitude();
        }

        else {
            // UNABLE TO GET LOCATION
            latlng[0] = globals.getLocErrorDouble();
            latlng[1] = globals.getLocErrorDouble();
        }


        return latlng;
    }



    // NO LONGER NECESSARY
    /*
    private int reverseGeocoding(double latitude, double longitude){
        HttpURLConnection urlConnection = null;
        URL url = null;
        JSONObject object = null;
        InputStream inStream = null;

        // String API_KEY = "AIzaSyDWCEwmaXxv7lrTimFem48rs38UYwh0_Lk"; no longer being used
        // Example of proper URL:https://maps.googleapis.com/maps/api/geocode/json?latlng=40.714224,-73.961452&key=API_KEY
        // http://ws.geonames.org/findNearbyPostalCodesJSON?formatted=true&lat=34.5&lng=-118&username=walterqian
        String urlString = "http://ws.geonames.org/findNearbyPostalCodesJSON?formatted=true&lat="+
                String.valueOf(latitude) + "&lng=" +
                String.valueOf(longitude) + "&username=walterqian";


        try {
            url = new URL(urlString);
            urlConnection = (HttpURLConnection) url.openConnection();
            Log.e("URLSTRING:",url.toString());
            urlConnection.setRequestMethod("GET");
            urlConnection.setDoOutput(true);
            urlConnection.setDoInput(true);
            urlConnection.connect();
            inStream = urlConnection.getInputStream();
            BufferedReader bReader = new BufferedReader(new InputStreamReader(inStream));
            String temp, response = "";
            while ((temp = bReader.readLine()) != null) {
                response += temp;
                Log.e("Response",temp);
            }
            object = (JSONObject) new JSONTokener(response).nextValue();
        } catch (Exception e) {

            Log.e("Error:","Http Connection");
        } finally {
            if (inStream != null) {
                try {
                    // this will close the bReader as well
                    inStream.close();
                } catch (IOException ignored) {
                }
            }
            if (urlConnection != null) {
                urlConnection.disconnect();
            }
        }
        return 1;
    }
    */




}
