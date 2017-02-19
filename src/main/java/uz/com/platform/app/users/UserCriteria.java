package uz.com.platform.app.users;

import lombok.*;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Builder
public class UserCriteria {
    private String uuid;
    private String username;
    private String keyword;
    private String department;
    private String position;
    private String certificate;
}
