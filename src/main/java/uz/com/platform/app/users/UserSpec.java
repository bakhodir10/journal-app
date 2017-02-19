package uz.com.platform.app.users;

import org.springframework.data.jpa.domain.Specification;
import uz.com.journal.core.NonDeletableSpec;
import uz.com.platform.app.role.Role_;
import uz.com.platform.app.tokens.Token;
import uz.com.platform.app.tokens.Token_;
import uz.com.platform.app.utils.SpecUtils;

import javax.persistence.criteria.JoinType;
import javax.persistence.criteria.Root;

public abstract class UserSpec {
    public static Specification<User> findByCriteria(UserFetch fetch, UserCriteria criteria, User currentUser) {
        return SpecUtils.and(

                NonDeletableSpec.byDeleted(false),

                criteria == null ? null : byKeyword(criteria.getKeyword()),
                criteria == null ? null : byUuid(criteria.getUuid()),
                criteria == null ? null : byUsername(criteria.getUsername()),

                fetch == null ? null : fetch.getRoleFetch() == null ? null : fetchRoles(),
                fetch == null ? null : fetch.getPermissionFetch() == null ? null : fetchPermissions()
        );
    }

    public static Specification<User> findByUsername(String username) {
        return username == null ? null : SpecUtils.and(
                NonDeletableSpec.byDeleted(false),
                byUsername(username),
                fetchRoles(),
                fetchPermissions()
        );
    }

    public static Specification<User> findByTokenUuid(String tokenUuid) {
        return tokenUuid == null ? null : SpecUtils.and(
                NonDeletableSpec.byDeleted(false),
                byTokenUuid(tokenUuid),
                fetchRoles(),
                fetchPermissions()
        );
    }

    private static Specification<User> byUsername(String username) {
        return username == null || username.isEmpty() ? null :
                (root, query, cb) -> cb.equal(root.get(User_.username), username);
    }


    private static Specification<User> byKeyword(String keyword) {
        return keyword == null || keyword.isEmpty() ? null :
                (root, query, cb) -> cb.like(root.get(User_.fullName), "%" + keyword + "%");
    }


    private static Specification<User> byTokenUuid(String tokenUuid) {
        if (tokenUuid == null || tokenUuid.isEmpty()) {
            return null;
        }
        return (root, query, cb) -> {
            Root<Token> token = query.from(Token.class);
            return cb.and(
                    cb.equal(token.get(Token_.uuid), tokenUuid),
                    cb.equal(token.get(Token_.user), root)
            );
        };
    }

    private static Specification<User> byUuid(String uuid) {
        if (uuid == null || uuid.isEmpty()) {
            return null;
        }
        return (root, query, cb) -> cb.equal(root.get(User_.uuid), uuid);
    }


    private static Specification<User> fetchPermissions() {
        return (root, query, cb) -> {
            Class<?> resultType = query.getResultType();
            if (resultType != Long.class && resultType != long.class) {
                root.fetch(User_.roles, JoinType.LEFT).fetch(Role_.permissions, JoinType.LEFT);
            }
            return null;
        };
    }

    private static Specification<User> fetchRoles() {
        return (root, query, cb) -> {
            Class<?> resultType = query.getResultType();
            if (resultType != Long.class && resultType != long.class) {
                root.fetch(User_.roles, JoinType.LEFT);
            }
            return null;
        };
    }
}
