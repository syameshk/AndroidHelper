package androidhelper.sensorhelper;

import android.Manifest;
import android.app.Activity;
import android.content.pm.PackageManager;
import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.hardware.SensorManager;
import android.content.Context;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.util.Log;

import com.unity3d.player.UnityPlayer;

/**
 * Created by Syamesh K on 2/21/2018.
 */

public class HeartRateSensor {
    private String TAG = "SENSOR_HELPER";
    private SensorManager sensorManager;
    private Sensor sensor;
    private int BODY_SENSORS_REQUEST_ID = 20;
    private String callbackObjectName;

    private float heatRate = 0;

    public void Init(Activity activity, String callbackObjectName ){
        this.callbackObjectName = callbackObjectName;
        sensorManager = (SensorManager) activity.getSystemService(Context.SENSOR_SERVICE);
        sensor = sensorManager.getDefaultSensor(Sensor.TYPE_HEART_RATE);
        if(sensor == null){
            Log.d(TAG,"Sensor is null, check for permission");
            UnityPlayer.UnitySendMessage(callbackObjectName,"defaultCallbackMethode", "Sensor is null, check for permission");
        }
    }

    public void Init(Activity activity ){
        Init(activity,"DefaultCallbackClass");
    }

    public int CheckPermission(Activity activity){
        int status = 0;
        if (ContextCompat.checkSelfPermission(activity.getApplicationContext(), Manifest.permission.BODY_SENSORS)
                != PackageManager.PERMISSION_GRANTED) {
            // Permission is not granted
            // request the permission
            ActivityCompat.requestPermissions(activity,
                    new String[]{Manifest.permission.BODY_SENSORS}, BODY_SENSORS_REQUEST_ID);

            // BODY_SENSORS_REQUEST_ID is an
            // app-defined int constant. The callback method gets the
            // result of the request.

        }else{
            status = 1;
        }
        return status;
    }

    public void Start(){
        sensorManager.registerListener(sensorListener, sensor, SensorManager.SENSOR_DELAY_NORMAL);
    }

    public void Stop(){
        sensorManager.unregisterListener(sensorListener);
    }

    public float getCurrentData(){
        return heatRate;
    }

    private final SensorEventListener sensorListener = new SensorEventListener() {

        @Override
        public void onSensorChanged(SensorEvent event) {
            /*
            for(int i=0;i< event.values.length;i++){
                Log.d("Sensor",i+" : "+event.values[i]);
            }
            */

            heatRate = event.values[0];
            UnityPlayer.UnitySendMessage(callbackObjectName, "onSensorChanged", ""+event.values[0]);
        }

        @Override
        public void onAccuracyChanged(Sensor sensor, int accuracy) {
            UnityPlayer.UnitySendMessage(callbackObjectName,"onAccuracyChanged", ""+accuracy);
        }

    };

}
