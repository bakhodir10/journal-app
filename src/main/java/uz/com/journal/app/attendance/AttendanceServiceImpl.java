package uz.com.journal.app.attendance;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import uz.com.journal.core.NonDeletableRepository;
import uz.com.journal.core.NonDeletableServiceImpl;
import uz.com.platform.app.users.User;

@Service
public class AttendanceServiceImpl extends NonDeletableServiceImpl<Attendance> implements AttendanceService {

    private AttendanceRepository attendanceRepository;

    @Autowired
    public void setAttendanceRepository(AttendanceRepository attendanceRepository) {
        this.attendanceRepository = attendanceRepository;
    }

    @Override
    protected NonDeletableRepository<Attendance> getRepository() {
        return attendanceRepository;
    }

    @Transactional(readOnly = true)
    @Override
    public Page<Attendance> findPage(AttendanceFetch fetch, AttendanceCriteria criteria, Pageable pageable, User currentUser) {
        return attendanceRepository.findAll(AttendanceSpec.findByCriteria(fetch, criteria, currentUser), pageable);
    }

    @Override
    @Transactional(readOnly = true)
    public Attendance findOne(String uuid, AttendanceFetch fetch, User currentUser) {
        return attendanceRepository.findOne(AttendanceSpec.findByCriteria(fetch, AttendanceCriteria.builder().uuid(uuid).build(), currentUser));
    }

    @Override
    @Transactional
    public Attendance update(String uuid, AttendanceItem item, User currentUser) {
        Attendance attendance = attendanceRepository.findOne(AttendanceSpec.findByCriteria(null, AttendanceCriteria.builder().uuid(uuid).build(), currentUser));
        transfer(item, attendance);
        return super.save(attendance, currentUser);
    }

    @Override
    @Transactional
    public Attendance create(AttendanceItem item, User currentUser) {
        Attendance attendance = new Attendance();
        return super.save(attendance, currentUser);
    }

    private void transfer(AttendanceItem item, Attendance attendance) {
        if (item != null) {
            attendance.setDate(item.getDate());
        }
    }
}
