package uz.com.journal.app.subject;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import uz.com.journal.core.NonDeletableRepository;
import uz.com.journal.core.NonDeletableServiceImpl;
import uz.com.platform.app.users.User;

@Service
public class SubjectServiceImpl extends NonDeletableServiceImpl<Subject> implements SubjectService {

    private SubjectRepository subjectRepository;

    @Autowired
    public void setSubjectRepository(SubjectRepository subjectRepository) {
        this.subjectRepository = subjectRepository;
    }

    @Override
    protected NonDeletableRepository<Subject> getRepository() {
        return subjectRepository;
    }

    @Transactional(readOnly = true)
    @Override
    public Page<Subject> findPage(SubjectFetch fetch, SubjectCriteria criteria, Pageable pageable, User currentUser) {
        return subjectRepository.findAll(SubjectSpec.findByCriteria(fetch, criteria, currentUser), pageable);
    }

    @Override
    @Transactional(readOnly = true)
    public Subject findOne(String uuid, SubjectFetch fetch, User currentUser) {
        return subjectRepository.findOne(SubjectSpec.findByCriteria(fetch, SubjectCriteria.builder().uuid(uuid).build(), currentUser));
    }

    @Override
    @Transactional
    public Subject update(String uuid, SubjectItem subjectItem, User currentUser) {
        Subject subject = subjectRepository.findOne(SubjectSpec.findByCriteria(null, SubjectCriteria.builder().uuid(uuid).build(), currentUser));
        transfer(subjectItem, subject);
        return super.save(subject, currentUser);
    }

    @Override
    @Transactional
    public Subject create(SubjectItem subjectItem, User currentUser) {
        Subject subject = new Subject();
        transfer(subjectItem, subject);
        return super.save(subject, currentUser);
    }

    private void transfer(SubjectItem item, Subject subject) {
        if (item.getName() != null) subject.setName(item.getName());
    }
}
