package syam.androidhelper;

import android.app.Activity;
import android.os.Bundle;
import android.util.Log;

import java.util.Timer;
import java.util.TimerTask;

import syam.androidhelper.R;
import androidhelper.sensorhelper.HeartRateSensor;

public class MainActivity extends Activity {

    public HeartRateSensor sensor;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        sensor = new HeartRateSensor();
        sensor.Init(getApplicationContext());

        Timer timer = new Timer();
        timer.schedule(new TimerTask() {
            @Override
            public void run() {
                update();
            }
        },1000,5000);
    }

    @Override
    protected void onResume() {
        Log.d("Sensor","Starting");
        super.onResume();
        sensor.Start();
    }

    @Override
    protected void onStop() {
        sensor.Stop();
        super.onStop();
    }

    private void update(){
        //This method is called directly by the timer
        //and runs in the same thread as the timer.

        Log.d("Sensor","Update :"+sensor.getCurrentData());

        //We call the method that will work with the UI
        //through the runOnUiThread method.
    }



}
