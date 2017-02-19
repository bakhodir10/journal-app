package uz.com.platform.app.role;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import uz.com.platform.app.core.BaseService;
import uz.com.platform.app.users.User;
import uz.com.platform.config.CurrentUser;

public interface RoleService extends BaseService<Role> {

    Role save(RoleItem roleItem, @CurrentUser User currentUser);

    Page<Role> findPage(RoleFetch fetch, RoleCriteria criteria, Pageable pageable, User currentUser);

}
