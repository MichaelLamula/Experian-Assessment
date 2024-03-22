package Assessment.studentmanagementapplication.service.StudentServiceImpl;

import Assessment.studentmanagementapplication.dto.CreateStudentProfileDto;
import Assessment.studentmanagementapplication.dto.StudentScoreDto;;
import Assessment.studentmanagementapplication.entity.StudentScores;
import Assessment.studentmanagementapplication.exception.StudentExceptionNotFound;
import Assessment.studentmanagementapplication.mapper.StudentScoreMapper;
import Assessment.studentmanagementapplication.repository.StudentScoreRepository;
import Assessment.studentmanagementapplication.service.StudentScoreService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.logging.Logger;


@Service
public class StudentScoreServiceImpl implements StudentScoreService {
    private static final Logger LOGGER = Logger.getLogger(StudentScoreServiceImpl.class.getName());
    @Autowired
    private StudentScoreRepository studentScoreRepository;

    @Override
    public StudentScoreDto createStudentScore(StudentScoreDto studentScoreDto) {
        scoreValidation(studentScoreDto.getScore());
        StudentScores studentScores = StudentScoreMapper.mapToStudentScore(studentScoreDto);
        studentScoreRepository.save(studentScores);
        return StudentScoreMapper.mapToStudentScoreDto(studentScores);
    }

    @Override
    public long getCount(String studentNo) {
        return studentScoreRepository.getCountByStudentNumber(studentNo);
    }

    public int getCurrentScore(String studentNo) {
        int currentScore = getAllStudentScoresByStudentNumber(studentNo).size() - 1;
        return getAllStudentScoresByStudentNumber(studentNo).get(currentScore).getScore();
    }

    public int getAverageScore(String studentNo) {
        long count = getCount(studentNo);
        long average = 0;
        int sum = 0;

        try {
            if (count != 0) {
                for (StudentScores studentScore : getAllStudentScoresByStudentNumber(studentNo)) {
                    sum += studentScore.getScore();
                }
                average = sum / count;
            }
        } catch (ArithmeticException exception) {
            LOGGER.info("Any number divide by zero is undefined");
            throw new StudentExceptionNotFound("Any number divide by zero is undefined");
        }
        return (int) average;
    }

    @Override
    public List<StudentScores> getAllStudentScoresByStudentNumber(String studentNo) {
        return studentScoreRepository.getStudentScoresByStudentNumber(studentNo);
    }

    public void addScore(CreateStudentProfileDto student) {
        StudentScoreDto studentScoreDto = new StudentScoreDto();
        String studentNo = student.getFirstName() + student.getLastName();
        studentScoreDto.setStudentNumber(studentNo);
        studentScoreDto.setScore(student.getCurrentScore());
        StudentScores addedScore = StudentScoreMapper.mapToStudentScore(studentScoreDto);
        studentScoreRepository.save(addedScore);
    }

    public void deleteAllScores(String studentNo){
        List<StudentScores> students  = studentScoreRepository.getStudentScoresByStudentNumber(studentNo);
        for (StudentScores student:students) {
            studentScoreRepository.delete(student);
        }
    }

    private void scoreValidation(int score) {
        if (score <= 0 || score > 100) {
            LOGGER.info("score doesnt meat the requirements");
            throw new StudentExceptionNotFound("Score must be more than 0 and not more than 100");
        }
    }
}
