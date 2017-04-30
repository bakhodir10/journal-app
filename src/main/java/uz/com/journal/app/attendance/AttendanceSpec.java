package uz.com.journal.app.attendance;


import org.springframework.data.jpa.domain.Specification;
import uz.com.journal.core.NonDeletableSpec;
import uz.com.platform.app.users.User;
import uz.com.platform.app.users.User_;
import uz.com.platform.app.utils.SpecUtils;

public abstract class AttendanceSpec {

    public static Specification<Attendance> findByCriteria(AttendanceFetch fetch, AttendanceCriteria criteria, User currentUser) {
        return SpecUtils.and(
                NonDeletableSpec.byDeleted(false),

                criteria.getUuid() != null ? byUuid(criteria.getUuid()) : null
        );
    }

    private static Specification<Attendance> byUuid(String uuid) {
        return uuid == null || uuid.isEmpty() ? null :
                (root, query, cb) -> cb.equal(root.get(Attendance_.uuid), uuid);
    }
}
