package uz.com.journal.app.student;

import lombok.*;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Builder
public class StudentCriteria {
    private String uuid;
    private String groupUuid;
    private String subjectUuid;
}
