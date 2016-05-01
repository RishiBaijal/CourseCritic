package in.iiitd.pcsma.chat;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.method.ScrollingMovementMethod;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

import java.util.concurrent.ExecutionException;

public class TalkToGroup extends AppCompatActivity {

    String editTextString = "";
    String userNameSpeaking = "";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_talk_to_group);

       /* EditText editText = (EditText) findViewById(R.id.editText);
        editTextString = editText.getText().toString();*/
        Bundle bundle = getIntent().getExtras();
        userNameSpeaking = bundle.getString("username");
        System.out.println("WHATS IN :" + editTextString);


        GetFromDBGroup bj2 = new GetFromDBGroup();
        try {
            String groupnameList = bj2.execute(editTextString).get();
            TextView textView = (TextView) findViewById(R.id.textView4);
            System.out.println("GROUPNAME LIST: " + groupnameList);
            textView.setText(groupnameList);
            textView.setMovementMethod(new ScrollingMovementMethod());
        } catch (InterruptedException e) {
            e.printStackTrace();
        } catch (ExecutionException e) {
            e.printStackTrace();
        }


    }
    public String saveGroup (){
        EditText alpha = (EditText) findViewById(R.id.editText);
        String group = alpha.getText().toString();


        return group;
    }

    public void startChatGroup(View view)
    {
        Intent intent = new Intent(this, ChatActivity.class);
        SaveToDBGroup alpha = new SaveToDBGroup();
        alpha.execute(saveGroup());
        intent.putExtra("group", saveGroup());
        intent.putExtra("username",userNameSpeaking );
        startActivity(intent);
    }




}
