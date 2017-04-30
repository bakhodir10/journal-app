package uz.com.journal.app.attendance;

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
@RequestMapping("/api/attendances")
public class AttendanceController {

    @Setter(onMethod = @__({@Autowired}))
    private AttendanceService attendanceService;

    @GetMapping
    public ResponseEntity<Page<Attendance>> page(AttendanceFetch fetch, AttendanceCriteria criteria, Pageable pageable, @CurrentUser User currentUser) {
        Page<Attendance> page = attendanceService.findPage(fetch, criteria, pageable, currentUser);
        return new ResponseEntity<>(page, HttpStatus.OK);
    }

    @GetMapping(value = "/{uuid}")
    public ResponseEntity<Attendance> findOne(AttendanceFetch fetch, @PathVariable String uuid, @CurrentUser User currentUser) {
        Attendance attendance = attendanceService.findOne(uuid, fetch, currentUser);
        return new ResponseEntity<>(attendance, HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity<Object> create(@RequestBody AttendanceItem item, @CurrentUser User currentUser) {
        Attendance attendance = attendanceService.create(item, currentUser);
        HttpHeaders headers = new HttpHeaders();
        headers.setLocation(ControllerLinkBuilder.linkTo(AttendanceController.class).slash(attendance.getUuid()).toUri());
        return new ResponseEntity<>(attendance, headers, HttpStatus.CREATED);
    }

    @PutMapping(value = "/{uuid}")
    public ResponseEntity<Object> update(@PathVariable String uuid, @RequestBody AttendanceItem item, @CurrentUser User currentUser)
            throws IllegalTransactionStateException {
        Attendance attendance = attendanceService.update(uuid, item, currentUser);
        return new ResponseEntity<>(attendance, HttpStatus.OK);
    }

    @DeleteMapping(value = "/{uuid}")
    public ResponseEntity<Void> delete(@PathVariable String uuid, @CurrentUser User currentUser) {
        this.attendanceService.delete(uuid, currentUser);
        return new ResponseEntity<>(HttpStatus.OK);
    }
}
