package uz.com.journal.app.marks;

import com.fasterxml.jackson.annotation.JsonBackReference;
import lombok.Getter;
import lombok.Setter;
import uz.com.journal.app.student.Student;
import uz.com.journal.core.NonDeletable;

import javax.persistence.*;

@Getter
@Setter
@Entity
@Table(name = "marks")
public class Mark extends NonDeletable {

    @JsonBackReference
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "student_uuid")
    private Student student;

    @Column(name = "status")
    private String status;
}