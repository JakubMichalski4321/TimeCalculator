package com.example.club27;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import androidx.appcompat.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Button obliczButton = (Button) findViewById(R.id.obliczButton);
        obliczButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                EditText minutesSetEditText = (EditText) findViewById(R.id.minutesSetEditText);
                TextView resultTextView = (TextView) findViewById(R.id.resultText);

                double minutes = Integer.parseInt(minutesSetEditText.getText().toString());

            }
        });

    }
}
