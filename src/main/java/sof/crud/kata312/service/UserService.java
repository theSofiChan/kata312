package sof.crud.kata312.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import sof.crud.kata312.repositry.RoleRepository;
import sof.crud.kata312.repositry.UserRepository;
import sof.crud.kata312.model.Role;
import sof.crud.kata312.model.User;
import javax.transaction.Transactional;
import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class UserService implements UserDetailsService {

    private final UserRepository userRepository;

    @Autowired
    public UserService(UserRepository userRepository, RoleRepository roleRepository) {
        this.userRepository = userRepository;
        this.roleRepository = roleRepository;
    }

    @Autowired
    private final RoleRepository roleRepository;

    public User save(User user) {

        return userRepository.save(user);
    }


    public User show(Long id) {
        return userRepository.findById(id).orElse(null);

    }

    public void update(User updatedUser) {
        userRepository.save(updatedUser);
    }

    public void delete(Long id) {
        userRepository.deleteById(id);
    }


    public List<User> index() {
        return userRepository.findAll();
    }

    public User findByUserName(String name) {
        return userRepository.findByName(name);
    }

    @Override
    @Transactional
    public UserDetails loadUserByUsername(String name) throws UsernameNotFoundException {
        User user = findByUserName(name);
        if (user == null) {
            throw new UsernameNotFoundException(String.format("user %s not found", name));
        }
        return new org.springframework.security.core.userdetails.User(user.getName(), user.getPassword(),
                mapRolesToAuthorities(user.getRoles()));
    }

    private Collection<? extends GrantedAuthority> mapRolesToAuthorities(Collection<Role> roles) {
        return roles.stream().map(role -> new SimpleGrantedAuthority(role.getName())).collect(Collectors.toList());
    }

    public User get(Long id) {
        return userRepository.findById(id).get();
    }

    public List<Role> listRoles() {
        return roleRepository.findAll();
    }
}

