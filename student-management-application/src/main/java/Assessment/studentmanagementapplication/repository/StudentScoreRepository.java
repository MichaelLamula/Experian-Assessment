package Assessment.studentmanagementapplication.repository;

import Assessment.studentmanagementapplication.entity.StudentScores;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
@Repository
public interface StudentScoreRepository extends JpaRepository<StudentScores, Long> {
    @Query("SELECT sc FROM StudentScores sc WHERE sc.studentNumber = :studentNo")
    List<StudentScores> getStudentScoresByStudentNumber(String studentNo);
    @Query("SELECT COUNT(*) FROM StudentScores sc WHERE sc.studentNumber = :studentNo")
    long getCountByStudentNumber(String studentNo);

}
