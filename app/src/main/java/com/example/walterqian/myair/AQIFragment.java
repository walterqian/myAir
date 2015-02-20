package com.example.walterqian.myair;

import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import org.json.JSONTokener;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * Created by walterqian on 2/18/15.
 */
public class AQIFragment extends Fragment {

    int[] AQI = new int[1];
    TextView textView;
    public AQIFragment(){

    }
    @Override
    public void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);

        setHasOptionsMenu(true);
;


    }
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.aqi_fragment, container, false);
        LocationFragment locationFragment = new LocationFragment();
        double[] latlng = locationFragment.getLocation(getActivity());
        populateAQI(latlng);
        textView = (TextView) rootView.findViewById(R.id.aqi_fragment_textview);

        Log.e("VALUE:", String.valueOf(AQI[0]));
        return rootView;


    }

    public void setView(){
        textView.setText(String.valueOf(AQI[0]));
    }

    public void populateAQI(double[] latlng){
        getAirQuality airQuality = new getAirQuality();
        String latitude = String.valueOf(latlng[0]);
        String longitude = String.valueOf(latlng[1]);
        airQuality.execute(latitude,longitude);
    }

    public class getAirQuality extends AsyncTask<String,Void,int[]> {


        private void parseJson(String jsonData) throws JSONException{

            JSONArray AQIarray = new JSONArray(jsonData);
            // this gets most relevant/first json object
            JSONObject AQIinformation = AQIarray.getJSONObject(0);


            AQI[0] = AQIinformation.getInt("AQI");

        }


        protected int[] doInBackground(String... params) {
            HttpURLConnection urlConnection = null;
            URL url = null;
            JSONObject object = null;
            InputStream inStream = null;

            // EXAMPLE OF API:
            // http://www.airnowapi.org/aq/forecast/latLong/?format=application/json&
            // latitude=34&longitude=-118&date=2015-02-18&distance=10&API_KEY=88668394-4867-4EA3-BB54-C08FE6FC434F

        /*
        format can be application/json,application/xml,text/csv
        latLong or zipcode
        distance is radius in miles
        API_KEY=88668394-4867-4EA3-BB54-C08FE6FC434F
        http://www.airnowapi.org/forecastsbylatlon/query for more information and documentation
         */

            //values for url


            String urlString; //Build this as URL
            final String format = "application/json";
            String latitude = String.valueOf(params[0]);
            String longitude = String.valueOf(params[1]);
            String distance = "10";
            String date = "2015-02-18";
            String zipCode = "90024";
            String API_KEY = "88668394-4867-4EA3-BB54-C08FE6FC434F";
            String jsonAQI;

            if (latitude == "-1000" && longitude == "-1000") {
                urlString = "http://www.airnowapi.org/aq/forecast/zipCode/?format=" + format +
                        "&zipCode=" + zipCode +
                        "&date=" + date +
                        "&distance=" + distance +
                        "&API_KEY=" + API_KEY;
            } else {
                urlString = "http://www.airnowapi.org/aq/forecast/latLong/?format=" + format +
                        "&latitude=" + latitude +
                        "&longitude=" + longitude +
                        "&date=" + date +
                        "&distance=" + distance +
                        "&API_KEY=" + API_KEY;
            }

            try {
                url = new URL(urlString);
                urlConnection = (HttpURLConnection) url.openConnection();
                Log.e("URLSTRING:", url.toString());

                urlConnection.setRequestMethod("GET");
                urlConnection.setDoOutput(true);
                urlConnection.setDoInput(true);

                urlConnection.connect();


                InputStream inputStream = urlConnection.getInputStream();

                if (inputStream == null) {
                    // Nothing to do.
                    Log.e("Error", "NO INPUTSTREAM");
                    AQI[0] = -1;
                    return AQI;
                }

                StringBuilder total = new StringBuilder(inputStream.available()); // initial capacity set to avoid reallocations
                BufferedReader reader = new BufferedReader(new InputStreamReader(inputStream));



                String line;
                while ((line = reader.readLine()) != null) {
                    // Since it's JSON, adding a newline isn't necessary (it won't affect parsing)
                    // But it does make debugging a *lot* easier if you print out the completed
                    // buffer for debugging.
                    total.append(line);

                }
                jsonAQI = total.toString();
                Log.e("JSON String:", jsonAQI);


            } catch (Exception e) {
                Log.e("Error:", "Http Connection");
                return AQI;
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
                    Log.e("Was Here:", "Connection Closed");
                }
            }
            try {
                parseJson(jsonAQI);

            }
            catch(JSONException e){
                Log.e("JSON EXCEPTION",e.getMessage(),e);
                e.printStackTrace();
            }
            // TESTING
            String test = String.valueOf(AQI[0]);
            Log.e("Value of AQI:",test);
            return AQI;
        }

        @Override
        protected void onPostExecute(int[] results) {
            setView();
        }
    }
}
