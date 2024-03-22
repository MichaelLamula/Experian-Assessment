package Assessment.studentmanagementapplication.service.StudentServiceImpl;

import Assessment.studentmanagementapplication.dto.CreateStudentProfileDto;
import Assessment.studentmanagementapplication.dto.StudentDto;
import Assessment.studentmanagementapplication.entity.Student;
import Assessment.studentmanagementapplication.exception.StudentExceptionNotFound;
import Assessment.studentmanagementapplication.mapper.StudentMapper;
import Assessment.studentmanagementapplication.repository.StudentRepository;
import Assessment.studentmanagementapplication.service.StudentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Logger;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

@Service
@Repository
public class StudentServiceImpl implements StudentService {
    private static final Logger LOGGER = Logger.getLogger(StudentServiceImpl.class.getName());
    private List<StudentDto> studentDtoList;
    private StudentDto studentDto;
    @Autowired
    private StudentRepository studentRepository;
    @Autowired
    private StudentScoreServiceImpl studentScoreService;

    private void inputValidator(CreateStudentProfileDto studentProfileDto) {
        if (studentProfileDto.getCurrentScore() <= 0 || studentProfileDto.getCurrentScore() >= 100) {
            LOGGER.info("Current score doesnt not meet the requirements");
            throw new StudentExceptionNotFound("Current score must no be less than 0 or greater than 100");
        }
        if (StringUtils.isEmpty(studentProfileDto.getFirstName()) || StringUtils.isEmpty(studentProfileDto.getLastName())) {
            LOGGER.info("First name and Last name must be provided");
            throw new StudentExceptionNotFound("Need to provide both First name and Last name ");
        }
    }

    private void cellNumberValidator(String cellNumber) {
        String pattern = "^((\\+27)[6-8][0-9]{8})";
        if (!cellNumber.matches(pattern)) {
            LOGGER.info("Invalid cell number");
            throw new StudentExceptionNotFound("The cell number you provided is not valid");
        }
    }

    private boolean emailValidator(String email) {
        final String EMAIL_REGEX = "^[a-zA-Z0-9_+&*-]+(?:\\.[a-zA-Z0-9_+&*-]+)*@(?:[a-zA-Z0-9-]+\\.)+[a-zA-Z]{2,7}$";
        final Pattern pattern = Pattern.compile(EMAIL_REGEX);
        Matcher matcher = pattern.matcher(email);

        if (!matcher.matches()) {
            LOGGER.info("Email provided is invalid");
            throw new StudentExceptionNotFound("Invalid email");
        }

        return matcher.matches();
    }

    private void checkIfStudentExist(String studentNo) {
        if (studentRepository.getStudent(studentNo) != null) {
            throw new StudentExceptionNotFound("Student Already Exist");
        }
    }

    private void checkIfEmailIsTaken(String email){
        if(studentRepository.getStudentsByEmailAddress(email) != null){
            throw new StudentExceptionNotFound("Email already taken try another one");
        }
    }

    @Override
    public CreateStudentProfileDto createStudentProfile(CreateStudentProfileDto createStudentProfileDto) {
        checkIfStudentExist(createStudentProfileDto.getFirstName() + createStudentProfileDto.getLastName());
        inputValidator(createStudentProfileDto);
        emailValidator(createStudentProfileDto.getEmailAddress());
        checkIfEmailIsTaken(createStudentProfileDto.getEmailAddress());
        cellNumberValidator(createStudentProfileDto.getCellPhoneNumber());
        Student student = StudentMapper.mapToStudent(createStudentProfileDto);
        studentRepository.save(student);
        return StudentMapper.mapToCreateStudentProfileDto(student);
    }


    @Override
    public StudentDto findStudentByStudentNumber(String studentNumber) {
        Student student = studentRepository.findById(studentNumber).orElseThrow(() -> new StudentExceptionNotFound("Student with student number : " + studentNumber + " does not exist"));

        StudentDto studentDto = StudentMapper.mapToStudentDto(student);
        studentDto.setCurrentScore(studentScoreService.getCurrentScore((student.getStudentNumber())));
        studentDto.setAverageScore(studentScoreService.getAverageScore(student.getStudentNumber()));

        return studentDto;
    }

