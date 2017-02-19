package uz.com.journal.app.group;


import org.springframework.data.jpa.domain.Specification;
import uz.com.journal.app.subject.Subject_;
import uz.com.journal.core.NonDeletableSpec;
import uz.com.platform.app.users.User;
import uz.com.platform.app.utils.SpecUtils;

public abstract class GroupSpec {

    public static Specification<Group> findByCriteria(GroupFetch fetch, GroupCriteria criteria, User currentUser) {
        return SpecUtils.and(
                NonDeletableSpec.byDeleted(false),
                criteria == null ? null : byUuid(criteria.getUuid()),
                criteria == null ? null : bySubjectUuid(criteria.getSubjectUuid())
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
}
