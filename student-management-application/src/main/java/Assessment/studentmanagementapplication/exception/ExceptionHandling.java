package Assessment.studentmanagementapplication.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(value = HttpStatus.NOT_FOUND)
public class ExceptionHandling extends RuntimeException{
    public ExceptionHandling(String message){
        super(message);
    }
}
