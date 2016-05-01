package in.iiitd.pcsma.chat;

import android.os.AsyncTask;

import com.mongodb.BasicDBObject;
import com.mongodb.DB;
import com.mongodb.DBCollection;
import com.mongodb.MongoClient;
import com.mongodb.MongoClientURI;

/**
 * Created by Udai on 4/20/2016.
 */
public class SaveToDBGroup extends AsyncTask<String, Void, Boolean> {

    @Override
    protected Boolean doInBackground(String... arg0) {
        try
        {
            String group = arg0[0];
            MongoClientURI uri = new MongoClientURI("mongodb://rishi:ThunderAndSparks8@ds013951.mlab.com:13951/chatrabbit2");
            MongoClient client = new MongoClient(uri);
            DB db = client.getDB(uri.getDatabase());
            DBCollection newcollection = db.getCollection("groupnames");
            BasicDBObject alphaDoc = new BasicDBObject();
            alphaDoc.put("Groupname", group);
            /*alphaDoc.put("rollNo", student.getRollNo());
            alphaDoc.put("courseList", student.getCourseList());
            alphaDoc.put("quizID", "");
            alphaDoc.put("option", "");
            alphaDoc.put("correct", "");*/
            newcollection.insert(alphaDoc);
            return true;


        }
        catch (Exception e)
        {
            return false;
        }
    }
}