package uz.com.journal.app.attendance;


import lombok.*;

import java.util.Date;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Builder
public class AttendanceItem {
    private Date date;
}
