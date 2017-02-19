package uz.com.journal.app.subject;


import org.springframework.data.jpa.domain.Specification;
import uz.com.journal.core.NonDeletableSpec;
import uz.com.platform.app.users.User;
import uz.com.platform.app.utils.SpecUtils;

public abstract class SubjectSpec {

    public static Specification<Subject> findByCriteria(SubjectFetch fetch, SubjectCriteria criteria, User currentUser) {
        return SpecUtils.and(
                NonDeletableSpec.byDeleted(false),
                criteria == null ? null : byUuid(criteria.getUuid())
        );
    }

    private static Specification<Subject> byUuid(String locationUuid) {
        return locationUuid == null ? null :
                (root, query, cb) -> cb.equal(root.get(Subject_.uuid), locationUuid);
    }
}
