package com.pcsma.rishi.accserver;

import android.app.Activity;
import android.os.Bundle;
import android.widget.TextView;

public class MainActivity extends Activity {

    Server server;
    TextView infoIP, message;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        infoIP = (TextView) findViewById(R.id.infoip);
        message = (TextView) findViewById(R.id.msg);
        server = new Server(this);
        infoIP.setText(server.getIPAddress() + " " + server.getPort());
    }
    protected void onDestroy()
    {
        super.onDestroy();
        server.onDestroy();
    }
}
