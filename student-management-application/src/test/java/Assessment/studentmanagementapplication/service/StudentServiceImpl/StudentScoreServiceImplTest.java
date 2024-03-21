package Assessment.studentmanagementapplication.service.StudentServiceImpl;

import Assessment.studentmanagementapplication.dto.CreateStudentProfileDto;
import Assessment.studentmanagementapplication.dto.StudentScoreDto;
import Assessment.studentmanagementapplication.entity.StudentScores;
import Assessment.studentmanagementapplication.mapper.StudentScoreMapper;
import Assessment.studentmanagementapplication.repository.StudentScoreRepository;
import Assessment.studentmanagementapplication.service.StudentScoreService;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;



import static org.junit.jupiter.api.Assertions.assertEquals;

@SpringBootTest
class StudentScoreServiceImplTest {
    @Mock
    @Autowired
    private StudentScoreService studentScoreService;

    @Mock
    @Autowired
    private StudentScoreRepository studentScoreRepository;

    @Autowired
    @Mock
    StudentScoreServiceImpl service;


    @Test
    void shouls_return_student_average_by_student_number() {
        int average = service.getAverageScore("SusanNkosi");
        assertEquals(60, average);
    }

    @Test
    void should_return_student_score_by_student_number() {
        long studentScores = studentScoreRepository.getCountByStudentNumber("SusanNkosi");
        assertEquals(studentScores, studentScoreService.getCount("SusanNkosi"));
    }

    @Test
    void should_add_student_score_by_student_number() {
        StudentScores studentScores = StudentScoreMapper.mapToStudentScore(setUpRequestData());
        studentScoreService.createStudentScore(StudentScoreMapper.mapToStudentScoreDto(studentScores));
        service.addScore(new CreateStudentProfileDto());
    }

    static StudentScoreDto setUpRequestData() {
        return new StudentScoreDto(12L, "SusanNkosi", 60);
    }

}