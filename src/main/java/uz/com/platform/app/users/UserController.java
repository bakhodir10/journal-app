package uz.com.platform.app.users;

import lombok.Setter;
import netscape.security.ForbiddenTargetException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.hateoas.mvc.ControllerLinkBuilder;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.IllegalTransactionStateException;
import org.springframework.web.bind.annotation.*;
import uz.com.platform.config.CurrentUser;
import uz.com.journal.app.util.UrlManage;

import java.security.InvalidParameterException;

@RestController
@RequestMapping(UrlManage.USERS)
public class UserController {
    @Setter(onMethod = @__({@Autowired}))
    private UserService userService;

    @GetMapping
    public ResponseEntity<Page<User>> page(UserFetch fetch, UserCriteria criteria, Pageable pageable, @CurrentUser User currentUser) {
        Page<User> page = userService.findPage(fetch, criteria, pageable, currentUser);
        return new ResponseEntity<>(page, HttpStatus.OK);
    }

    @GetMapping(value = UrlManage.UUID)
    public ResponseEntity<User> findOne(UserFetch fetch, @PathVariable String uuid, @CurrentUser User currentUser) {
        User item = userService.findOne(uuid, fetch, currentUser);
        return new ResponseEntity<>(item, HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity<Object> create(@RequestBody UserItem item, @CurrentUser User currentUser) {
        User user = userService.create(item, currentUser);
        HttpHeaders headers = new HttpHeaders();
        headers.setLocation(ControllerLinkBuilder.linkTo(UserController.class).slash(user.getUuid()).toUri());
        return new ResponseEntity<>(user, headers, HttpStatus.CREATED);
    }

    @PutMapping(value = UrlManage.UUID)
    public ResponseEntity<Void> update(@PathVariable String uuid, @RequestBody UserItem item, @CurrentUser User currentUser)
            throws IllegalTransactionStateException {
        userService.update(uuid, item, currentUser);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @DeleteMapping(value = UrlManage.UUID)
    public ResponseEntity<Void> delete(@PathVariable String uuid, @CurrentUser User currentUser) {
        userService.delete(uuid, currentUser);
        return new ResponseEntity<>(HttpStatus.OK);
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
