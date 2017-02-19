package uz.com.journal.app.subject;

import com.fasterxml.jackson.annotation.JsonManagedReference;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.Where;
import uz.com.journal.app.group.Group;
import uz.com.journal.core.NonDeletable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import java.util.Date;
import java.util.Set;

@Getter
@Setter
@Entity
@Table(name = "subjects")
public class Subject extends NonDeletable {

    @Column(name = "name")
    private String name;
    @Column(name = "time")
    private Date time;
    @Column(name = "semester")
    private Short semester;

    @JsonManagedReference
    @OneToMany(mappedBy = "subject")
    @Where(clause = "deleted = false")
    private Set<Group> groups;

    /* UI da Select2 plugin ishlatiladi, Select2 ga text property berish kerak, shunchun qo'shimcha method qo'shildi */
    public String getText() {
        return name;
    }
}
