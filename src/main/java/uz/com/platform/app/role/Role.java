package uz.com.platform.app.role;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import uz.com.platform.app.users.Permission;
import uz.com.journal.core.NonDeletable;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

@Getter
@Setter
@Entity
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "roles")
public class Role extends NonDeletable {
    @Column(name = "name")
    private String name;

    @ElementCollection
    @CollectionTable(name = "roles_permissions")
    private Set<Permission> permissions = new HashSet<>();
}
