package hello;

import java.util.List;

import org.springframework.data.mongodb.repository.MongoRepository;

public interface StudentRepository extends MongoRepository<Student, String> {

    public Student findByFirstName(String firstName);
    public List<Student> findByRollNumber(String rollNumber);
    public List<Student> deleteByFirstName(String firstName);
    public List<Student> deleteByRollNumber(String rollNumber);  

}
