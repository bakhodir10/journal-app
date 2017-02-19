package uz.com.platform.app.role;

import lombok.Setter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import uz.com.platform.app.core.BaseRepository;
import uz.com.platform.app.core.BaseServiceImpl;
import uz.com.platform.app.users.User;
import uz.com.platform.config.CurrentUser;

@Service
public class RoleServiceImpl extends BaseServiceImpl<Role> implements RoleService {
    @Setter(onMethod = @__({@Autowired}))
    private RoleRepository roleRepository;

    @Override
    protected BaseRepository<Role> getRepository() {
        return roleRepository;
    }

    @Override
    public Page<Role> findPage(RoleFetch fetch, RoleCriteria criteria, Pageable pageable, User currentUser) {
        return roleRepository.findAll(pageable);
    }

    @Override
    @Transactional
    public Role save(RoleItem roleItem, @CurrentUser User currentUser) {
        if (roleItem != null) {
            Role role = new Role();
            if (roleItem.getId() != null) {
                role = roleRepository.findOne(roleItem.getId());
            }
//            Set<Permission> permissions = new HashSet<>();
//            for (Long permission : roleItem.getPermissions()) {
//                permissions.add(Permission.UPDATE_USER);
//            } //
//            role.setPermissions(permissions); // TODO: 8/4/16 add permission
            role.setName(roleItem.getName());
            return save(role, currentUser);
        }
        return null;
    }
}
