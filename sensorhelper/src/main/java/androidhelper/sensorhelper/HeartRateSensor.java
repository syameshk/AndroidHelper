package androidhelper.sensorhelper;

import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.hardware.SensorManager;
import android.content.Context;

/**
 * Created by skunnummal on 2/21/2018.
 */

public class HeartRateSensor {
    private SensorManager sensorManager;
    private Sensor sensor;

    private float heatRate = 0;

    public void Init(Context context){
        sensorManager = (SensorManager) context.getSystemService(Context.SENSOR_SERVICE);
        sensor = sensorManager.getDefaultSensor(Sensor.TYPE_HEART_RATE);
    }

    public void Start(){
        sensorManager.registerListener(sensorListener, sensorManager.getDefaultSensor(Sensor.TYPE_HEART_RATE), SensorManager.SENSOR_DELAY_NORMAL);
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

        }

        @Override
        public void onAccuracyChanged(Sensor sensor, int accuracy) {
        }

    };

}
