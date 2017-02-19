package uz.com.journal.app.group;


import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import uz.com.journal.core.NonDeletableService;
import uz.com.platform.app.users.User;

public interface GroupService extends NonDeletableService<Group> {
    Page<Group> findPage(GroupFetch fetch, GroupCriteria criteria, Pageable pageable, User currentUser);

    Group findOne(String uuid, GroupFetch fetch, User currentUser);

    Group create(GroupItem item, User currentUser);

    Group update(String uuid, GroupItem item, User currentUser);
}
