package uz.com.platform.app;

import lombok.Setter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import uz.com.platform.app.users.User;
import uz.com.platform.app.users.UserRepository;

import javax.annotation.PostConstruct;

@Component
public class StartupTasks {
    private static final String ADMIN_USER_USERNAME = "admin";
    private static final String ADMIN_USER_PASSWORD = "qwerty";
    private static final String SYSTEM_USER_USERNAME = "system";


    @Setter(onMethod = @__({@Autowired}))
    private UserRepository userRepository;

    @PostConstruct
    public void init() {
        initUsers();
    }

    private void initUsers() {
        if (userRepository.count() == 0) {
            User admin = new User();
            admin.setUsername(ADMIN_USER_USERNAME);
            admin.setFullName(ADMIN_USER_USERNAME);
            admin.setPassword(ADMIN_USER_PASSWORD);
            admin.setEnabled(true);
            admin.setCreatedBy(admin);
            userRepository.save(admin);
        }
    }
}

