package in.iiitd.pcsma.chat;

import android.os.AsyncTask;
import android.os.SystemClock;
import android.widget.ArrayAdapter;

import com.mongodb.BasicDBObject;
import com.mongodb.DB;
import com.mongodb.DBCollection;
import com.mongodb.DBCursor;
import com.mongodb.DBObject;
import com.mongodb.MongoClient;
import com.mongodb.MongoClientURI;

import java.util.List;

/**
 * Created by Udai on 4/14/2016.
 */
public class GetFromDB extends AsyncTask<String, Void, String> {
    static String server_output = null;
    static String temp_output = null;


    protected String doInBackground(String... arg0) {

        String temp = "";
        try {

            String user = arg0[0];
            System.out.println("The name of the user is : " + user);
            MongoClientURI uri = new MongoClientURI("mongodb://rishi:ThunderAndSparks8@ds013951.mlab.com:13951/chatrabbit2");
            MongoClient client = new MongoClient(uri);
            DB db = client.getDB(uri.getDatabase());
            DBCollection newcollection = db.getCollection("usernames");
            DBCursor dbCursor = newcollection.find();
            while (dbCursor.hasNext())
            {
                DBObject dbObject = dbCursor.next();
                //temp += dbCursor.next().toString();
                System.out.println("USERNAME NIGGA:" + dbObject.get("Username"));
                temp = temp + dbObject.get("Username") + "\n";
            }

//            List<DBObject> list =  newcollection.find().toArray();
//
//            for(DBObject i : list){
//                temp += i.toString() + "\n";
//
//            }
//            System.out.println("TEMP: " + temp);

            /*System.out.println("The quizID is : " + quizID);*/
            /*MongoClientURI uri = new MongoClientURI("");
            MongoClient client = new MongoClient(uri);
            DB db = client.getDB(uri.getDatabase());
            // DB db = mongo.getDB("quiz");
            DBCollection collection = db.getCollection("teachers");
            BasicDBObject whereQuery = new BasicDBObject();
            whereQuery.put("quizID", quizID);
            DBCursor cursor3 = collection.find(whereQuery);
            while (cursor3.hasNext()) {
                DBObject dbObject = (DBObject) cursor3.next();

                //      System.out.println("TIIIIIIME Boobs: " + dbObject.get("time"));
                quizObj.quizID = quizID;
                System.out.println("Successfully retrieved quiz ID");
                quizObj.question = dbObject.get("question").toString();
                System.out.println("Successfully retrieved question.");
                System.out.println("QUESTION" + quizObj.question);
                quizObj.option1 = dbObject.get("optionA").toString();
                System.out.println("OPTION: " + quizObj.option1);
                quizObj.option2 = dbObject.get("optionB").toString();
                System.out.println("OPTION: " + quizObj.option2);

                quizObj.option3 = dbObject.get("optionC").toString();
                System.out.println("OPTION: " + quizObj.option3);

                quizObj.option4 = dbObject.get("optionD").toString();
                System.out.println("OPTION: " + quizObj.option4);
                quizObj.time = Long.parseLong(String.valueOf(dbObject.get("time")));//String.valueOf(dbObject.get("time"));
                System.out.println("Time gotten from the database at AsyncTask GetQuizInfo: " + Long.parseLong(String.valueOf(dbObject.get("time"))));


            }
        } */
        } catch (Exception e) {
            e.getMessage();


        }
        return temp;
    }
}
