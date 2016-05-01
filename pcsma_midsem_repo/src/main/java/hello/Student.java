package hello;

import org.springframework.data.annotation.Id;


public class Student {

    @Id
    private String id;

    private String firstName;
    private String rollNumber;
    private String course;

    public Student() {}

    public Student(String firstName, String rollNumber, String course) {
        this.firstName = firstName;
        this.rollNumber = rollNumber;
        this.course = course;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getRollNumber() {
        return rollNumber;
    }

    public void setRollNumber(String rollNumber) {
        this.rollNumber = rollNumber;
    }

    public String getCourse() {
        return course;
    }

    public void setCourse(String course) {
        this.course = course;
    }
    
    
    @Override
    public String toString() {
        return id + " " + firstName + " " + rollNumber;
    }

}

