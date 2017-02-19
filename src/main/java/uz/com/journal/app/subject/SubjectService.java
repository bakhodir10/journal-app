package uz.com.journal.app.subject;


import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import uz.com.journal.core.NonDeletableService;
import uz.com.platform.app.users.User;

public interface SubjectService extends NonDeletableService<Subject> {
    Page<Subject> findPage(SubjectFetch fetch, SubjectCriteria criteria, Pageable pageable, User currentUser);

    Subject findOne(String uuid, SubjectFetch fetch, User currentUser);

    Subject create(SubjectItem subjectItem, User currentUser);

    Subject update(String uuid, SubjectItem subjectItem, User currentUser);
}
