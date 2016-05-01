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
public class EnterUsername extends Activity {
    public String username;
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_entertext);


    }

    public String saveUserName (){
        EditText alpha = (EditText) findViewById(R.id.editTextuno);
        username = alpha.getText().toString();


        return username;
    }


    public void startSingleChat(View view)
    {
        Intent intent = new Intent(this, TalkTo.class);
        String temp = saveUserName();
        intent.putExtra("username", temp);
        SaveToDB bj = new SaveToDB();
        bj.execute(temp);

        startActivity(intent);
    }
}
