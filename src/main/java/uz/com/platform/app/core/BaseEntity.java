package uz.com.platform.app.core;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Getter;
import lombok.Setter;
import uz.com.platform.app.users.User;

import javax.persistence.*;
import java.util.Date;
import java.util.UUID;

@Getter
@MappedSuperclass
public abstract class BaseEntity {

    @Column(name = "id")
    private Long id;

    @Id
    @Column(name = "uuid")
    private String uuid = UUID.randomUUID().toString();

    @Column(name = "created_at")
    private Date createdAt = new Date();

    @Setter
    @JsonIgnore
    @Column(name = "modified_at")
    private Date modifiedAt;

    @Setter
    @JsonIgnore
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "created_by_uuid")
    private User createdBy;


    @Setter
    @JsonIgnore
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "modified_by_id")
    private User modifiedBy;
}
