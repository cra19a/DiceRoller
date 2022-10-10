package com.example.diceroller;

import androidx.appcompat.app.AppCompatActivity;

import android.media.MediaPlayer;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import android.content.Context;
import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.hardware.SensorManager;
import android.os.Bundle;
import android.widget.Toast;
import java.util.Objects;

import java.util.Random;

public class MainActivity extends AppCompatActivity {
    private ImageView imageViewDice;
    private Random rng = new Random();
    public TextView textViewCrit;
    private SensorManager mSensorManager;
    private float mAccel;
    private float mAccelCurrent;
    private float mAccelLast;

    // making comment here so I can commit maybe

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mSensorManager = (SensorManager) getSystemService(Context.SENSOR_SERVICE);
        Objects.requireNonNull(mSensorManager).registerListener(mSensorListener, mSensorManager.getDefaultSensor(Sensor.TYPE_ACCELEROMETER),
                SensorManager.SENSOR_DELAY_NORMAL);
        mAccel = 10f;
        mAccelCurrent = SensorManager.GRAVITY_EARTH;
        mAccelLast = SensorManager.GRAVITY_EARTH;

        final MediaPlayer anime_wow = MediaPlayer.create(this,R.raw.anime_wow);
        final MediaPlayer erro = MediaPlayer.create(this,R.raw.erro);
        textViewCrit = findViewById(R.id.textViewCrit);
        imageViewDice = findViewById(R.id.image_view_dice);
        imageViewDice.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                rollDice();
            }
        });
    }

    private final SensorEventListener mSensorListener = new SensorEventListener() {
        @Override
        public void onSensorChanged(SensorEvent event) {
            float x = event.values[0];
            float y = event.values[1];
            float z = event.values[2];
            mAccelLast = mAccelCurrent;
            mAccelCurrent = (float) Math.sqrt((double) (x * x + y * y + z * z));
            float delta = mAccelCurrent - mAccelLast;
            mAccel = mAccel * 0.9f + delta;
            if (mAccel > 12) {
                Toast.makeText(getApplicationContext(), "Shake event detected", Toast.LENGTH_SHORT).show();
            }
        }
        @Override
        public void onAccuracyChanged(Sensor sensor, int accuracy) {
        }
    };
    @Override
    protected void onResume() {
        mSensorManager.registerListener(mSensorListener, mSensorManager.getDefaultSensor(Sensor.TYPE_ACCELEROMETER),
                SensorManager.SENSOR_DELAY_NORMAL);
        super.onResume();
    }
    @Override
    protected void onPause() {
        mSensorManager.unregisterListener(mSensorListener);
        super.onPause();
    }

    private void rollDice() {
        int randomNumber = rng.nextInt(20) + 1;

        switch (randomNumber) {
            case 1:
                MediaPlayer missSound = MediaPlayer.create(this, R.raw.erro);
                missSound.start();
                imageViewDice.setImageResource(R.drawable.d20_1);
                textViewCrit.setVisibility(View.VISIBLE);
                textViewCrit.setText(getString(R.string.critical_miss));
                break;
            case 2:
                imageViewDice.setImageResource(R.drawable.d20_2);
                textViewCrit.setText("");
                break;
            case 3:
                imageViewDice.setImageResource(R.drawable.d20_3);
                textViewCrit.setText("");
                break;
            case 4:
                imageViewDice.setImageResource(R.drawable.d20_4);
                textViewCrit.setText("");
                break;
            case 5:
                imageViewDice.setImageResource(R.drawable.d20_5);
                textViewCrit.setText("");
                break;
            case 6:
                imageViewDice.setImageResource(R.drawable.d20_6);
                textViewCrit.setText("");
                break;
            case 7:
                imageViewDice.setImageResource(R.drawable.d20_7);
                textViewCrit.setText("");
                break;
            case 8:
                imageViewDice.setImageResource(R.drawable.d20_8);
                textViewCrit.setText("");
                break;
            case 9:
                imageViewDice.setImageResource(R.drawable.d20_9);
                textViewCrit.setText("");
                break;
            case 10:
                imageViewDice.setImageResource(R.drawable.d20_10);
                textViewCrit.setText("");
                break;
            case 11:
                imageViewDice.setImageResource(R.drawable.d20_11);
                textViewCrit.setText("");
                break;
            case 12:
                imageViewDice.setImageResource(R.drawable.d20_12);
                textViewCrit.setText("");
                break;
            case 13:
                imageViewDice.setImageResource(R.drawable.d20_13);
                textViewCrit.setText("");
                break;
            case 14:
                imageViewDice.setImageResource(R.drawable.d20_14);
                textViewCrit.setText("");
                break;
            case 15:
                imageViewDice.setImageResource(R.drawable.d20_15);
                textViewCrit.setText("");
                break;
            case 16:
                imageViewDice.setImageResource(R.drawable.d20_16);
                textViewCrit.setText("");
                break;
            case 17:
                imageViewDice.setImageResource(R.drawable.d20_17);
                textViewCrit.setText("");
                break;
            case 18:
                imageViewDice.setImageResource(R.drawable.d20_18);
                textViewCrit.setText("");
                break;
            case 19:
                imageViewDice.setImageResource(R.drawable.d20_19);
                textViewCrit.setText("");
                break;
            case 20:
                MediaPlayer hitSound = MediaPlayer.create(this, R.raw.anime_wow);
                hitSound.start();
                imageViewDice.setImageResource(R.drawable.d20_20);
                textViewCrit.setVisibility(View.VISIBLE);
                textViewCrit.setText(getString(R.string.critical_hit));
                break;
        }
    }
}