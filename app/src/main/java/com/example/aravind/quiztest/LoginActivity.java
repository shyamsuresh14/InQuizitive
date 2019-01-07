package com.example.aravind.quiztest;

import android.content.Context;
import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.net.ConnectivityManager;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.MotionEvent;
import android.view.View;
import android.view.WindowManager;
import android.view.animation.TranslateAnimation;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;

import java.net.InetAddress;

public class LoginActivity extends AppCompatActivity
{
    String username,userpassword;
    private float x1,x2;
    static final int MIN_DISTANCE=70;
    private FirebaseAuth firebaseAuth;

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);

        if (!isNetworkConnected())
            Toast.makeText(LoginActivity.this,"No internet Connection",Toast.LENGTH_LONG).show();

    }

    private boolean isNetworkConnected()
    {
        ConnectivityManager cm = (ConnectivityManager) getSystemService(Context.CONNECTIVITY_SERVICE);
        return cm.getActiveNetworkInfo() != null;
    }


    public boolean onTouchEvent(MotionEvent event)
    {
        switch (event.getAction())
        {
            case MotionEvent.ACTION_DOWN:
                x1=event.getX();
                break;

            case MotionEvent.ACTION_UP:
                x2=event.getX();
                float deltaX=x2-x1;

                if (Math.abs(deltaX)>MIN_DISTANCE)
                {
                    if (x1>x2) //Left Swipe
                    {
                        startActivity(new Intent(LoginActivity.this,SignUpActivity.class));
                    }
                }
        }

        return super.onTouchEvent(event);
    }


    public void ValidateLoginCredentials(View view)
    {
        username=((EditText)findViewById(R.id.user_email)).getText().toString();
        userpassword=((EditText)findViewById(R.id.user_password)).getText().toString();

        if (username.equals("") || username==null || userpassword.equals("") || userpassword==null)
            ((TextView)findViewById(R.id.ErrorLoginMsg)).setText("Incomplete form..");

        else
        {
            firebaseAuth=FirebaseAuth.getInstance();
            firebaseAuth.signInWithEmailAndPassword(username, userpassword).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                @Override
                public void onComplete(@NonNull Task<AuthResult> task) {
                    if (task.isSuccessful())
                    {
                        Toast.makeText(LoginActivity.this, "Validated", Toast.LENGTH_SHORT).show();
                        startActivity(new Intent(LoginActivity.this, Question_Answer.class));
                    }

                    else
                        {
                        ((TextView)findViewById(R.id.ErrorLoginMsg)).setText("Invalid Login Credentials...");
                    }
                }
            });
        }

    }
}
