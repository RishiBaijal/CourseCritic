/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package in.iiitd.rishi;

import java.util.ArrayList;

/**
 *
 * @author Apple
 */
public class Student {
    private String name, roll_no;
    private ArrayList<String> courses;

    public Student(String name, String roll_no, ArrayList<String> courses){
        
        this.name = name;
        this.roll_no = roll_no;
        this.courses = courses;
    }

    
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getRoll_no() {
        return roll_no;
    }

    public void setRoll_no(String roll_no) {
        this.roll_no = roll_no;
    }

    public ArrayList<String> getCourses() {
        return courses;
    }

    public void setCourses(ArrayList<String> courses) {
        this.courses = courses;
    }
    
    
}
