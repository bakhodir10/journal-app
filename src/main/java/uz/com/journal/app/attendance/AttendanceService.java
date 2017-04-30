package uz.com.journal.app.attendance;


import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import uz.com.journal.core.NonDeletableService;
import uz.com.platform.app.users.User;

public interface AttendanceService extends NonDeletableService<Attendance> {
    Page<Attendance> findPage(AttendanceFetch fetch, AttendanceCriteria criteria, Pageable pageable, User currentUser);

    Attendance findOne(String uuid, AttendanceFetch fetch, User currentUser);

    Attendance create(AttendanceItem item, User currentUser);

    Attendance update(String uuid, AttendanceItem item, User currentUser);
}
