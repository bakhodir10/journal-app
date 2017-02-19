package uz.com.journal.app.student;

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
@RequestMapping("/api/students")
public class StudentController {

    @Setter(onMethod = @__({@Autowired}))
    private StudentServiceImpl studentService;

    @GetMapping
    public ResponseEntity<Page<Student>> page(StudentFetch fetch, StudentCriteria criteria, Pageable pageable, @CurrentUser User currentUser) {
        Page<Student> page = studentService.findPage(fetch, criteria, pageable, currentUser);
        return new ResponseEntity<>(page, HttpStatus.OK);
    }

    @GetMapping(value = "/{uuid}")
    public ResponseEntity<Student> findOne(StudentFetch fetch, @PathVariable String uuid, @CurrentUser User currentUser) {
        Student item = studentService.findOne(uuid, fetch, currentUser);
        return new ResponseEntity<>(item, HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity<Object> create(@RequestBody StudentItem item, @CurrentUser User currentUser) {
        Student student = studentService.create(item, currentUser);
        HttpHeaders headers = new HttpHeaders();
        headers.setLocation(ControllerLinkBuilder.linkTo(StudentController.class).slash(student.getUuid()).toUri());
        return new ResponseEntity<>(student, headers, HttpStatus.CREATED);
    }

    @PutMapping(value = "/{uuid}")
    public ResponseEntity<Object> update(@PathVariable String uuid, @RequestBody StudentItem item, @CurrentUser User currentUser)
            throws IllegalTransactionStateException {
        Student student = studentService.update(uuid, item, currentUser);
        return new ResponseEntity<>(student, HttpStatus.OK);
    }

    @DeleteMapping(value = "/{uuid}")
    public ResponseEntity<Void> delete(@PathVariable String uuid, @CurrentUser User currentUser) {
        this.studentService.delete(uuid, currentUser);
        return new ResponseEntity<>(HttpStatus.OK);
    }
}
