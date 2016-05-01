package in.iiitd.pcsma.chat;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

/**
 * Created by Udai on 4/13/2016.
 */
public class EnterUsernameGroup extends Activity {
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_entertext2);


    }

    public String saveUserName2 (){
        EditText alpha = (EditText) findViewById(R.id.editTextduos);
        String username = alpha.getText().toString();


        return username;
    }


    public void startGroupChat(View view)
    {
        Intent intent = new Intent(this, ChatActivity.class);
        intent.putExtra("username", saveUserName2());
        startActivity(intent);
    }
}
