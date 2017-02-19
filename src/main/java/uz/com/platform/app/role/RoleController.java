package uz.com.platform.app.role;

import lombok.Setter;
import netscape.security.ForbiddenTargetException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import uz.com.platform.app.users.User;
import uz.com.platform.config.CurrentUser;

import java.security.InvalidParameterException;

@RestController
@RequestMapping("/roles")
public class RoleController {
    @Setter(onMethod = @__({@Autowired}))
    private RoleService roleService;

    @GetMapping
    public ResponseEntity<Page<Role>> page(RoleFetch fetch, RoleCriteria criteria, Pageable pageable, @CurrentUser User currentUser) {
        Page<Role> page = roleService.findPage(fetch, criteria, pageable, currentUser);
        return new ResponseEntity<>(page, HttpStatus.OK);
    }

    @ExceptionHandler({ForbiddenTargetException.class})
    public ResponseEntity<String> handleForbidden(Exception e) {
        return new ResponseEntity<>(e.getMessage(), HttpStatus.FORBIDDEN);
    }

    @ExceptionHandler({IllegalArgumentException.class, InvalidParameterException.class})
    public ResponseEntity<String> handleNotFound(Exception e) {
        return new ResponseEntity<>(e.getMessage(), HttpStatus.BAD_REQUEST);
    }
}