    @Override
    public List<StudentDto> findStudentByFirstName(String firstName) {
        List<Student> students = studentRepository.getStudentsByFirstName(firstName);
        studentDtoList = new ArrayList<>();

        for (Student student : students) {
            studentDto = StudentMapper.mapToStudentDto(student);
            studentDto.setCurrentScore(studentScoreService.getCurrentScore(student.getStudentNumber()));
            studentDto.setAverageScore(studentScoreService.getAverageScore(student.getStudentNumber()));
            studentDtoList.add(studentDto);
        }

        return studentDtoList;
    }

    @Override
    public List<StudentDto> findStudentByLastName(String lastName) {
        List<Student> students = studentRepository.getStudentsByLastName(lastName);
        studentDtoList = new ArrayList<>();

        for (Student student : students) {
            studentDto = StudentMapper.mapToStudentDto(student);
            studentDto.setCurrentScore(studentScoreService.getCurrentScore(student.getStudentNumber()));
            studentDto.setAverageScore(studentScoreService.getAverageScore(student.getStudentNumber()));
            studentDtoList.add(studentDto);
        }
        return studentDtoList;
    }

    @Override
    public StudentDto findStudentByEmail(String email) {
        emailValidator(email);
        Student student = studentRepository.getStudentsByEmailAddress(email);
        studentDto = StudentMapper.mapToStudentDto(student);
        studentDto.setAverageScore(studentScoreService.getAverageScore(student.getStudentNumber()));
        studentDto.setCurrentScore(studentScoreService.getCurrentScore(student.getStudentNumber()));
        return studentDto;
    }

    @Override
    public List<StudentDto> getAllStudentProfiles() {
        List<Student> students = studentRepository.findAll();
        studentDtoList = new ArrayList<>();

        for (Student student : students) {
            studentDto = StudentMapper.mapToStudentDto(student);
            studentDto.setCurrentScore(studentScoreService.getCurrentScore(student.getStudentNumber()));
            studentDto.setAverageScore(studentScoreService.getAverageScore(student.getStudentNumber()));
            studentDtoList.add(studentDto);
        }

        return studentDtoList;
    }

    @Override
    public StudentDto updateStudentProfile(String studentNo, StudentDto studentDto) {
        Student student = studentRepository.findById(studentNo).orElseThrow(() -> new StudentExceptionNotFound("Student with student number : " + studentNo + " does not exist"));
        SimpleDateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");

        if (StringUtils.isEmpty(studentDto.getFirstName())) {
            student.setFirstName(student.getFirstName());
        } else {
            student.setFirstName(studentDto.getFirstName());
        }

        if (StringUtils.isEmpty(studentDto.getLastName())) {
            student.setLastName(student.getLastName());
        } else {
            student.setLastName(studentDto.getLastName());
        }

        if (studentDto.getDateOfBirth() == null) {
            student.setDateOfBirth(student.getDateOfBirth());
        } else {
            student.setDateOfBirth(dateFormat.format(studentDto.getDateOfBirth()));
        }

        if (StringUtils.isEmpty(studentDto.getCellPhoneNumber())) {
            student.setCellPhoneNumber(student.getCellPhoneNumber());
        } else {
            cellNumberValidator(studentDto.getCellPhoneNumber());
            student.setCellPhoneNumber(studentDto.getCellPhoneNumber());
        }

        if (StringUtils.isEmpty(studentDto.getEmailAddress())) {
            student.setEmailAddress(student.getEmailAddress());
        } else {
            emailValidator(studentDto.getEmailAddress());
            student.setEmailAddress(studentDto.getEmailAddress());
        }

        Student updatedStudent = studentRepository.save(student);

        return StudentMapper.mapToStudentDto(updatedStudent);
    }

    @Override
    public void deleteStudentProfile(String studentNumber) {
        Student student = studentRepository.findById(studentNumber).orElseThrow(() -> new StudentExceptionNotFound("Student with student number : " + studentNumber + " does not exist"));
        studentRepository.deleteById(studentNumber);
    }
}
