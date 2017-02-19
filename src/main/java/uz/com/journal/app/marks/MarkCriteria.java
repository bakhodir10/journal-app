package uz.com.journal.app.marks;

import lombok.*;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Builder
public class MarkCriteria {
    private String uuid;
    private String groupUuid;
    private String subjectUuid;
}
