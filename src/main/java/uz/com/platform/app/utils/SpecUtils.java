package uz.com.platform.app.utils;

import org.springframework.data.jpa.domain.Specification;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;
import java.util.ArrayList;
import java.util.List;

public class SpecUtils {
    @SafeVarargs
    public static <T> Specification<T> and(Specification<T>... specs) {
        return (root, query, cb) -> {
            Predicate[] predicates = toPredicateArray(root, query, cb, specs);
            return predicates.length == 0 ? null : cb.and(predicates);
        };
    }

    @SafeVarargs
    public static <T> Specification<T> or(Specification<T>... specs) {
        return (root, query, cb) -> {
            Predicate[] predicates = toPredicateArray(root, query, cb, specs);
            return predicates.length == 0 ? null : cb.or(predicates);
        };
    }

    @SafeVarargs
    private static <T> Predicate[] toPredicateArray(Root<T> root, CriteriaQuery<?> query, CriteriaBuilder cb, Specification<T>... specs) {
        return toArray(toPredicate(root, query, cb, specs));
    }

    @SafeVarargs
    private static <T> List<Predicate> toPredicate(Root<T> root, CriteriaQuery<?> query, CriteriaBuilder cb, Specification<T>... specs) {
        List<Predicate> predicates = new ArrayList<>();
        for (Specification<T> spec : specs) {
            if (spec != null) {
                Predicate predicate = spec.toPredicate(root, query, cb);
                if (predicate != null) {
                    predicates.add(predicate);
                }
            }
        }
        return predicates;
    }

    private static Predicate[] toArray(List<Predicate> predicates) {
        return predicates.toArray(new Predicate[predicates.size()]);
    }
}
