package uz.com.platform.app.role;

import uz.com.platform.app.core.BaseRepository;

public interface RoleRepository extends BaseRepository<Role> {
    Role findByName(String name);

    Role findById(Long id);
}
