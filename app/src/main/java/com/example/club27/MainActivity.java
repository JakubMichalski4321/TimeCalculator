package com.example.club27;

import android.annotation.SuppressLint;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Switch;
import android.widget.TextView;
import androidx.appcompat.app.AppCompatActivity;
import java.text.DateFormat;
import java.util.Calendar;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        final Button calculateButton = findViewById(R.id.obliczButton);
        final Switch wasHeDrinkingSwitch = findViewById(R.id.byloPiteSwitch);
        final EditText minutesSetEditText = findViewById(R.id.minutesSetEditText);
        final Calendar calendar = Calendar.getInstance();
        TextView textViewDate = findViewById(R.id.currentTimeView);

        SetCurrentDateToCurrentTimeView(textViewDate, calendar);

        calculateButton.setOnClickListener(new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            if (!minutesSetEditText.getText().toString().trim().equals("")) {

                calculateButton.setEnabled(false);
                PlaySound();
                WaitForXSeconds(3);

                SetResultToResultTextView(CalculateResult(calendar, minutesSetEditText, wasHeDrinkingSwitch));

                calculateButton.setEnabled(true);
            }
        } });
    }

    @SuppressLint("SetTextI18n")
    private void SetResultToResultTextView(double calculateResult) {
        TextView resultTextView = findViewById(R.id.resultText);
        resultTextView.setText((int) calculateResult + " minut");
    }

    private double CalculateResult(Calendar calendar, EditText minutesSetEditText, Switch wasHeDrinkingSwitch) {
        int dayOfWeek = calendar.get(Calendar.DAY_OF_WEEK);
        int hourOfDay = calendar.get(Calendar.HOUR);

        double scaleLevel = 1.0;

        switch (hourOfDay) {
            case 0:
                scaleLevel = 1.1;
                if (dayOfWeek == 6) scaleLevel *= 2;
                break;
            case 1:
                scaleLevel = 1.05;
                break;
            case 2:
            case 3:
            case 4:
                scaleLevel = 1.02;
                break;
            case 5:
                scaleLevel = 1.01;
                break;
            case 6:
            case 7:
                scaleLevel = 1.0;
                break;
            case 8:
                scaleLevel = 1.3;
                if (dayOfWeek == 6) scaleLevel *= 3;
                break;
            case 9:
                scaleLevel = 1.6;
                if (dayOfWeek == 6) scaleLevel *= 3;
                break;
            case 10:
                scaleLevel = 2.1;
                if (dayOfWeek == 6) scaleLevel *= 3;
                break;
            case 11:
                scaleLevel = 2.6;
                if (dayOfWeek == 6) scaleLevel *= 4;
                break;
            case 12:
                scaleLevel = 3.2;
                if (dayOfWeek == 6) scaleLevel *= 4;
                break;
            case 13:
                scaleLevel = 2.67;
                if (dayOfWeek == 6) scaleLevel *= 2.4;
                break;
            case 14:
                scaleLevel = 3.12;
                if (dayOfWeek == 6) scaleLevel *= 2;
                break;
            case 15:
                scaleLevel = 3.24;
                if (dayOfWeek == 6) scaleLevel *= 1.5;
                break;
            case 16:
                scaleLevel = 3.1;
                if (dayOfWeek == 6) scaleLevel *= 1.5;
                break;
            case 17:
                scaleLevel = 2.7;
                if (dayOfWeek == 6) scaleLevel *= 1.3;
                break;
            case 18:
                scaleLevel = 2.4;
                if (dayOfWeek == 6) scaleLevel *= 1.2;
                break;
            case 19:
                scaleLevel = 2.5;
                if (dayOfWeek == 6) scaleLevel *= 1.3;
                break;
            case 20:
                scaleLevel = 2.3;
                if (dayOfWeek == 6) scaleLevel *= 1.4;
                break;
            case 21:
                scaleLevel = 2.2;
                if (dayOfWeek == 6) scaleLevel *= 1.3;
                break;
            case 22:
                scaleLevel = 1.5;
                if (dayOfWeek == 6) scaleLevel *= 1.2;
                break;
            case 23:
                scaleLevel = 1.3;
                if (dayOfWeek == 6) scaleLevel *= 1.1;
                break;
        }

        double scale = (Math.random() * ((scaleLevel - 1) + 1)) + 1;
        double resultMinutes = Double.parseDouble(minutesSetEditText.getText().toString()) * scale;
        if (wasHeDrinkingSwitch.isChecked()) resultMinutes *= 0.7;

        return resultMinutes;
    }

    private void WaitForXSeconds(int seconds) {
        int milliseconds = seconds * 1000;
        try { Thread.sleep(milliseconds); } catch (InterruptedException e) { e.printStackTrace(); }
    }

    private void PlaySound() {
        final MediaPlayer clockSound = MediaPlayer.create(this, R.raw.ticking_sound);
        clockSound.start();
    }

    private void SetCurrentDateToCurrentTimeView(TextView textViewDate, Calendar calendar) {
        String currentDate = DateFormat.getDateInstance(DateFormat.FULL).format(calendar.getTime());
        textViewDate.setText(currentDate);
    }
}
