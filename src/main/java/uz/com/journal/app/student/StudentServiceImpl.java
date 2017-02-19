package uz.com.journal.app.student;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import uz.com.journal.app.group.Group;
import uz.com.journal.app.group.GroupRepository;
import uz.com.journal.app.marks.Mark;
import uz.com.journal.app.marks.MarkRepository;
import uz.com.journal.app.subject.Subject;
import uz.com.journal.app.subject.SubjectRepository;
import uz.com.journal.core.NonDeletableRepository;
import uz.com.journal.core.NonDeletableServiceImpl;
import uz.com.platform.app.users.User;

import java.util.HashSet;
import java.util.Set;

@Service
public class StudentServiceImpl extends NonDeletableServiceImpl<Student> implements StudentService {

    private StudentRepository studentRepository;
    private MarkRepository markRepository;
    private SubjectRepository subjectRepository;
    private GroupRepository groupRepository;

    @Autowired
    public void setSubjectRepository(StudentRepository studentRepository) {
        this.studentRepository = studentRepository;
    }

    @Autowired
    public void setMarkRepository(MarkRepository markRepository) {
        this.markRepository = markRepository;
    }

    @Autowired
    public void setSubjectRepository(SubjectRepository subjectRepository){this.subjectRepository = subjectRepository;}

    @Autowired
    public void setGroupRepository(GroupRepository groupRepository){this.groupRepository = groupRepository;}
    @Override
    protected NonDeletableRepository<Student> getRepository() {
        return studentRepository;
    }

    @Transactional(readOnly = true)
    @Override
    public Page<Student> findPage(StudentFetch fetch, StudentCriteria criteria, Pageable pageable, User currentUser) {
        return studentRepository.findAll(StudentSpec.findByCriteria(fetch, criteria, currentUser), pageable);
    }

    @Override
    @Transactional(readOnly = true)
    public Student findOne(String uuid, StudentFetch fetch, User currentUser) {
        return studentRepository.findOne(StudentSpec.findByCriteria(fetch, StudentCriteria.builder().uuid(uuid).build(), currentUser));
    }

    @Override
    @Transactional
    public Student update(String uuid, StudentItem item, User currentUser) {
        Student student = studentRepository.findOne(StudentSpec.findByCriteria(null, StudentCriteria.builder().uuid(uuid).build(), currentUser));
        transfer(item, student);
        return super.save(student, currentUser);
    }

    @Override
    @Transactional
    public Student create(StudentItem item, User currentUser) {
        Student student = new Student();
        transfer(item, student);
        Subject subject = subjectRepository.findOne(item.getSubject());
        super.save(student, currentUser);
        Set<Mark> marks = new HashSet<>();
        for (int i = 0; i < 24; i++) {
            Mark mark = new Mark();
            mark.setStudent(student);
            mark.setSubject(subject);
            marks.add(mark);
            markRepository.save(mark);
        }
        student.setMarks(marks);
        return super.save(student, currentUser);
    }

    private void transfer(StudentItem item, Student student) {
        if (item != null) {
            if (item.getName() != null) student.setName(item.getName());
            if (item.getGroup() != null) {
                Group group = groupRepository.findOne(item.getGroup());
                if (group != null) student.setGroup(group);
            }
        }
    }
}
