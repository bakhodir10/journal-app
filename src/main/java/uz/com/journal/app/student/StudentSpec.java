package uz.com.journal.app.student;


import org.springframework.data.jpa.domain.Specification;
import uz.com.journal.app.group.Group_;
import uz.com.journal.app.marks.Mark;
import uz.com.journal.app.marks.Mark_;
import uz.com.journal.app.subject.Subject_;
import uz.com.journal.core.NonDeletableSpec;
import uz.com.platform.app.users.User;
import uz.com.platform.app.utils.SpecUtils;

import javax.persistence.criteria.JoinType;

public abstract class StudentSpec {

    public static Specification<Student> findByCriteria(StudentFetch fetch, StudentCriteria criteria, User currentUser) {
        return SpecUtils.and(
                NonDeletableSpec.byDeleted(false),
                criteria == null ? null : byUuid(criteria.getUuid()),
                criteria == null ? null : byGroupUuid(criteria.getGroupUuid()),
                criteria == null ? null : byWeeks(criteria.getSubjectUuid()),
                fetch == null ? null : fetchMarks()
        );
    }

    private static Specification<Student> byUuid(String uuid) {
        return uuid == null ? null :
                (root, query, cb) -> cb.equal(root.get(Student_.uuid), uuid);
    }

    private static Specification<Student> byGroupUuid(String groupUuid) {
        return groupUuid == null ? null :
                (root, query, cb) -> cb.equal(root.get(Student_.group).get(Group_.uuid), groupUuid);
    }

    private static Specification<Student> byWeeks(String subjectUuid) {
        return subjectUuid == null ? null :
                (root, query, cb) -> cb.equal(root.get(Student_.group).get(Group_.subject).get(Subject_.uuid), subjectUuid);
    }

    private static Specification<Student> fetchMarks() {
        return (root, query, cb) -> {
            Class<?> resultType = query.getResultType();
            if (resultType != Long.class && resultType != long.class) {
                root.fetch(Student_.marks, JoinType.LEFT).fetch(Mark_.subject, JoinType.LEFT);
                root.fetch(Student_.marks, JoinType.LEFT).fetch(Mark_.student, JoinType.LEFT);
            }
            return null;
        };
    }
}
