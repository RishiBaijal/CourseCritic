package in.iiitd.pcsma.chat;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;

public class MainActivity extends Activity {
    public String displayName = "";
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        Bundle bundle = getIntent().getExtras();
        displayName = bundle.getString("displayName");

//        setupConnectionFactory();
//        publishToAMQP();
//        setupPubButton();
//
//        final Handler incomingMessageHandler = new Handler() {
//            @Override
//            public void handleMessage(Message msg) {
//                String message = msg.getData().getString("msg");
//                TextView tv = (TextView) findViewById(R.id.textView);
//                Date now = new Date();
//                SimpleDateFormat ft = new SimpleDateFormat ("hh:mm:ss");
//                tv.append(ft.format(now) + ' ' + message + '\n');
//            }
//        };
//        subscribe(incomingMessageHandler);
    }
    public void startSingleChatEU(View view)
    {

        SaveToDB bj = new SaveToDB();
        bj.execute(displayName);
        Intent intent = new Intent(this, TalkTo.class);
        intent.putExtra("username", displayName);
        startActivity(intent);
    }
    public void startGroupChatEU(View view)
    {
        Intent intent = new Intent(this, TalkToGroup.class);
        intent.putExtra("username", displayName);
        startActivity(intent);
    }

//    void setupPubButton() {
//        Button button = (Button) findViewById(R.id.publish);
//        button.setOnClickListener(new OnClickListener() {
//            @Override
//            public void onClick(View arg0) {
//                EditText et = (EditText) findViewById(R.id.text);
//                publishMessage(et.getText().toString());
//                et.setText("");
//            }
//        });
//    }
//
//    Thread subscribeThread;
//    Thread publishThread;
//    @Override
//    protected void onDestroy() {
//        super.onDestroy();
//        publishThread.interrupt();
//        subscribeThread.interrupt();
//    }
//
//    private BlockingDeque<String> queue = new LinkedBlockingDeque<String>();
//    void publishMessage(String message) {
//        //Adds a message to internal blocking queue
//        try {
//            Log.d("","[q] " + message);
//            queue.putLast(message);
//        } catch (InterruptedException e) {
//            e.printStackTrace();
//        }
//    }
//
//    ConnectionFactory factory = new ConnectionFactory();
//    private void setupConnectionFactory() {
//        String uri = "amqp://kcmhmxfn:gepGpoFPhwoZCr1rHaD93s2RmnOzPdfg@fox.rmq.cloudamqp.com/kcmhmxfn";
//        try {
//            factory.setAutomaticRecoveryEnabled(false);
//            factory.setUri(uri);
//        } catch (KeyManagementException | NoSuchAlgorithmException | URISyntaxException e1) {
//            e1.printStackTrace();
//        }
//    }
//
//    void subscribe(final Handler handler)
//    {
//        subscribeThread = new Thread(new Runnable() {
//            @Override
//            public void run() {
//                while(true) {
//                    try {
//                        Connection connection = factory.newConnection();
//                        Channel channel = connection.createChannel();
//                        channel.basicQos(1);
//                        DeclareOk q = channel.queueDeclare();
//                        channel.queueBind(q.getQueue(), "amq.direct", "chat");
//                        QueueingConsumer consumer = new QueueingConsumer(channel);
//                        channel.basicConsume(q.getQueue(), true, consumer);
//
//                        // Process deliveries
//                        while (true) {
//                            QueueingConsumer.Delivery delivery = consumer.nextDelivery();
//
//                            String message = new String(delivery.getBody());
//                            Log.d("","[r] " + message);
//
//                            Message msg = handler.obtainMessage();
//                            Bundle bundle = new Bundle();
//
//                            bundle.putString("msg", message);
//                            msg.setData(bundle);
//                            handler.sendMessage(msg);
//                        }
//                    } catch (InterruptedException e) {
//                        break;
//                    } catch (Exception e1) {
//                        Log.d("", "Connection broken: " + e1.getClass().getName());
//                        try {
//                            Thread.sleep(4000); //sleep and then try again
//                        } catch (InterruptedException e) {
//                            break;
//                        }
//                    }
//                }
//            }
//        });
//        subscribeThread.start();
//    }
//
//    public void publishToAMQP()
//    {
//        publishThread = new Thread(new Runnable() {
//            @Override
//            public void run() {
//                while(true) {
//                    try {
//                        Connection connection = factory.newConnection();
//                        Channel ch = connection.createChannel();
//                        ch.confirmSelect();
//
//                        while (true) {
//                            String message = queue.takeFirst();
//                            try{
//                                ch.basicPublish("amq.direct", "chat", null, message.getBytes());
//                                Log.d("", "[s] " + message);
//                                ch.waitForConfirmsOrDie();
//                            } catch (Exception e){
//                                Log.d("","[f] " + message);
//                                queue.putFirst(message);
//                                throw e;
//                            }
//                        }
//                    } catch (InterruptedException e) {
//                        break;
//                    } catch (Exception e) {
//                        Log.d("", "Connection broken: " + e.getClass().getName());
//                        try {
//                            Thread.sleep(5000); //sleep and then try again
//                        } catch (InterruptedException e1) {
//                            break;
//                        }
//                    }
//                }
//            }
//        });
//        publishThread.start();
//    }
}