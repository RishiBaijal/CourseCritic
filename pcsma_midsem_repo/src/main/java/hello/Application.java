package hello;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class Application implements CommandLineRunner {

    @Autowired
    private StudentRepository repository;

    public static void main(String[] args) {
        SpringApplication.run(Application.class, args);
    }

    @Override
    public void run(String... args) throws Exception {
        InputStreamReader isr = new InputStreamReader(System.in);
        BufferedReader obj = new BufferedReader(isr);
        repository.deleteAll();
        int choice;
        do {
            System.out.println("Enter your choice: ");
            System.out.println("1. Insert");
            System.out.println("2. Find");
            System.out.println("3. Update");
            System.out.println("4. Delete");
            System.out.println("5. Display");
            System.out.println("6. Quit");
            choice = Integer.parseInt(obj.readLine());
            switch (choice) {
                case 1:
                    System.out.println("Enter student name");
                    String name = obj.readLine();
                    System.out.println("Enter student roll number");
                    String roll = obj.readLine();
                    System.out.println("Enter course");
                    String course = obj.readLine();
                    Student student1 = new Student(name, roll, course);
                    repository.save(student1);
                    break;
                case 2:
                    System.out.println("Select criteria: ");
                    System.out.println("1. Name");
                    System.out.println("2. Roll number");
                    int criteria;
                    criteria = Integer.parseInt(obj.readLine());
                    if (criteria == 1) {
                        System.out.println("Enter name");
                        String tempName = obj.readLine();
                        System.out.println(repository.findByFirstName(tempName));
                    } else if (criteria == 2) {
                        System.out.println("Enter roll number");
                        String tempRoll = obj.readLine();
                        System.out.println(repository.findByRollNumber(tempRoll));
                    } else {
                        System.out.println("Incorrect criteria");
                    }
                    break;
                case 3:
                    System.out.println("Select criteria: ");
                    System.out.println("1. Name");
                    System.out.println("2. Roll number");
                    criteria = Integer.parseInt(obj.readLine());
                    if (criteria == 1) {
                        System.out.println("Enter old name");
                        String tempName = obj.readLine();
                        System.out.println("Enter new name");
                        String newName = obj.readLine();
                        System.out.println("Enter roll number.");
                        String rollNo = obj.readLine();
                        System.out.println("Enter course");
                        String newCourse = obj.readLine();
                        Student newStudent = new Student(newName, rollNo, newCourse);
                        System.out.println(repository.deleteByFirstName(tempName));
                        System.out.println(repository.save(newStudent));

                    } else if (criteria == 2) {
                        System.out.println("Enter old roll number");
                        String tempRoll = obj.readLine();
                        System.out.println("Enter new name");
                        String newName = obj.readLine();
                        System.out.println("Enter roll number.");
                        String rollNo = obj.readLine();
                        System.out.println("Enter course");
                        String newCourse = obj.readLine();
                        Student newStudent = new Student(newName, rollNo, newCourse);
                        System.out.println(repository.deleteByRollNumber(tempRoll));
                        System.out.println(repository.save(newStudent));
                    }
                    break;
                case 4:
                    System.out.println("Select criteria: ");
                    System.out.println("1. Name");
                    System.out.println("2. Roll number");
                    criteria = Integer.parseInt(obj.readLine());
                    if (criteria == 1) {
                        System.out.println("Enter name");
                        String tempName = obj.readLine();
                        System.out.println(repository.deleteByFirstName(tempName));
                    } else if (criteria == 2) {
                        System.out.println("Enter roll number");
                        String tempRoll = obj.readLine();
                        System.out.println(repository.deleteByRollNumber(tempRoll));
                    }
                    break;
                case 5:
                    for (Student student : repository.findAll()) {
                        System.out.println(student);
                    }
                    break;
                case 6:
                    System.exit(0);
                    break;
            }

        } while (choice != 6);

    }

}
