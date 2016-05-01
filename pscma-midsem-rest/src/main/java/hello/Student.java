package hello;

import org.springframework.data.annotation.Id;

public class Student {

    @Id
    private String id;

    private String firstName, rollNumber, course;

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
//
//    public String getId() {
//        return id;
//    }

    public String getCourse() {
        return course;
    }

    public void setCourse(String course) {
        this.course = course;
    }
    

}
