package com.example.jose.movimientoaudio;

import android.content.Context;
import android.graphics.Color;
import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.hardware.SensorManager;
import android.media.AudioManager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class MainActivity extends AppCompatActivity {

   SensorManager sensorManager;
   Sensor sensor;
   SensorEventListener sensorEventListener;
    private AudioManager audioManager;
    int mov;
    int mov2;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        setVolumeControlStream(AudioManager.STREAM_MUSIC);
        sensorManager = (SensorManager) getSystemService((SENSOR_SERVICE));
        sensor = sensorManager.getDefaultSensor(Sensor.TYPE_ACCELEROMETER);
        if(sensor==null){
            finish();
        }
        sensorEventListener = new SensorEventListener() {
            @Override
            public void onSensorChanged(SensorEvent event) {
             float x = event.values[0];
             float z = event.values[2];

             if (x<-5 && mov==0){
                 mov++;
                 getWindow().getDecorView().setBackgroundColor(Color.BLUE);
             }else if(x>5 && mov==1){
                 mov++;
                 getWindow().getDecorView().setBackgroundColor(Color.CYAN);
             }

             if(z<-5 && mov2==0){
                 getWindow().getDecorView().setBackgroundColor(Color.RED);
                 mov2++;
             }else if(z>5 && mov2==1){
                 mov2++;
                 getWindow().getDecorView().setBackgroundColor(Color.GRAY);
             }


             if(mov==2){mov=0;
             subir();
             }else if(mov2==2){
                 mov2=0;
                 bajar();
             }



              }

            @Override
            public void onAccuracyChanged(Sensor sensor, int accuracy) {

            }
        };
        start();
    }


    public void volumen(View view) {
        audioManager = (AudioManager) getSystemService(Context.AUDIO_SERVICE);
        AudioManager AudioManager = (AudioManager) getSystemService (Context.AUDIO_SERVICE);
        int musicVolume = AudioManager.getStreamVolume(AudioManager.STREAM_MUSIC);
        audioManager.setStreamVolume(AudioManager.STREAM_MUSIC, musicVolume+1,0);
    }

    public void DownV(View view) {
        audioManager = (AudioManager) getSystemService(Context.AUDIO_SERVICE);
        AudioManager AudioManager = (AudioManager) getSystemService (Context.AUDIO_SERVICE);
        int musicVolume = AudioManager.getStreamVolume(AudioManager.STREAM_MUSIC);
        audioManager.setStreamVolume(AudioManager.STREAM_MUSIC, musicVolume-1,0);

    }


    public void subir (){
        audioManager = (AudioManager) getSystemService(Context.AUDIO_SERVICE);
        AudioManager AudioManager = (AudioManager) getSystemService (Context.AUDIO_SERVICE);
        int musicVolume = AudioManager.getStreamVolume(AudioManager.STREAM_MUSIC);
        audioManager.setStreamVolume(AudioManager.STREAM_MUSIC, musicVolume+1,0);

    }

    public void  bajar (){
        audioManager = (AudioManager) getSystemService(Context.AUDIO_SERVICE);
        AudioManager AudioManager = (AudioManager) getSystemService (Context.AUDIO_SERVICE);
        int musicVolume = AudioManager.getStreamVolume(AudioManager.STREAM_MUSIC);
        audioManager.setStreamVolume(AudioManager.STREAM_MUSIC, musicVolume-1,0);

    }

    private void start (){
        sensorManager.registerListener(sensorEventListener,sensor,SensorManager.SENSOR_DELAY_NORMAL);
    }

    public void stop (){
        sensorManager.unregisterListener(sensorEventListener);
    }
}
