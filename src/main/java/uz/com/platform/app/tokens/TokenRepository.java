package uz.com.platform.app.tokens;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import uz.com.platform.app.core.BaseRepository;

public interface TokenRepository extends BaseRepository<Token> {
    @Query("select new uz.com.platform.app.tokens.TokenItem(t.uuid, t.user.id, t.user.username) from Token t where t.uuid=:uuid")
    TokenItem findTokenItemByUuid(@Param("uuid") String uuid);
}
