package in.iiitd.rishi;

import com.mongodb.BasicDBObject;
import com.mongodb.DB;
import com.mongodb.DBCollection;
import com.mongodb.DBCursor;
import com.mongodb.Mongo;
import com.mongodb.MongoClient;
import java.net.UnknownHostException;
import java.util.Date;
import java.util.Iterator;
import java.util.List;
import java.util.Set;
import java.io.*;
import java.util.ArrayList;

/**
 * Hello world!
 *
 */
public class App {

    public static void main(String[] args) throws UnknownHostException, IOException {
        String studentName, studentRollNo;
        ArrayList<String> courses = new ArrayList<String>();
        int numberOfCourses;
        //System.out.println( "Hello World!" );
        // Mongo mongo = new Mongo("localhost", 27017);
        InputStreamReader inputStreamReader = new InputStreamReader(System.in);
        BufferedReader obj = new BufferedReader(inputStreamReader);
        System.out.println("Enter your choice: ");
        System.out.println("1. Insert");
        System.out.println("2. Find");
        System.out.println("3. Update");
        System.out.println("4. Delete");
        System.out.println("5. Display");
        System.out.println("6. Quit");
        int choice;
        choice = Integer.parseInt(obj.readLine());
        while (choice != 6) {
            switch (choice) {
                case 1:
                    System.out.println("Enter student name: ");
                    studentName = obj.readLine();
                    System.out.println("Enter student roll number: ");
                    studentRollNo = obj.readLine();
                    System.out.println("Enter number of courses: ");
                    numberOfCourses = Integer.parseInt(obj.readLine());
                    for (int i = 0; i < numberOfCourses; i++) {
                        String temp;
                        System.out.println("Enter course name: ");
                        temp = obj.readLine();
                        courses.add(temp);
                    }
                    Student student = new Student(studentName, studentRollNo, courses);
                    MongoClient mongoClient = new MongoClient("localhost", 27017);

                    List<String> dbs = mongoClient.getDatabaseNames();
                    Iterator iterator = dbs.iterator();
                    System.out.println("Printing database names: ");
                    while (iterator.hasNext()) {
                        System.out.println(iterator.next());
                    }
                    //Creation
                    DB db = mongoClient.getDB("Student");

                    Set<String> tables = db.getCollectionNames();

                    for (String coll : tables) {
                        System.out.println(coll);
                    }
                    // Insertion
                    // DBCollection collection = db.getCollection("user");
                    DBCollection table = db.getCollection("user");
                    BasicDBObject document = new BasicDBObject();
                    document.put("name", student.getName());
                    document.put("RollNumber", student.getRoll_no());
                    document.put("createdDate", new Date());
                    for (int i = 0; i < numberOfCourses; i++) {
                        String temp = student.getCourses().get(i);
                        document.put("course_" + (i + 1), temp);
                    }
                    table.insert(document);
                    break;
                //Find and display
                case 2:
                    mongoClient = new MongoClient("localhost", 27017);
                    db = mongoClient.getDB("Student");
                    table = db.getCollection("user");
                    System.out.println("Select criteria to search by: ");
                    System.out.println("1. Name");
                    System.out.println("2. Roll number");
                    int criteria;
                    criteria = Integer.parseInt(obj.readLine());
                    if (criteria == 1) {
                        String nameTemp;
                        System.out.println("Enter name");
                        nameTemp = obj.readLine();
                        BasicDBObject searchQuery = new BasicDBObject();
                        searchQuery.put("name", nameTemp);
                        DBCursor cursor = table.find(searchQuery);
                        System.out.println("Results of the find query: ");
                        while (cursor.hasNext()) {
                            System.out.println(cursor.next());
                        }
                    } else if (criteria == 2) {
                        String tempRoll;
                        System.out.println("Enter roll number");
                        tempRoll = obj.readLine();
                        BasicDBObject searchQuery = new BasicDBObject();
                        searchQuery.put("RollNumber", tempRoll);
                        DBCursor cursor = table.find(searchQuery);
                        System.out.println("Results of the find query: ");
                        while (cursor.hasNext()) {
                            System.out.println(cursor.next());
                        }
                    } else {
                        System.out.println("Incorrect criteria");
                    }

                    break;
                //Updation
                case 3:
                    mongoClient = new MongoClient("localhost", 27017);
                    db = mongoClient.getDB("Student");
                    table = db.getCollection("user");
                    System.out.println("Select criteria to search by: ");
                    System.out.println("1. Name");
                    System.out.println("2. Roll number");
                    criteria = Integer.parseInt(obj.readLine());
                    if (criteria == 1) {
                        BasicDBObject query = new BasicDBObject();
                        System.out.println("Enter old name: ");
                        query.put("name", obj.readLine());
                        System.out.println("Enter new name: ");
                        BasicDBObject obj1 = new BasicDBObject();
                        String newName = obj.readLine();
                        System.out.println("Enter new Roll number");
                        String newRollNo = obj.readLine();
                        //obj1.put("name", obj.readLine());
                        //obj1.put("RollNumber", obj.readLine());
                        System.out.println("Enter number of courses");
                        int noc;
                        noc = Integer.parseInt(obj.readLine());
                        ArrayList<String> arrayList = new ArrayList<String>();
                        for (int i = 0; i < noc; i++) {
                            System.out.println("Enter course # : " + (i + 1));
                            arrayList.add(obj.readLine());
                        }

                        Student newStudent = new Student(newName, newRollNo, arrayList);
                        obj1.put("name", newStudent.getName());
                        obj1.put("RollNumber", newStudent.getRoll_no());
                        for (int i = 0; i < noc; i++) {
                            String temp = newStudent.getCourses().get(i);
                            obj1.put("course_" + (i + 1), temp);
                        }
                        BasicDBObject updateObject = new BasicDBObject();
                        updateObject.put("$set", obj1);

                        table.update(query, updateObject);

                    } else if (criteria == 2) {
                        BasicDBObject query = new BasicDBObject();
                        System.out.println("Enter old roll number: ");
                        query.put("RollNumber", obj.readLine());
                        System.out.println("Enter new name: ");
                        BasicDBObject obj1 = new BasicDBObject();
                        String newName = obj.readLine();
                        System.out.println("Enter new Roll number");
                        String newRollNo = obj.readLine();
                        //obj1.put("name", obj.readLine());
                        //obj1.put("RollNumber", obj.readLine());
                        System.out.println("Enter number of courses");
                        int noc;
                        noc = Integer.parseInt(obj.readLine());
                        ArrayList<String> arrayList = new ArrayList<String>();
                        for (int i = 0; i < noc; i++) {
                            System.out.println("Enter course # : " + (i + 1));
                            arrayList.add(obj.readLine());
                        }

                        Student newStudent = new Student(newName, newRollNo, arrayList);
                        obj1.put("name", newStudent.getName());
                        obj1.put("RollNumber", newStudent.getRoll_no());
                        for (int i = 0; i < noc; i++) {
                            String temp = newStudent.getCourses().get(i);
                            obj1.put("course_" + (i + 1), temp);
                        }
                        BasicDBObject updateObject = new BasicDBObject();
                        updateObject.put("$set", obj1);

                        table.update(query, updateObject);

                    } else {
                        System.out.println("Incorrect criteria");
                    }
                    // Display
                    System.out.println("Results after updation.");
                    BasicDBObject searchQuery2 = new BasicDBObject().append("name", "Udai");
                    DBCursor dbcursor = table.find(searchQuery2);
                    while (dbcursor.hasNext()) {
                        System.out.println(dbcursor.next());
                    }
                    break;
                case 4:
                    System.out.println("comes here!");
                    mongoClient = new MongoClient("localhost", 27017);
                    db = mongoClient.getDB("Student");
                    table = db.getCollection("user");
                    System.out.println("Select criteria to delete by: ");
                    System.out.println("1. Name");
                    System.out.println("2. Roll number");
                    criteria = Integer.parseInt(obj.readLine());
                    if (criteria == 1) {
                        BasicDBObject query = new BasicDBObject();
                        System.out.println("Enter name: ");
                        query.put("name", obj.readLine());
                        table.remove(query);
//                        System.out.println("Enter new name: ");
//                        BasicDBObject obj1 = new BasicDBObject();
//                        String newName = obj.readLine();
//                        System.out.println("Enter new Roll number");
//                        String newRollNo = obj.readLine();
//                        //obj1.put("name", obj.readLine());
//                        //obj1.put("RollNumber", obj.readLine());
//                        System.out.println("Enter number of courses");
//                        int noc;
//                        noc = Integer.parseInt(obj.readLine());
//                        ArrayList<String> arrayList = new ArrayList<String>();
//                        for (int i = 0; i < noc; i++) {
//                            System.out.println("Enter course # : " + (i + 1));
//                            arrayList.add(obj.readLine());
//                        }
//
//                        Student newStudent = new Student(newName, newRollNo, arrayList);
//                        obj1.put("name", newStudent.getName());
//                        obj1.put("RollNumber", newStudent.getRoll_no());
//                        for (int i = 0; i < noc; i++) {
//                            String temp = newStudent.getCourses().get(i);
//                            obj1.put("course_" + (i + 1), temp);
//                        }
//                        BasicDBObject updateObject = new BasicDBObject();
//                        updateObject.put("$set", obj1);
//
//                        table.update(query, updateObject);

                    } else if (criteria == 2) {
                        BasicDBObject query = new BasicDBObject();
                        System.out.println("Enter name: ");
                        query.put("name", obj.readLine());
                        table.remove(query);

                    } else {
                        System.out.println("Incorrect criteria");
                    }
                    // Display
                    System.out.println("Results after updation.");
                    BasicDBObject searchQuery3 = new BasicDBObject();
                    DBCursor dbcursor2 = table.find(searchQuery3);
                    while (dbcursor2.hasNext()) {
                        System.out.println(dbcursor2.next());
                    }
                    break;
                case 5:
                    mongoClient = new MongoClient("localhost", 27017);
                    db = mongoClient.getDB("Student");
                    table = db.getCollection("user");
                    //  System.out.println(table.find());
                    DBCursor cursor2 = table.find();

                    while (cursor2.hasNext()) {
                        System.out.println(cursor2.next());
                    }
                    break;
                case 6:
                    System.exit(1);
                    break;
            }
            System.out.println("Enter your choice: ");
            System.out.println("1. Insert");
            System.out.println("2. Find");
            System.out.println("3. Update");
            System.out.println("4. Delete");
            System.out.println("5. Display");
            System.out.println("6. Quit");
            choice = Integer.parseInt(obj.readLine());

        }
    }
}
