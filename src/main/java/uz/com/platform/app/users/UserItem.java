package uz.com.platform.app.users;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;

import java.util.Date;

@RequiredArgsConstructor
@Getter
@Setter
public class UserItem {
    private Long id;
    private String uuid;
    private String email;
    private boolean enabled;
    private String fullName;
    private Language language;
    private String role;
    private String password;
    private String phoneNumber;
    private String description;
    private Long pageSize;
    private String position;
    private Date startDate;
    private Date endDate;
    private String username;
    private String certificateFile;
    private String department;
}
