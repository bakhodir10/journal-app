package uz.com.platform.app.tokens;

import uz.com.platform.app.core.BaseService;
import uz.com.platform.app.users.User;

public interface TokenService extends BaseService<Token> {
    TokenItem generate(User user);
}
