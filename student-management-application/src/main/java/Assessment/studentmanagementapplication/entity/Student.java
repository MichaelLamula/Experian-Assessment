package Assessment.studentmanagementapplication.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Entity(name = "Students")
@Table(name = "Students")
public class Student {
    @Id
    @Column(name = "student_number", nullable = false, unique = true)
    private String studentNumber;

    @Column(name = "first_name", nullable = false)
    private String firstName;

    @Column(name = "last_name", nullable = false)
    private String lastName;

    @Column(name = "date_of_birth")
    private String dateOfBirth;

    @Column(name = "cell_phone_number")
    private String cellPhoneNumber;

    @Column(name = "email_address",unique = true)
    private String emailAddress;

    @Column(name = "current_score")
    private int currentScore;

    @Column(name = "average_score")
    private int averageScore;


    public Student(String firstName, String lastName, String dateOfBirth, String cellPhoneNumber, String emailAddress, int currentScore, int averageScore) {
        this.studentNumber = generateStudentNumber(firstName,lastName);
        this.firstName = firstName;
        this.lastName = lastName;
        this.dateOfBirth = dateOfBirth;
        this.cellPhoneNumber = cellPhoneNumber;
        this.emailAddress = emailAddress;
        this.currentScore = currentScore;
        this.averageScore = averageScore;
    }

    private String generateStudentNumber(String firstName,String lastName){
        return firstName+lastName;
    }
}
