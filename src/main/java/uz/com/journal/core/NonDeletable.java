package uz.com.journal.core;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Getter;
import lombok.Setter;
import uz.com.platform.app.core.BaseEntity;
import uz.com.platform.app.users.User;

import javax.persistence.*;
import java.util.Date;

@Getter
@Setter
@MappedSuperclass
public abstract class NonDeletable extends BaseEntity {

    @JsonIgnore
    @Column(name = "deleted")
    private Boolean deleted = Boolean.FALSE;

    @JsonIgnore
    @Column(name = "deleted_at")
    private Date deletedAt;


    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "deleted_by_uuid")
    @JsonIgnore
    private User deletedBy;
}
