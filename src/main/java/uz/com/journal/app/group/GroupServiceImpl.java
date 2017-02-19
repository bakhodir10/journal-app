package uz.com.journal.app.group;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import uz.com.journal.app.subject.Subject;
import uz.com.journal.app.subject.SubjectRepository;
import uz.com.journal.core.NonDeletableRepository;
import uz.com.journal.core.NonDeletableServiceImpl;
import uz.com.platform.app.users.User;

@Service
public class GroupServiceImpl extends NonDeletableServiceImpl<Group> implements GroupService {

    private GroupRepository groupRepository;
    private SubjectRepository subjectRepository;

    @Autowired
    public void setGroupRepository(GroupRepository groupRepository) {
        this.groupRepository = groupRepository;
    }

    @Autowired
    public void setSubjectRepository(SubjectRepository subjectRepository) {
        this.subjectRepository = subjectRepository;
    }

    @Override
    protected NonDeletableRepository<Group> getRepository() {
        return groupRepository;
    }

    @Transactional(readOnly = true)
    @Override
    public Page<Group> findPage(GroupFetch fetch, GroupCriteria criteria, Pageable pageable, User currentUser) {
        return groupRepository.findAll(GroupSpec.findByCriteria(fetch, criteria, currentUser), pageable);
    }

    @Override
    @Transactional(readOnly = true)
    public Group findOne(String uuid, GroupFetch fetch, User currentUser) {
        return groupRepository.findOne(GroupSpec.findByCriteria(fetch, GroupCriteria.builder().uuid(uuid).build(), currentUser));
    }

    @Override
    @Transactional
    public Group update(String uuid, GroupItem subjectItem, User currentUser) {
        Group group = groupRepository.findOne(GroupSpec.findByCriteria(null, GroupCriteria.builder().uuid(uuid).build(), currentUser));
        transfer(subjectItem, group);
        return super.save(group, currentUser);
    }

    @Override
    @Transactional
    public Group create(GroupItem subjectItem, User currentUser) {
        Group group = new Group();
        transfer(subjectItem, group);
        return super.save(group, currentUser);
    }

    private void transfer(GroupItem item, Group group) {
        if (item != null) {
            if (item.getSubject() != null) {
                Subject subject = subjectRepository.findOne(item.getSubject());
                if (subject != null) group.setSubject(subject);
            }
            if (item.getName() != null) group.setName(item.getName());
        }
    }
}
