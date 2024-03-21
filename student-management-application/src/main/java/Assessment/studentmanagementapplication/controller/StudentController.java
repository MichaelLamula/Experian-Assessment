package Assessment.studentmanagementapplication.controller;

import Assessment.studentmanagementapplication.dto.CreateStudentProfileDto;
import Assessment.studentmanagementapplication.dto.StudentDto;
import Assessment.studentmanagementapplication.dto.StudentScoreDto;
import Assessment.studentmanagementapplication.service.StudentScoreService;
import Assessment.studentmanagementapplication.service.StudentService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@RestController
@RequestMapping("/api/students")
public class StudentController {
    private StudentService studentService;
    private StudentScoreService studentScoreService;

    public StudentController(StudentService studentService, StudentScoreService studentScoreService) {
        this.studentService = studentService;
        this.studentScoreService = studentScoreService;
    }

    @PostMapping("/addStudent")
    public ResponseEntity<CreateStudentProfileDto> createStudentProfile(@RequestBody CreateStudentProfileDto createStudentProfileDto) {
        CreateStudentProfileDto savedStudent = studentService.createStudentProfile(createStudentProfileDto);
        StudentScoreDto studentScoreDto = new StudentScoreDto();
        String studentNo = createStudentProfileDto.getFirstName() + createStudentProfileDto.getLastName();
        studentScoreDto.setStudentNumber(studentNo);
        studentScoreDto.setScore(createStudentProfileDto.getCurrentScore());
        studentScoreService.createStudentScore(studentScoreDto);
        return new ResponseEntity<>(savedStudent, HttpStatus.CREATED);
    }

    @PostMapping("/addingScore")
    public ResponseEntity<StudentScoreDto> addStudentScore(@RequestBody StudentScoreDto studentScoreDto) {
        StudentScoreDto addedScore = studentScoreService.createStudentScore(studentScoreDto);
        return new ResponseEntity<>(addedScore, HttpStatus.CREATED);
    }

    @GetMapping("/getByStudentNumber/{studentNumber}")
    public ResponseEntity<StudentDto> getStudentByStudentNumber(@PathVariable("studentNumber") String studentNo) {
        StudentDto student = studentService.findStudentByStudentNumber(studentNo);
        return ResponseEntity.ok(student);
    }

    @GetMapping("firstName/{firstName}")
    public ResponseEntity<List<StudentDto>> getStudentProfileByFirstName(@PathVariable("firstName") String firstName) {
        List<StudentDto> students = studentService.findStudentByFirstName(firstName);
        return ResponseEntity.ok(students);
    }

    @GetMapping("/lastName/{lastName}")
    public ResponseEntity<List<StudentDto>> getStudentProfileByLastName(@PathVariable("lastName") String lastName) {
        List<StudentDto> students = studentService.findStudentByLastName(lastName);
        return ResponseEntity.ok(students);
    }

    @GetMapping("/email/{email}")
    public ResponseEntity<StudentDto> getStudentByEmail(@PathVariable("email") String email) {
        StudentDto student = studentService.findStudentByEmail(email);
        return ResponseEntity.ok(student);
    }

    @GetMapping("/getAllStudents")
    public ResponseEntity<List<StudentDto>> getAllStudentProfiles() {
        List<StudentDto> students = studentService.getAllStudentProfiles();
        return ResponseEntity.ok(students);
    }

    @PutMapping("/studentNumber/{studentNumber}")
    public ResponseEntity<StudentDto> updateStudentProfile(@PathVariable("studentNumber") String studentNo,
                                                           @RequestBody StudentDto studentDto) {
        StudentDto student = studentService.updateStudentProfile(studentNo, studentDto);
        return ResponseEntity.ok(student);
    }

    @DeleteMapping("/delete/{studentNumber}")
    public ResponseEntity<String> deleteStudentProfile(@PathVariable("studentNumber") String studentNo) {
        studentService.deleteStudentProfile(studentNo);
        return ResponseEntity.ok("Student profile deleted successfully");
    }
}
