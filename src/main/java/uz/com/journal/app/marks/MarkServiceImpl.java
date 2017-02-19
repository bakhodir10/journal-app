package uz.com.journal.app.marks;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import uz.com.journal.core.NonDeletableRepository;
import uz.com.journal.core.NonDeletableServiceImpl;
import uz.com.platform.app.users.User;

@Service
public class MarkServiceImpl extends NonDeletableServiceImpl<Mark> implements MarkService {

    private MarkRepository markRepository;

    @Autowired
    public void setMarkRepository(MarkRepository markRepository) {
        this.markRepository = markRepository;
    }

    @Override
    protected NonDeletableRepository<Mark> getRepository() {
        return markRepository;
    }

    @Transactional(readOnly = true)
    @Override
    public Page<Mark> findPage(MarkFetch fetch, MarkCriteria criteria, Pageable pageable, User currentUser) {
        return markRepository.findAll(MarkSpec.findByCriteria(fetch, criteria, currentUser), pageable);
    }

    @Override
    @Transactional(readOnly = true)
    public Mark findOne(String uuid, MarkFetch fetch, User currentUser) {
        return markRepository.findOne(MarkSpec.findByCriteria(fetch, MarkCriteria.builder().uuid(uuid).build(), currentUser));
    }

    @Override
    @Transactional
    public Mark update(String uuid, MarkItem item, User currentUser) {
        Mark mark = markRepository.findOne(MarkSpec.findByCriteria(null, MarkCriteria.builder().uuid(uuid).build(), currentUser));
        transfer(item, mark);
        return super.save(mark, currentUser);
    }

    @Override
    @Transactional
    public Mark create(MarkItem item, User currentUser) {
        Mark mark = new Mark();
        return super.save(mark, currentUser);
    }

    private void transfer(MarkItem item, Mark mark) {
        if (item != null) {
        }
    }
}
