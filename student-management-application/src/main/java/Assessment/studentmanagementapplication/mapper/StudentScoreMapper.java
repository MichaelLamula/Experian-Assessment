package Assessment.studentmanagementapplication.mapper;

import Assessment.studentmanagementapplication.dto.StudentScoreDto;
import Assessment.studentmanagementapplication.entity.StudentScores;

public class StudentScoreMapper {

    public static StudentScoreDto mapToStudentScoreDto(StudentScores studentScore){
        return new StudentScoreDto(studentScore.getId(), studentScore.getStudentNumber(), studentScore.getScore());
    }

    public static StudentScores mapToStudentScore(StudentScoreDto studentScoreDto){
        return new StudentScores(studentScoreDto.getId(), studentScoreDto.getStudentNumber(), studentScoreDto.getScore());
    }
}
