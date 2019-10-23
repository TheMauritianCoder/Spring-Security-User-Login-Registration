package services.ravi.user.crm.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import services.ravi.user.crm.constant.UserRoles;
import services.ravi.user.crm.dto.UserDto;
import services.ravi.user.crm.exception.EmailExistsException;
import services.ravi.user.crm.model.User;
import services.ravi.user.crm.repository.RoleRepository;
import services.ravi.user.crm.repository.UserRepository;
import services.ravi.user.crm.service.UserService;

import javax.transaction.Transactional;
import java.util.Arrays;

@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private RoleRepository roleRepository;

    @Autowired
    private BCryptPasswordEncoder bCryptPasswordEncoder;

    @Transactional
    @Override
    public User registerNewUser(final UserDto userDto) {
        if (emailExists(userDto.getEmail())) {
            throw new EmailExistsException("There is an account with that email adress: " + userDto.getEmail());
        }
        final User user = new User();

        user.setFirstName(userDto.getFirstName());
        user.setLastName(userDto.getLastName());
        user.setPassword(bCryptPasswordEncoder.encode(userDto.getPassword()));
        user.setEmail(userDto.getEmail());
        user.setRoles(Arrays.asList(roleRepository.findByName(UserRoles.USER)));

        // TODO: Replace static configuration of enabled by user email verification.
        user.setEnabled(true);
        return userRepository.save(user);
    }

    private boolean emailExists(final String email) {
        return userRepository.findByEmail(email) != null;
    }

}
