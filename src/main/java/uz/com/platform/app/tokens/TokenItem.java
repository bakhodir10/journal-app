package uz.com.platform.app.tokens;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Getter
public class TokenItem {
    private final String uuid;
    private final Long userId;
    private final String username;
}
