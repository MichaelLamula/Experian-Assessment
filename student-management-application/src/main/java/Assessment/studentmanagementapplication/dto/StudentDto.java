package Assessment.studentmanagementapplication.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.Date;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class StudentDto {
    private String studentNumber;
    private String firstName;
    private String lastName;
    private Date dateOfBirth;
    private String cellPhoneNumber;
    private String emailAddress;
    private int currentScore;
    private int averageScore;
}
