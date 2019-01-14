package com.example.aravind.quiztest;

import android.content.pm.ActivityInfo;
import android.graphics.Color;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.view.WindowManager;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;

public class Question_Answer extends AppCompatActivity
{
    private RadioGroup radioGroup;
    private RadioButton radioButton,correctradiobutton;

    String question_render,OptA,OptB,OptC,OptD,ChosenOption;
    String correctAnswer;

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_question__answer);

        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);
    }

    public void RenderQuestion()
    {
        question_render="Who was the third President of India?";
        OptA="V.V. Giri";
        OptB="Zakir Hussain";
        OptC="Zail Singh";
        OptD="Neelam Sanjeeva Reddy";

        correctAnswer=OptB;

        ((TextView)findViewById(R.id.Question)).setText(question_render);
        ((TextView)findViewById(R.id.Option1)).setText(OptA);
        ((TextView)findViewById(R.id.Option2)).setText(OptB);
        ((TextView)findViewById(R.id.Option3)).setText(OptC);
        ((TextView)findViewById(R.id.Option4)).setText(OptD);
    }


    public void CheckAnswer(View view)
    {
        radioGroup = (RadioGroup)findViewById(R.id.OptionsGroup);
        int SelectedOpt = radioGroup.getCheckedRadioButtonId();
        radioButton=(RadioButton)findViewById(SelectedOpt);

        ChosenOption=radioButton.getText().toString();

        if (ChosenOption.equals(correctAnswer))
        {
            radioButton.setBackgroundColor(Color.parseColor("#399615"));

            ((TextView)findViewById(R.id.Score_value)).setText("10");

            radioButton = (RadioButton) findViewById(R.id.Option1);

            radioButton = (RadioButton) findViewById(R.id.Option2);
            //radioButton.setChecked(false);

            radioButton = (RadioButton) findViewById(R.id.Option3);

            radioButton = (RadioButton) findViewById(R.id.Option4);
        }

        else
        {
            radioButton.setBackgroundColor(Color.parseColor("#f23c32"));
            ((TextView)findViewById(R.id.Score_value)).setText("-5");

            radioButton=(RadioButton)findViewById(R.id.Option2);
            radioButton.setBackgroundColor(Color.parseColor("#399615"));

            radioButton = (RadioButton) findViewById(R.id.Option1);
            //radioButton.setChecked(false);
            //radioButton.setBackgroundColor(Color.parseColor("#d39965"));

            radioButton = (RadioButton) findViewById(R.id.Option2);
            radioButton.setChecked(false);
            //radioButton.setBackgroundColor(Color.parseColor("#d39965"));

            radioButton = (RadioButton) findViewById(R.id.Option3);;

            radioButton = (RadioButton) findViewById(R.id.Option4);
        }

    }

    public void ClearAnswers(View view)
    {
        ((TextView)findViewById(R.id.Score_value)).setText("0");

        radioButton = (RadioButton) findViewById(R.id.Option1);
        radioButton.setChecked(false);
        radioButton.setBackgroundColor(Color.parseColor("#d39965"));

        radioButton = (RadioButton) findViewById(R.id.Option2);
        radioButton.setChecked(false);
        radioButton.setBackgroundColor(Color.parseColor("#d39965"));

        radioButton = (RadioButton) findViewById(R.id.Option3);
        radioButton.setChecked(false);
        radioButton.setBackgroundColor(Color.parseColor("#d39965"));

        radioButton = (RadioButton) findViewById(R.id.Option4);
        radioButton.setChecked(false);
        radioButton.setBackgroundColor(Color.parseColor("#d39965"));
    }
}
