package uz.com.platform.app.tokens;

import lombok.Getter;
import lombok.Setter;
import uz.com.platform.app.core.BaseEntity;
import uz.com.platform.app.users.User;

import javax.persistence.*;

@Getter
@Setter
@Entity
@Table(name = "tokens")
public class Token extends BaseEntity {

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_uuid")
    private User user;
}
