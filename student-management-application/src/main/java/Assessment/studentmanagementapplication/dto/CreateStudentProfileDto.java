package Assessment.studentmanagementapplication.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.Date;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class CreateStudentProfileDto {
    private String firstName;
    private String lastName;
    private Date dateOfBirth;
    private String cellPhoneNumber;
    private String emailAddress;
    private int currentScore;
}
