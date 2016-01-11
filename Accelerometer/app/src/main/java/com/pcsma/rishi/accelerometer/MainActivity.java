package com.pcsma.rishi.accelerometer;

import android.app.Activity;
import android.content.Context;
import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.hardware.SensorManager;
import android.os.Bundle;
import android.os.Environment;
import android.os.StrictMode;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileWriter;
import java.net.Socket;

public class MainActivity extends Activity implements SensorEventListener{

    private long lastUpdate = 0;
    boolean n;
    private SensorManager sensorManager;
    private Sensor sensor;
    String s = "";
    boolean startButtonPressed = false, stopButtonPressed = false;
    String acc = "";
    FileWriter fileWriter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        StrictMode.ThreadPolicy policy = new StrictMode.ThreadPolicy.Builder().permitAll().build();
        StrictMode.setThreadPolicy(policy);

        Button startBtn = (Button) findViewById(R.id.start);
        startBtn.setVisibility(Button.VISIBLE);
        Button stopBtn = (Button) findViewById(R.id.stop);
        stopBtn.setVisibility(Button.INVISIBLE);




        sensorManager = (SensorManager) getSystemService(Context.SENSOR_SERVICE);
        sensor = sensorManager.getDefaultSensor(Sensor.TYPE_ACCELEROMETER);
        sensorManager.registerListener(this, sensor, SensorManager.SENSOR_DELAY_NORMAL);

    }

    public void startCollection(View view)
    {
        startButtonPressed = !(startButtonPressed);
    }
    public void stopCollection(View view)
    {
        stopButtonPressed = !(stopButtonPressed);
    }
    public void sendMessage(View view)
    {
        FileInputStream inputStream;
        String filepath = Environment.getExternalStorageDirectory() + "/accel.csv";
        System.out.println("FILEPATH: " + filepath);
        String data = "";
        Socket socket = null;
        DataInputStream dataInputStream = null;
        DataOutputStream dataOutputStream = null;
        int bytesRead;
        StringBuffer fileContent = new StringBuffer("");
        try
        {

            inputStream = new FileInputStream(filepath);
            //inputStream = getResources().openRawResource(R.);
            CSVFile csvFile = new CSVFile(inputStream);
            data = csvFile.read();

//            byte[] buffer = new byte[1024];
//            while ((bytesRead = inputStream.read(buffer)) != -1)
//            {
//                fileContent.append(new String (buffer, 0, bytesRead));
//            }
//            System.out.println("THE VALUE OF FILE CONTENT IS: " + fileContent.toString());
        }
        catch(Exception e)
        {
            e.printStackTrace();
        }
        try
        {
            socket = new Socket("192.168.1.4", 8888);
            dataOutputStream = new DataOutputStream(socket.getOutputStream());
            dataInputStream = new DataInputStream(socket.getInputStream());
            dataOutputStream.writeUTF("Accelerometer data:  " + data);
            TextView textView = (TextView) findViewById(R.id.responseTextView);
            textView.setText(dataInputStream.readUTF());
        }
        catch(Exception e)
        {
            e.printStackTrace();
        }
        finally
        {
            if (socket!= null)
            {
                try
                {
                    socket.close();
                }
                catch (Exception e)
                {
                    e.printStackTrace();
                }
            }
            if (dataOutputStream != null)
            {
                try
                {
                    dataOutputStream.close();
                }
                catch(Exception e)
                {
                    e.printStackTrace();
                }
            }
            if (dataInputStream != null)
            {
                try
                {
                    dataInputStream.close();
                }
                catch (Exception e)
                {
                    e.printStackTrace();
                }
            }
        }
    }
    @Override
    protected void onResume()
    {
        super.onResume();
        sensorManager.registerListener(this, sensor, SensorManager.SENSOR_DELAY_NORMAL);
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
        File root = Environment.getExternalStorageDirectory();
        File gpxFile = new File(root, "accel.csv");
        //System.out.println("ROOT : " + root);
        if (sensor.getType() == Sensor.TYPE_ACCELEROMETER && startButtonPressed) {
            Button startBtn = (Button) findViewById(R.id.start);
            startBtn.setVisibility(Button.INVISIBLE);
            Button stopBtn = (Button) findViewById(R.id.stop);
            stopBtn.setVisibility(Button.VISIBLE);

            float x = event.values[0];
            float y = event.values[1];
            float z = event.values[2];

            long time = System.currentTimeMillis();
            s = time + "," + x + "," + y + "," + z + "";
            if ((time - lastUpdate) > 3000) {
                long diffTime = (time - lastUpdate);
                lastUpdate = time;
                acc = acc + s + "\n";
                TextView text = (TextView) findViewById(R.id.accText);
                text.setText(acc);
                try
                {
                    fileWriter = new FileWriter(gpxFile);
                    fileWriter.write("Timestamp, x, y, z\n");
                    fileWriter.write(acc);
                }
                catch(Exception e)
                {
                    e.printStackTrace();
                }
            }
            if (stopButtonPressed)
            {
                startBtn.setVisibility(Button.VISIBLE);
                stopBtn.setVisibility(Button.INVISIBLE);
                startButtonPressed = false;
                stopButtonPressed = false;
                if (fileWriter != null)
                {
                    try {
                        fileWriter.flush();
                        fileWriter.close();
                    }
                    catch(Exception e)
                    {
                        e.printStackTrace();
                    }
                }
            }

        }

    }

    @Override
    public void onAccuracyChanged(Sensor sensor, int accuracy) {

    }
}
