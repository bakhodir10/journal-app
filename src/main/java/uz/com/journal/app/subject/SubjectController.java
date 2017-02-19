package uz.com.journal.app.subject;

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
@RequestMapping("/api/subjects")
public class SubjectController {

    @Setter(onMethod = @__({@Autowired}))
    private SubjectService subjectService;

    @GetMapping
    public ResponseEntity<Page<Subject>> page(SubjectFetch fetch, SubjectCriteria criteria, Pageable pageable, @CurrentUser User currentUser) {
        Page<Subject> page = subjectService.findPage(fetch, criteria, pageable, currentUser);
        return new ResponseEntity<>(page, HttpStatus.OK);
    }

    @GetMapping(value = "/{uuid}")
    public ResponseEntity<Subject> findOne(SubjectFetch fetch, @PathVariable String uuid, @CurrentUser User currentUser) {
        Subject item = subjectService.findOne(uuid, fetch, currentUser);
        return new ResponseEntity<>(item, HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity<Object> create(@RequestBody SubjectItem item, @CurrentUser User currentUser) {
        Subject subject = subjectService.create(item, currentUser);
        HttpHeaders headers = new HttpHeaders();
        headers.setLocation(ControllerLinkBuilder.linkTo(SubjectController.class).slash(subject.getUuid()).toUri());
        return new ResponseEntity<>(subject, headers, HttpStatus.CREATED);
    }

    @PutMapping(value = "/{uuid}")
    public ResponseEntity<Object> update(@PathVariable String uuid, @RequestBody SubjectItem item, @CurrentUser User currentUser)
            throws IllegalTransactionStateException {
        Subject subject = subjectService.update(uuid, item, currentUser);
        return new ResponseEntity<>(subject, HttpStatus.OK);
    }

    @DeleteMapping(value = "/{uuid}")
    public ResponseEntity<Void> delete(@PathVariable String uuid, @CurrentUser User currentUser) {
        this.subjectService.delete(uuid, currentUser);
        return new ResponseEntity<>(HttpStatus.OK);
    }
}
