package uz.com.journal.core;

import org.springframework.data.jpa.domain.Specification;

public abstract class NonDeletableSpec {
    public static <T extends NonDeletable> Specification<T> byDeleted(Boolean deleted) {
        return deleted ? null : (root, query, cb) -> cb.equal(root.get(NonDeletable_.deleted), deleted);
    }
}
