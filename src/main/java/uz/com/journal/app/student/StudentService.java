package uz.com.journal.app.student;


import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import uz.com.journal.core.NonDeletableService;
import uz.com.platform.app.users.User;

public interface StudentService extends NonDeletableService<Student> {
    Page<Student> findPage(StudentFetch fetch, StudentCriteria criteria, Pageable pageable, User currentUser);

    Student findOne(String uuid, StudentFetch fetch, User currentUser);

    Student create(StudentItem item, User currentUser);

    Student update(String uuid, StudentItem item, User currentUser);
}
