
package hello;

import java.util.List;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

@RepositoryRestResource(collectionResourceRel = "people", path = "people")
public interface StudentRepository extends MongoRepository<Student, String> {

	List<Student> findByRollNumber(@Param("rollNumber") String rollNumber);

}
