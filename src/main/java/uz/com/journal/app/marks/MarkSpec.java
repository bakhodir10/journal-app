package uz.com.journal.app.marks;


import org.springframework.data.jpa.domain.Specification;
import uz.com.journal.core.NonDeletableSpec;
import uz.com.platform.app.users.User;
import uz.com.platform.app.utils.SpecUtils;

public abstract class MarkSpec {

    public static Specification<Mark> findByCriteria(MarkFetch fetch, MarkCriteria criteria, User currentUser) {
        return SpecUtils.and(
                NonDeletableSpec.byDeleted(false)
        );
    }
}
