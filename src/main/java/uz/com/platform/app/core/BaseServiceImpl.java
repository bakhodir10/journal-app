package uz.com.platform.app.core;

import org.springframework.transaction.annotation.Transactional;
import uz.com.platform.app.users.User;
import uz.com.platform.config.CurrentUser;

import java.util.Date;

public abstract class BaseServiceImpl<T extends BaseEntity> implements BaseService<T> {

    protected abstract BaseRepository<T> getRepository();

    @Override
    @Transactional
    public <S extends T> T save(S entity, User currentUser) {
        T tEn = findOne(entity.getUuid(), currentUser);
        if (tEn == null) {
            entity.setCreatedBy(currentUser);
        } else {
            entity.setModifiedAt(new Date());
            entity.setModifiedBy(currentUser);
        }
        return getRepository().save(entity);
    }

    @Override
    @Transactional(readOnly = true)
    public T findOne(String uuid, User currentUser) {
        return getRepository().findOne(uuid);
    }

    @Override
    @Transactional
    public void delete(String uuid, @CurrentUser User currentUser) {
        getRepository().delete(findOne(uuid, currentUser));
    }
}
