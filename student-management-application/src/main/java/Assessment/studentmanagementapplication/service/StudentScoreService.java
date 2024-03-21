package Assessment.studentmanagementapplication.service;

import Assessment.studentmanagementapplication.dto.StudentScoreDto;
import Assessment.studentmanagementapplication.entity.StudentScores;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface StudentScoreService {
    StudentScoreDto createStudentScore(StudentScoreDto studentScoreDto);
    long getCount(String studentNo);
    List<StudentScores> getAllStudentScoresByStudentNumber(String studentNo);

}
