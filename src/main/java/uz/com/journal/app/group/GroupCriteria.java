package uz.com.journal.app.group;

import lombok.*;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Builder
public class GroupCriteria {
    private String uuid;
    private String subjectUuid;
}
