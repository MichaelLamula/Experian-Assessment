package Assessment.studentmanagementapplication.service.StudentServiceImpl;

import Assessment.studentmanagementapplication.dto.CreateStudentProfileDto;
import Assessment.studentmanagementapplication.dto.StudentDto;
import Assessment.studentmanagementapplication.entity.Student;
import Assessment.studentmanagementapplication.mapper.StudentMapper;
import Assessment.studentmanagementapplication.repository.StudentRepository;
import Assessment.studentmanagementapplication.service.StudentService;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.Date;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class StudentServiceImplTest {

    @Mock
    @Autowired
    private StudentService studentService;

    @Mock
    @Autowired
    private StudentRepository studentRepository;

    @Autowired
    @Mock
    StudentScoreServiceImpl serviceImpl;

    @Mock
    @Autowired
    StudentServiceImpl service;

    @Test
    void should_create_student_profile() {
        Date date = new Date();
        CreateStudentProfileDto createStudentProfileDto = new CreateStudentProfileDto("Mikes","Lamula",date,"+27788887890","kate@gmail.com",50);
        studentService.createStudentProfile(createStudentProfileDto);
    }

    @Test
    void should_return_student_profile_by_student_email(){
        Student student = studentRepository.getStudentsByEmailAddress("mike@gmail.com");
        StudentDto studentDto = StudentMapper.mapToStudentDto(student);
        assertEquals("MichaelLamula",studentDto.getStudentNumber());
        assertEquals("Michael",studentDto.getFirstName());
        assertEquals("Lamula",studentDto.getLastName());
    }

    @Test
    void should_return_student_profile_by_first_name(){
        List<StudentDto> studentDtoList = service.findStudentByFirstName("Michael");
        assertEquals("MichaelLamula",studentDtoList.get(0).getStudentNumber());
        assertEquals("Michael",studentDtoList.get(0).getFirstName());
        assertEquals("Lamula",studentDtoList.get(0).getLastName());
    }

}