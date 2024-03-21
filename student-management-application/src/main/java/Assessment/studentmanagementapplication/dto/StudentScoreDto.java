package Assessment.studentmanagementapplication.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class StudentScoreDto {
    private long id;
    private String studentNumber;
    private int score;
}
