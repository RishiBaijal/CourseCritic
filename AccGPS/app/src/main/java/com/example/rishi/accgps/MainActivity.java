package com.example.rishi.accgps;

import android.app.Activity;
import android.content.Context;
import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.hardware.SensorManager;
import android.os.Bundle;
import android.util.Log;
import android.widget.TextView;

public class MainActivity extends Activity implements SensorEventListener{

    private long lastUpdate = 0;
    private SensorManager sensorManager;
    private Sensor sensor;
    private TextView sensorTextView;

    GPSTracker gps;

//    Location currentLocation;
//    LocationManager locationManager;
//    TextView locationText;
//    String locationProvider;
//    private static readonly object = new object();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

//        locationManager = (LocationManager) this.getSystemService(Context.LOCATION_SERVICE);
//        LocationListener locationListener = new LocationListener() {
//
//
//            @Override
//            public void onLocationChanged(Location location) {
//
//            }
//
//            @Override
//            public void onStatusChanged(String provider, int status, Bundle extras) {
//
//            }
//
//            @Override
//            public void onProviderEnabled(String provider) {
//
//            }
//
//            @Override
//            public void onProviderDisabled(String provider) {
//
//            }
//        };
//        locationManager.requestLocationUpdates(LocationManager.NETWORK_PROVIDER, 3000, 10, this);

        sensorManager = (SensorManager) getSystemService(Context.SENSOR_SERVICE);
        sensor = sensorManager.getDefaultSensor(Sensor.TYPE_ACCELEROMETER);
        sensorManager.registerListener(this, sensor, SensorManager.SENSOR_DELAY_NORMAL);
        gps = new GPSTracker(MainActivity.this);
        if (gps.canGetLocation())
        {
            String sent = gps.getLatitude() + " " + gps.getLongitude();
            TextView text = (TextView) findViewById(R.id.gps_text);
            text.setText(sent);
        }
        else
        {
            Log.d("Can't get location", "Cannot get location");
            System.out.println("CAN'T GET LOCATION COCKSUCKA!!!!!");
            gps.showSettingsAlert();
        }


    }
//    @Override

    @Override
    protected void onResume()
    {
        super.onResume();
        sensorManager.registerListener(this, sensor, SensorManager.SENSOR_DELAY_NORMAL);
        gps = new GPSTracker(MainActivity.this);
        if (gps.canGetLocation())
        {
            String sent = gps.getLatitude() + " " + gps.getLongitude();
            TextView text = (TextView) findViewById(R.id.gps_text);
            text.setText(sent);
        }
        else
        {
            Log.d("Can't get location", "Cannot get location");
            System.out.println("CAN'T GET LOCATION COCKSUCKA!!!!!");
            gps.showSettingsAlert();
        }
    }

    @Override
    protected void onPause()
    {
        super.onPause();
        sensorManager.unregisterListener(this);
    }

    @Override
    public void onSensorChanged(SensorEvent event) {

        Sensor sensor = event.sensor;
        if (sensor.getType() == Sensor.TYPE_ACCELEROMETER) {
            float x = event.values[0];
            float y = event.values[1];
            float z = event.values[2];
            String s = x + " " + y + " " + z;
            long time = System.currentTimeMillis();
            if ((time - lastUpdate) > 3000) {
                long diffTime = (time - lastUpdate);
                lastUpdate = time;
                TextView text = (TextView) findViewById(R.id.accelerometer_text);
                text.setText(s);
            }
        }
    }

    @Override
    public void onAccuracyChanged(Sensor sensor, int accuracy) {

    }
}
