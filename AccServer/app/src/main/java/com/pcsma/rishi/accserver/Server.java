package com.pcsma.rishi.accserver;

import java.io.IOException;
import java.io.OutputStream;
import java.io.PrintStream;
import java.net.InetAddress;
import java.net.NetworkInterface;
import java.net.ServerSocket;
import java.net.Socket;
import java.net.SocketException;
import java.util.Enumeration;

/**
 * Created by Rishi on 09/01/16.
 */
public class Server {
    MainActivity activity;
    ServerSocket serverSocket;
    String message = "";
    static final int SERVERPORT = 8080;

    public Server(MainActivity activity) {
        this.activity = activity;
        Thread socketServerThread = new Thread(new SocketServerThread());
        socketServerThread.start();
    }

    int getPort() {
        return SERVERPORT;
    }

    public void onDestroy() {
        try {
            if (serverSocket != null) {
                serverSocket.close();
            }
        }
        catch (IOException e)
        {
            e.printStackTrace();
        }
    }

    private class SocketServerThread extends Thread {
        int count = 0;

        @Override
        public void run() {
            try {
                serverSocket = new ServerSocket(SERVERPORT);
                while (true)
                {
                    Socket socket = serverSocket.accept();
                    count++;

                    message += " # " + count + " from " + socket.getInetAddress() + " : " + socket.getPort() + " " + socket.toString();
                    activity.runOnUiThread(new Runnable() {
                        @Override
                        public void run() {
                            activity.message.setText(message);
                        }
                    });
                    SocketServerReplyThread sockeServerReplyThread = new SocketServerReplyThread(socket, count);
                    sockeServerReplyThread.run();
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }
    private class SocketServerReplyThread extends Thread{
        private Socket hostThreadSocket;
        int cnt;
        SocketServerReplyThread(Socket socket, int c)
        {
            hostThreadSocket = socket;
            cnt = c;
        }

        @Override
        public void run() {
            OutputStream outputStream;
            String reply = "You are client number " + cnt;
            try {
                outputStream = hostThreadSocket.getOutputStream();
                PrintStream printStream = new PrintStream(outputStream);
                printStream.print(reply);
                printStream.close();
                message += " replied " + reply + "\n";
                activity.runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        activity.message.setText(message);
                    }
                });

            } catch (IOException e) {
                e.printStackTrace();
                message += " Something wrong " + e.toString() + "\n";
            }
            catch (Exception e) {
                e.printStackTrace();

            }
            activity.runOnUiThread(new Runnable() {
                @Override
                public void run() {
                    activity.message.setText(message);
                }
            });

        }

    }

    public String getIPAddress()
    {
        String IP = "";
        try
        {
            Enumeration<NetworkInterface> enumNetWorkInterface = NetworkInterface.getNetworkInterfaces();
            while (enumNetWorkInterface.hasMoreElements())
            {
                NetworkInterface networkInterface = enumNetWorkInterface.nextElement();
                Enumeration<InetAddress> enumInetAddress = networkInterface.getInetAddresses();
                while (enumInetAddress.hasMoreElements())
                {
                    InetAddress inetAddress = enumInetAddress.nextElement();
                    if (inetAddress.isSiteLocalAddress())
                    {
                        IP += " Server running at: " + inetAddress.getHostAddress();
                    }
                }
            }
        }
        catch (SocketException e)
        {
            e.printStackTrace();
            IP += " Something wrong " + e.toString() + "\n";
        }
        catch (Exception e)
        {
            e.printStackTrace();
            IP += " Something wrong " + e.toString() + "\n";
        }
        return IP;
    }
}