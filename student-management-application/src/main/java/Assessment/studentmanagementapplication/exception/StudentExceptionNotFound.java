package Assessment.studentmanagementapplication.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(value = HttpStatus.NOT_FOUND)
public class StudentExceptionNotFound extends RuntimeException{
    public StudentExceptionNotFound(String message){
        super(message);
    }

}
