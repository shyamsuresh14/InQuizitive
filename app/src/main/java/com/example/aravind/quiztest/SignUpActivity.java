package com.example.aravind.quiztest;

import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.MotionEvent;
import android.view.View;
import android.view.WindowManager;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseAuthException;

public class SignUpActivity extends AppCompatActivity
{
    private float x1,x2;
    static final int MIN_DISTANCE=70;
    String uname,username,phoneno,pwd,pwdconfirm;
    TextView t;

    private FirebaseAuth firebaseAuth;

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_up);
        firebaseAuth=FirebaseAuth.getInstance();

        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);
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
                    if (x1<x2) //Right Swipe
                    {
                        startActivity(new Intent(SignUpActivity.this,LoginActivity.class));
                    }
                }
        }

        return super.onTouchEvent(event);
    }

    public void ValidateSignUpCredentials(View view)
    {
        uname=((EditText)findViewById(R.id.Name)).getText().toString().trim();
        username=((EditText)findViewById(R.id.Email)).getText().toString().trim();
        pwd=((EditText)findViewById(R.id.Password)).getText().toString().trim();
        pwdconfirm=((EditText)findViewById(R.id.ConfirmPassword)).getText().toString().trim();

        t=findViewById(R.id.ErrorMsg);

        if (uname==null || uname.length()==0 || username.length()==0 || username==null || pwd==null || pwd.length()==0 || pwdconfirm.length()==0 || pwdconfirm==null)
        {
            t.setText("Incomplete form.. Please fill it up! ");
        }

        else if (pwd.length()<=6)
            t.setText("Password should atleast be 7 characters");

        else if (!(pwd.equals(pwdconfirm)))
        {
            t.setText("Passwords do not match");
        }


        else
        {
            firebaseAuth.createUserWithEmailAndPassword(username,pwd).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                @Override
                public void onComplete(@NonNull Task<AuthResult> task)
                {
                    if (task.isSuccessful()) {
                        Toast.makeText(SignUpActivity.this, "Registration Successful", Toast.LENGTH_LONG).show();
                        t.setText("Registration Successful");

                        startActivity(new Intent(SignUpActivity.this,LoginActivity.class));
                    }

                    else
                    {
                        FirebaseAuthException e=(FirebaseAuthException)task.getException();
                        Toast.makeText(SignUpActivity.this,"Registration Failed :(",Toast.LENGTH_LONG).show();
                    }
                }
            });
        }
    }
}
