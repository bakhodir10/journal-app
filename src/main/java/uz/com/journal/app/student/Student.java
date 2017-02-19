package uz.com.journal.app.student;


import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.*;
import uz.com.journal.app.marks.Mark;
import uz.com.journal.app.group.Group;
import uz.com.journal.core.NonDeletable;

import javax.persistence.*;
import javax.persistence.Entity;
import javax.persistence.OrderBy;
import javax.persistence.Table;
import java.util.Set;

@Getter
@Setter
@Entity
@Table(name = "students")
public class Student extends NonDeletable {

    @Column(name = "name")
    private String name;

    @JsonBackReference
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "group_uuid")
    private Group group;

    @JsonManagedReference
    @OneToMany(mappedBy = "student")
    @Where(clause = "deleted = false")
    @OrderBy(value = "assign_date ASC")
    private Set<Mark> marks;
}
