package in.iiitd.pcsma.chat;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.method.ScrollingMovementMethod;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

import com.rabbitmq.client.AMQP;

import java.util.concurrent.ExecutionException;

public class TalkTo extends AppCompatActivity {

    String editTextString = "";
    String userNameSpeaking = "";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_talk_to);

       /* EditText editText = (EditText) findViewById(R.id.editText);
        editTextString = editText.getText().toString();*/
        Bundle bundle = getIntent().getExtras();
        userNameSpeaking = bundle.getString("username");
        System.out.println("WHATS IN :" + editTextString);


        GetFromDB bj2 = new GetFromDB();
        try {
            String usernameList = bj2.execute(editTextString).get();
            TextView textView = (TextView) findViewById(R.id.textView4);
            System.out.println("USERNAME LIST: " + usernameList);
            textView.setText(usernameList);
            textView.setMovementMethod(new ScrollingMovementMethod());
        } catch (InterruptedException e) {
            e.printStackTrace();
        } catch (ExecutionException e) {
            e.printStackTrace();
        }


    }
    public String saveUserName3 (){
        EditText alpha = (EditText) findViewById(R.id.editText);
        String username = alpha.getText().toString();


        return username;
    }

    public void startChatSingle(View view)
    {
        Intent intent = new Intent(this, SingleChatActivity.class);
        intent.putExtra("talkingTo", saveUserName3());
        intent.putExtra("userNameSpeaking",userNameSpeaking );
        startActivity(intent);
    }




}
