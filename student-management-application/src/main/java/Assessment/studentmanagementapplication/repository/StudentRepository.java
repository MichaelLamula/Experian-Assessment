package Assessment.studentmanagementapplication.repository;

import Assessment.studentmanagementapplication.entity.Student;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;


import java.util.List;

@Repository
public interface StudentRepository extends JpaRepository<Student, String> {
    @Query("SELECT sp FROM Students sp WHERE sp.firstName = :firstName")
    List<Student> getStudentsByFirstName(String firstName);

    @Query("SELECT sp FROM Students sp WHERE sp.lastName = :lastName")
    List<Student> getStudentsByLastName(String lastName);

    @Query("SELECT sp FROM Students sp WHERE sp.emailAddress = :lastName")
    Student getStudentsByEmailAddress(String lastName);
}
