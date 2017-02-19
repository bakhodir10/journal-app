package uz.com.platform.app.users;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import uz.com.journal.core.NonDeletableService;

public interface UserService extends NonDeletableService<User> {
    Page<User> findPage(UserFetch fetch, UserCriteria criteria, Pageable pageable, User currentUser);

    User findOne(String uuid, UserFetch fetch, User currentUser);

    User create(UserItem item, User currentUser);

    User update(String uuid, UserItem item, User currentUser);

    User findByUsername(String username);

    User findByTokenUuid(String tokenUuid);
}
