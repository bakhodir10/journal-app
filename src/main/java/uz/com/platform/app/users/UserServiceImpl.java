package uz.com.platform.app.users;

import lombok.Setter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import uz.com.platform.app.role.Role;
import uz.com.platform.app.role.RoleRepository;
import uz.com.journal.core.NonDeletableRepository;
import uz.com.journal.core.NonDeletableServiceImpl;

import java.util.HashSet;
import java.util.Set;

@Service
public class UserServiceImpl extends NonDeletableServiceImpl<User> implements UserService, UserDetailsService {

    @Setter(onMethod = @__({@Autowired}))
    private RoleRepository roleRepository;
    @Setter(onMethod = @__({@Autowired}))
    private UserRepository userRepository;

    @Override
    protected NonDeletableRepository<User> getRepository() {
        return userRepository;
    }


    @Override
    @Transactional(readOnly = true)
    public Page<User> findPage(UserFetch fetch, UserCriteria criteria, Pageable pageable, User currentUser) {
        return userRepository.findAll(UserSpec.findByCriteria(fetch, criteria, currentUser), pageable);
    }

    @Override
    @Transactional(readOnly = true)
    public User findOne(String uuid, UserFetch fetch, User currentUser) {
        return userRepository.findOne(UserSpec.findByCriteria(fetch, UserCriteria.builder().uuid(uuid).build(), currentUser));
    }

    @Override
    @Transactional
    public User create(UserItem item, User currentUser)  {
        User user = new User();
        transfer(item, user);
        user.setPassword("1");
        return super.save(user, currentUser);
    }


    @Override
    @Transactional
    public User update(String uuid, UserItem item, User currentUser) {
        User user = userRepository.findOne(UserSpec.findByCriteria(null, UserCriteria.builder().uuid(uuid).build(), currentUser));
        transfer(item, currentUser);
        return super.save(user, currentUser);
    }

    @Override
    @Transactional
    public void delete(String uuid, User currentUser) {

        userRepository.findOne(UserSpec.findByCriteria(null, UserCriteria.builder().uuid(uuid).build(), currentUser));
    }

    private void transfer(UserItem item, User user) {
        if (item != null) {

            if (item.getRole() != null) {
                Role role = roleRepository.findOne(item.getRole());
                Set<Role> roles = user.getRoles();
                if (role != null) {
                    if (roles != null)
                        roles.clear();
                    else {
                        roles = new HashSet<>();
                    }
                    roles.add(role);
                    user.setRoles(roles);
                }
            }

            if (item.getUsername() != null) user.setUsername(item.getUsername());
            if (item.getFullName() != null) user.setFullName(item.getFullName());
        }

    }


    @Override
    @Transactional(readOnly = true)
    public User findByUsername(String username) {
        User user = userRepository.findOne(UserSpec.findByUsername(username));
        if (user != null)
            user.rebuildPermissions();
        return user;
    }

    @Override
    @Transactional(readOnly = true)
    public User findByTokenUuid(String tokenUuid) {
        User user = userRepository.findOne(UserSpec.findByTokenUuid(tokenUuid));
        if (user != null) {
            user.rebuildPermissions();
        }
        return user;
    }

    @Override
    @Transactional(readOnly = true)
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        return findByUsername(username);
    }

}
