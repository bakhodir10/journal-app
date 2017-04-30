package uz.com.journal.app.attendance;


import com.fasterxml.jackson.annotation.JsonBackReference;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.Where;
import uz.com.journal.app.group.Group;
import uz.com.journal.app.marks.Mark;
import uz.com.journal.core.NonDeletable;

import javax.persistence.*;
import java.util.Date;

@Getter
@Setter
@Entity
@Table(name = "attendance")
public class Attendance extends NonDeletable {
    @Column(name = "date")
    private Date date;

    @JsonBackReference
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "group_uuid")
    private Group group;
}
