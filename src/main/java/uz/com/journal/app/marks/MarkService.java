package uz.com.journal.app.marks;


import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import uz.com.journal.core.NonDeletableService;
import uz.com.platform.app.users.User;

public interface MarkService extends NonDeletableService<Mark> {
    Page<Mark> findPage(MarkFetch fetch, MarkCriteria criteria, Pageable pageable, User currentUser);

    Mark findOne(String uuid, MarkFetch fetch, User currentUser);

    Mark create(MarkItem item, User currentUser);

    Mark update(String uuid, MarkItem item, User currentUser);
}
