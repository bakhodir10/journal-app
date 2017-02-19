package uz.com.platform.app.tokens;

import lombok.Setter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import uz.com.platform.app.core.BaseRepository;
import uz.com.platform.app.core.BaseServiceImpl;
import uz.com.platform.app.users.User;
import uz.com.platform.app.users.UserRepository;
import uz.com.platform.app.users.UserSpec;

@Service
public class TokenServiceImpl extends BaseServiceImpl<Token> implements TokenService {
    @Setter(onMethod = @__({@Autowired}))
    private TokenRepository tokenRepository;
    @Setter(onMethod = @__({@Autowired}))
    private UserRepository userRepository;

    @Override
    protected BaseRepository<Token> getRepository() {
        return tokenRepository;
    }

    @Override
    @Transactional
    public TokenItem generate(User user) {
        Token token = new Token();
        token.setUser(user);
        token = save(token, userRepository.findOne(UserSpec.findByUsername("system")));
        return tokenRepository.findTokenItemByUuid(token.getUuid());
    }
}
