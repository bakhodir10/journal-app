package uz.com.journal.app.marks;


import lombok.*;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Builder
public class MarkItem {
    private String status;
    private String studentUuid;
}
