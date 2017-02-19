package uz.com.platform.app.core;

import uz.com.platform.app.users.User;

public interface BaseService<T extends BaseEntity> {

    T findOne(String uuid, User currentUser);
    <S extends T> T save(S entity, User currentUser);
    void delete(String uuid, User currentUser);
}
