package uz.com.journal.app.group;

import lombok.Setter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.hateoas.mvc.ControllerLinkBuilder;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.IllegalTransactionStateException;
import org.springframework.web.bind.annotation.*;
import uz.com.platform.app.users.User;
import uz.com.platform.config.CurrentUser;

@RestController
@RequestMapping("/api/groups")
public class GroupController {

    @Setter(onMethod = @__({@Autowired}))
    private GroupService groupService;

    @GetMapping
    public ResponseEntity<Page<Group>> page(GroupFetch fetch, GroupCriteria criteria, Pageable pageable, @CurrentUser User currentUser) {
        Page<Group> page = groupService.findPage(fetch, criteria, pageable, currentUser);
        return new ResponseEntity<>(page, HttpStatus.OK);
    }

    @GetMapping(value = "/{uuid}")
    public ResponseEntity<Group> findOne(GroupFetch fetch, @PathVariable String uuid, @CurrentUser User currentUser) {
        Group group = groupService.findOne(uuid, fetch, currentUser);
        return new ResponseEntity<>(group, HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity<Object> create(@RequestBody GroupItem item, @CurrentUser User currentUser) {
        Group group = groupService.create(item, currentUser);
        HttpHeaders headers = new HttpHeaders();
        headers.setLocation(ControllerLinkBuilder.linkTo(GroupController.class).slash(group.getUuid()).toUri());
        return new ResponseEntity<>(group, headers, HttpStatus.CREATED);
    }

    @PutMapping(value = "/{uuid}")
    public ResponseEntity<Object> update(@PathVariable String uuid, @RequestBody GroupItem item, @CurrentUser User currentUser)
            throws IllegalTransactionStateException {
        Group group = groupService.update(uuid, item, currentUser);
        return new ResponseEntity<>(group, HttpStatus.OK);
    }

    @DeleteMapping(value = "/{uuid}")
    public ResponseEntity<Void> delete(@PathVariable String uuid, @CurrentUser User currentUser) {
        this.groupService.delete(uuid, currentUser);
        return new ResponseEntity<>(HttpStatus.OK);
    }
}
