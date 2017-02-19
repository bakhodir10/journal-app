package uz.com.journal.core;

import org.springframework.transaction.annotation.Transactional;
import uz.com.platform.app.core.BaseServiceImpl;
import uz.com.platform.app.users.User;

import java.util.Date;

public abstract class NonDeletableServiceImpl<T extends NonDeletable> extends BaseServiceImpl<T> implements NonDeletableService<T> {

    @Override
    protected abstract NonDeletableRepository<T> getRepository();

    @Override
    @Transactional
    public void delete(String uuid, User currentUser) {
        T entity = getRepository().findOne(uuid);
        entity.setDeleted(true);
        entity.setDeletedAt(new Date());
        entity.setDeletedBy(currentUser);
        getRepository().save(entity);
    }
}
