package uz.com.journal.app.student;


import lombok.*;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Builder
public class StudentItem {
    private String name;
    private String group;
    private String subject;
}
