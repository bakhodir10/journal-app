package uz.com.journal.app.group;


import org.springframework.data.jpa.domain.Specification;
import uz.com.journal.app.subject.Subject_;
import uz.com.journal.core.NonDeletableSpec;
import uz.com.platform.app.users.User;
import uz.com.platform.app.utils.SpecUtils;

import javax.persistence.criteria.JoinType;

public abstract class GroupSpec {

    public static Specification<Group> findByCriteria(GroupFetch fetch, GroupCriteria criteria, User currentUser) {
        return SpecUtils.and(
                NonDeletableSpec.byDeleted(false),
                criteria == null ? null : byUuid(criteria.getUuid()),
                criteria == null ? null : bySubjectUuid(criteria.getSubjectUuid()),


                fetchAttendance()
        );
    }

    private static Specification<Group> byUuid(String locationUuid) {
        return locationUuid == null ? null :
                (root, query, cb) -> cb.equal(root.get(Subject_.uuid), locationUuid);
    }

    private static Specification<Group> bySubjectUuid(String subjectUuid) {
        return subjectUuid == null ? null :
                (root, query, cb) -> cb.equal(root.get(Group_.subject).get(Subject_.uuid), subjectUuid);
    }
    private static Specification<Group> fetchAttendance() {
        return (root, query, cb) -> {
            Class<?> resultType = query.getResultType();
            if (resultType != Long.class && resultType != long.class) {
                root.fetch(Group_.attendances, JoinType.LEFT);
            }
            return null;
        };
    }
}
