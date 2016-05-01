package com.pcsma.rishi.accelerometer;

import android.os.AsyncTask;
import android.widget.TextView;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.Socket;
import java.net.UnknownHostException;

/**
 * Created by Rishi on 10/01/16.
 */
public class Client extends AsyncTask<Void, Void, Void>{
    String destAddress, response = "";
    int destPort;
    TextView textView;

    Client(String destAddress, int destPort, TextView textView)
    {
        this.destAddress = destAddress;
        this.destPort = destPort;
        this.textView = textView;
    }

    protected Void doInBackground(Void...arg0)
    {
        Socket socket = null;
        try
        {
            socket = new Socket(destAddress, destPort);
            ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream(1024);
            byte[] buffer = new byte[1024];
            int bytesRead;
            InputStream inputStream = socket.getInputStream();
            System.out.println("INPUT STREAM: " + inputStream.toString());
            while ((bytesRead = inputStream.read(buffer)) != -1)
            {
                byteArrayOutputStream.write(buffer, 0, bytesRead);
                //.write("MY COCK IS MUCH BIGGER THAN YOURS!!".getBytes());
                //byteArrayOutputStream.write(buffer1, 0, bytesRead);
                response += byteArrayOutputStream.toString("UTF-8");
            }

        }
        catch (UnknownHostException e)
        {
            e.printStackTrace();
            response += " unknown host exception: " + e.toString();
        }
        catch (IOException e)
        {
            e.printStackTrace();
            response += "IO exception: " + e.toString();
        }
        catch (Exception e)
        {
            e.printStackTrace();
        }
    return null;
    }

    @Override
    protected void onPostExecute(Void result)
    {
        textView.setText(response);
        super.onPostExecute(result);
    }
}
