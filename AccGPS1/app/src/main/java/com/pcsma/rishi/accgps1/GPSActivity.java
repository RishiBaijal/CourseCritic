package com.pcsma.rishi.accgps1;

import android.app.Activity;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.os.Bundle;
import android.widget.TextView;

public class GPSActivity extends Activity implements LocationListener {
    private LocationManager locationManager;
//    private LocationListener locationListener = new DummyLocationListener();
//    private GPSListener gpsListener = new GPSListener();
    private Location location;
    TextView gpsText;

//    private GpsStatus gpsStatus;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_gps);
        gpsText = (TextView) findViewById(R.id.gps_text);
    }

    void initializeLocationManager()
    {
        locationManager = (LocationManager) getSystemService(LOCATION_SERVICE);
    }

//        locationManager = (LocationManager) getSystemService(Context.LOCATION_SERVICE);
//        gpsStatus = locationManager.getGpsStatus(null);
//        if (checkSelfPermission(Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
//            return;
//        }
//        locationManager.addGpsStatusListener(gpsListener);
//        locationManager.requestLocationUpdates(locationManager.GPS_PROVIDER, 2000, 0, locationListener);

//
//    private void getSatData()
//    {
//        Iterable<GpsSatellite> sats = gpsStatus.getSatellites();
//        for(GpsSatellite sat : sats){
//            StringBuilder sb = new StringBuilder();
//            sb.append(sat.getPrn());
//            sb.append("\t");
//            sb.append(sat.getElevation());
//            sb.append("\t");
//            sb.append(sat.getAzimuth());
//            sb.append("\t");
//            sb.append(sat.getSnr());
//
//            try {
//
//            } catch (Exception e) {
//                Log.w("SatData Error",e.getMessage());
//            }
//        }
//    }
    protected void onResume()
    {
        super.onResume();
    }

    @Override
    public void onLocationChanged(Location location) {

    }

    @Override
    public void onStatusChanged(String provider, int status, Bundle extras) {

    }

    @Override
    public void onProviderEnabled(String provider) {

    }

    @Override
    public void onProviderDisabled(String provider) {

    }
//
//    class GPSListener implements GpsStatus.Listener
//    {
//        @Override
//        public void onGpsStatusChanged(int event)
//        {
//            getSatData();
//        }
//    }
//    class DummyLocationListener implements LocationListener
//    {
//
//        @Override
//        public void onLocationChanged(Location location) {
//
//        }
//
//        @Override
//        public void onStatusChanged(String provider, int status, Bundle extras) {
//
//        }
//
//        @Override
//        public void onProviderEnabled(String provider) {
//
//        }
//
//        @Override
//        public void onProviderDisabled(String provider) {
//
//        }
//    }
}
