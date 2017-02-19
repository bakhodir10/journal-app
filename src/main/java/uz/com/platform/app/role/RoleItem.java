package uz.com.platform.app.role;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

/**
 * Created by bakhodir on 8/3/16.
 */
@Getter
@RequiredArgsConstructor
public class RoleItem {
    private Long id;
    private String name;
    private Long permissions[];
}
