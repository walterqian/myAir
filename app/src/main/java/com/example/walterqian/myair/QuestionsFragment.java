package com.example.walterqian.myair;


import android.support.v4.app.Fragment;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import java.util.ArrayList;

/**
 * Created by walterqian on 2/19/15.
 */
public class QuestionsFragment extends Fragment {

    int currentQuestion = 0;
    ArrayList<String> questions = new ArrayList<String>();
    TextView textView;

    public QuestionsFragment(){

    }
    @Override
    public void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);

        // added questions
        questions.add("Have you or will you engage in outdoor activity today?");
        questions.add("Did you or a household member have an asthma attack today?");
        questions.add("Did you talk to someone about air quality today?");
        questions.add("Did you seek additional information about air quality today?");
        questions.add("Thanks for your answers!");


        setHasOptionsMenu(true);



    }
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.survey_fragment, container, false);
        textView = (TextView) rootView.findViewById(R.id.survey_fragment_question);
        textView.setText(questions.get(0));

        Button yesButton = (Button) rootView.findViewById(R.id.survey_fragment_yes);
        Button noButton = (Button) rootView.findViewById(R.id.survey_fragment_no);

        yesButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                submitYes();
            }
        });

        noButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                submitNo();
            }
        });

        return rootView;


    }

    // ADD CODE FOR CHANGING DATA
    private void submitYes(){
        Log.e("Current Question",String.valueOf(currentQuestion));
        switch (currentQuestion) {
            case 0:
                textView.setText(questions.get(1));
                break;
            case 1:
                textView.setText(questions.get(2));
                break;
            case 2:
                textView.setText(questions.get(3));
                break;
            default:
                textView.setText(questions.get(4));
        }
        currentQuestion++;
    }

    private void submitNo(){
        switch (currentQuestion) {
            case 0:
                textView.setText(questions.get(1));
                break;
            case 1:
                textView.setText(questions.get(2));
                break;
            case 2:
                textView.setText(questions.get(3));
                break;
            default:
                textView.setText(questions.get(4));
                break;
        }
        currentQuestion++;
    }
}
