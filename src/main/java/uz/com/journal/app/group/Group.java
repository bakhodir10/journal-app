package uz.com.journal.app.group;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.Where;
import uz.com.journal.app.attendance.Attendance;
import uz.com.journal.app.student.Student;
import uz.com.journal.app.subject.Subject;
import uz.com.journal.core.NonDeletable;

import javax.persistence.*;
import java.util.Set;

@Getter
@Setter
@Entity
@Table(name = "groups")
public class Group extends NonDeletable {

    @Column(name = "name")
    private String name;

    @JsonBackReference
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "subject_uuid")
    @Where(clause = "deleted = false")
    private Subject subject;

    @JsonManagedReference
    @OneToMany(mappedBy = "group")
    @Where(clause = "deleted = false")
    private Set<Student> students;

    @JsonManagedReference
    @OneToMany(mappedBy = "group")
    @Where(clause = "deleted = false")
    @OrderBy(value = "date ASC")
    private Set<Attendance> attendances;
}
