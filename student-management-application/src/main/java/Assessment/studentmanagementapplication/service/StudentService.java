package Assessment.studentmanagementapplication.service;

import Assessment.studentmanagementapplication.dto.CreateStudentProfileDto;
import Assessment.studentmanagementapplication.dto.StudentDto;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface StudentService {
    CreateStudentProfileDto createStudentProfile(CreateStudentProfileDto createStudentProfileDto);

    StudentDto findStudentByStudentNumber(String studentNumber);

    List<StudentDto> findStudentByFirstName(String firstName);

    List<StudentDto> findStudentByLastName(String lastName);

    StudentDto findStudentByEmail(String email);

    List<StudentDto> getAllStudentProfiles();

    StudentDto updateStudentProfile(String studentNo, StudentDto studentDto);

    void deleteStudentProfile(String studentNumber);
}
