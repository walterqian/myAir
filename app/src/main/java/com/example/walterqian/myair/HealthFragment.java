package com.example.walterqian.myair;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import java.lang.reflect.Array;
import java.util.ArrayList;

/**
 * Created by walterqian on 2/22/15.
 */
public class HealthFragment extends Fragment {
    final String clicked = "Yes";
    final String notClicked = "No";
    TextView textView;
    ArrayList<String> hasBeenClicked = new ArrayList<>();

   public HealthFragment(){

    }
    @Override
    public void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);

        setHasOptionsMenu(true);
    }
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        final View rootView = inflater.inflate(R.layout.health_fragment, container, false);
        // 5 buttons
        hasBeenClicked.add(notClicked);
        hasBeenClicked.add(notClicked);
        hasBeenClicked.add(notClicked);
        hasBeenClicked.add(notClicked);
        hasBeenClicked.add(notClicked);

        Button goodButton = (Button) rootView.findViewById(R.id.good_button);
        Button moderateButton = (Button) rootView.findViewById(R.id.moderate_button);
        Button slightyUnhealthyButton = (Button) rootView.findViewById(R.id.unhealthy_for_sensitive_groups_button);
        Button unhealthyButton = (Button) rootView.findViewById(R.id.unhealthy_button);
        Button veryUnhealthyButton = (Button) rootView.findViewById(R.id.very_unhealthy_button);

        goodButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                textView = (TextView) rootView.findViewById(R.id.good_button);
                changeButtonText(0);
            }
        });
        moderateButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                textView = (TextView) rootView.findViewById(R.id.moderate_button);
                changeButtonText(1);
            }
        });
        slightyUnhealthyButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                textView = (TextView) rootView.findViewById(R.id.unhealthy_for_sensitive_groups_button);
                changeButtonText(2);
            }
        });
        unhealthyButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                textView = (TextView) rootView.findViewById(R.id.unhealthy_button);
                changeButtonText(3);
            }
        });
        veryUnhealthyButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                textView = (TextView) rootView.findViewById(R.id.very_unhealthy_button);
                changeButtonText(4);
            }
        });
        return rootView;


    }

    // positions go from 0-4
    public void changeButtonText(int position){
        if (hasBeenClicked.get(position) == notClicked){
            hasBeenClicked.set(position,clicked);

            textView.setTextSize(12);
            switch (position){
                case 0:
                    textView.setText("None");
                    break;
                case 1:
                    textView.setText("Unusually sensitive people should consider " +
                            "reducing prolonged or heavy outdoor exertion.");
                    break;
                case 2:
                    textView.setText("The following groups should reduce prolonged or heavy outdoor exertion:\n" +
                            "   - People with lung disease, such as asthma\n" +
                            "   - Children and older adults\n" +
                            "   - People who are active outdoors");
                    break;
                case 3:
                    textView.setText("The following groups should avoid prolonged or heavy outdoor exertion:\n" +
                            "   - People with lung disease, such as asthma\n" +
                            "   - Children and older adults\n" +
                            "   - People who are active outdoors\n" +
                            "Everyone else should limit prolonged outdoor exertion.");
                    break;
                default:
                    textView.setText("The following groups should avoid all outdoor exertion:\n" +
                            "   - People with lung disease, such as asthma\n" +
                            "   - Children and older adults\n" +
                            "   - People who are active outdoors\n" +
                            "Everyone else should limit outdoor exertion.");

            }
        }
        else{
            hasBeenClicked.set(position,notClicked);

            textView.setTextSize(20);
            switch (position){

                case 0:
                    textView.setText("Good (0-50)");
                    break;
                case 1:
                    textView.setText("Moderate (51-100)");
                    break;
                case 2:
                    textView.setText("Unhealthy for Sensitive Groups (101-150)");
                    break;
                case 3:
                    textView.setText("Unhealthy (151-200)");
                    break;
                default:
                    textView.setText("Very Unhealthy (201+)");

            }


        }
    }

}
