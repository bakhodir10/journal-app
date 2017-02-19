package uz.com.journal.app.marks;

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
@RequestMapping("/api/marks")
public class MarkController {

    @Setter(onMethod = @__({@Autowired}))
    private MarkService markService;

    @GetMapping
    public ResponseEntity<Page<Mark>> page(MarkFetch fetch, MarkCriteria criteria, Pageable pageable, @CurrentUser User currentUser) {
        Page<Mark> page = markService.findPage(fetch, criteria, pageable, currentUser);
        return new ResponseEntity<>(page, HttpStatus.OK);
    }

    @GetMapping(value = "/{uuid}")
    public ResponseEntity<Mark> findOne(MarkFetch fetch, @PathVariable String uuid, @CurrentUser User currentUser) {
        Mark Mark = markService.findOne(uuid, fetch, currentUser);
        return new ResponseEntity<>(Mark, HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity<Object> create(@RequestBody MarkItem item, @CurrentUser User currentUser) {
        Mark Mark = markService.create(item, currentUser);
        HttpHeaders headers = new HttpHeaders();
        headers.setLocation(ControllerLinkBuilder.linkTo(MarkController.class).slash(Mark.getUuid()).toUri());
        return new ResponseEntity<>(Mark, headers, HttpStatus.CREATED);
    }

    @PutMapping(value = "/{uuid}")
    public ResponseEntity<Object> update(@PathVariable String uuid, @RequestBody MarkItem item, @CurrentUser User currentUser)
            throws IllegalTransactionStateException {
        Mark Mark = markService.update(uuid, item, currentUser);
        return new ResponseEntity<>(Mark, HttpStatus.OK);
    }

    @DeleteMapping(value = "/{uuid}")
    public ResponseEntity<Void> delete(@PathVariable String uuid, @CurrentUser User currentUser) {
        this.markService.delete(uuid, currentUser);
        return new ResponseEntity<>(HttpStatus.OK);
    }
}
