package uz.com.journal.app.marks;

import com.fasterxml.jackson.annotation.JsonBackReference;
import lombok.Getter;
import lombok.Setter;
import uz.com.journal.app.student.Student;
import uz.com.journal.app.subject.Subject;
import uz.com.journal.core.NonDeletable;

import javax.persistence.*;
import java.util.Date;

@Getter
@Setter
@Entity
@Table(name = "marks")
public class Mark extends NonDeletable {

    @JsonBackReference
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "student_uuid")
    private Student student;

    @ManyToOne(fetch = FetchType.LAZY)
    private Subject subject;

    @Column(name = "assign_date")
    private Date date;

    @Column(name = "status")
    private String status;
}
// Royal education