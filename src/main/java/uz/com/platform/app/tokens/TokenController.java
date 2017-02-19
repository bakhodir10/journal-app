package uz.com.platform.app.tokens;

import lombok.Setter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import uz.com.platform.app.users.User;
import uz.com.platform.config.CurrentUser;

import java.util.HashMap;
import java.util.Map;

@RestController
public class TokenController {
    @Setter(onMethod = @__({@Autowired}))
    private TokenService tokenService;

    @RequestMapping(value = "/api/token/check", method = RequestMethod.GET)
    public Map<String, Object> check(@CurrentUser User currentUser) {
        Map<String, Object> userItem = new HashMap<>();
        userItem.put("username", currentUser.getUsername());
        userItem.put("fullName", currentUser.getFullName());
        Map<String, Object> result = new HashMap<>();
        result.put("data", userItem);
        if (currentUser.getRoles() != null && currentUser.getRoles().size() != 0)
            result.put("role", currentUser.getRoles().iterator().next());
        return result;
    }

    @RequestMapping(value = "/api/token/generate", method = RequestMethod.GET)
    public TokenItem generate(@CurrentUser User currentUser) {
        return tokenService.generate(currentUser);
    }
}
