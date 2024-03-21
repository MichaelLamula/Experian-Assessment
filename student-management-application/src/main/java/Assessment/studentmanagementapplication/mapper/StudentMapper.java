package Assessment.studentmanagementapplication.mapper;

import Assessment.studentmanagementapplication.dto.StudentDto;
import Assessment.studentmanagementapplication.entity.Student;


import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class StudentMapper {

    public static StudentDto mapToStudentDto(Student student) {
        SimpleDateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");

        Date dateOfBirth = null;
        try {
            dateOfBirth = dateFormat.parse(student.getDateOfBirth());
        } catch (ParseException e) {
            e.getStackTrace();
        }

        return new StudentDto(student.getStudentNumber(), student.getFirstName(),
                student.getLastName(), dateOfBirth,
                student.getCellPhoneNumber(), student.getEmailAddress(), student.getAverageScore(),
                student.getCurrentScore());
    }

    public static Student mapToStudent(StudentDto studentDto) {

        SimpleDateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");
        return new Student(studentDto.getFirstName(),
                studentDto.getLastName(), dateFormat.format(studentDto.getDateOfBirth()),
                studentDto.getCellPhoneNumber(), studentDto.getEmailAddress(),
                studentDto.getCurrentScore(), studentDto.getAverageScore());
    }

}
