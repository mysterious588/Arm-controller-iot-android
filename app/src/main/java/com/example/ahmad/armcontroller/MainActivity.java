package com.example.ahmad.armcontroller;

import android.os.Bundle;
import android.os.CountDownTimer;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;


public class MainActivity extends AppCompatActivity {
    Button button;
    TextView txt;
    TextView txt2;
    DatabaseReference mMode = FirebaseDatabase.getInstance().getReference().child("mode");

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        button = findViewById(R.id.uselessButton);
        txt = findViewById(R.id.firstUselessTextView);
        txt2 = findViewById(R.id.secondUselessTextView);
        mMode.setValue(1);
        new CountDownTimer(5000, 1000) {
            public void onFinish() {
                txt.setVisibility(View.VISIBLE);
                // When timer is finished
                // Execute your code here
            }

            public void onTick(long millisUntilFinished) {
                // millisUntilFinished    The amount of time until finished.
            }
        }.start();
        new CountDownTimer(7000, 1000) {
            public void onFinish() {
                txt2.setVisibility(View.VISIBLE);
                button.setVisibility(View.VISIBLE);
                button.setClickable(true);
            }

            public void onTick(long millisUntilFinished) {
                // millisUntilFinished    The amount of time until finished.
            }
        }.start();
    }

    @Override
    protected void onStop() {
        super.onStop();
        mMode.setValue(0);

    }
}
