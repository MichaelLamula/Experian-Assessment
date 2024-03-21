package Assessment.studentmanagementapplication.exception;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class StudentExceptionHandler {
    @ExceptionHandler(value = {StudentExceptionNotFound.class})
    public ResponseEntity<Object> handleStudentException(StudentExceptionNotFound studentExceptionNotFound){
        StudentException studentException = new StudentException(studentExceptionNotFound.getMessage(),studentExceptionNotFound.getCause(), HttpStatus.NOT_FOUND);
        return new ResponseEntity<>(studentException,HttpStatus.NOT_FOUND);
    }
}
