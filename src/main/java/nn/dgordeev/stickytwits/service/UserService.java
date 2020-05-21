package nn.dgordeev.stickytwits.service;

import nn.dgordeev.stickytwits.domain.Role;
import nn.dgordeev.stickytwits.domain.User;
import nn.dgordeev.stickytwits.repository.UserRepository;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import java.time.LocalDateTime;
import java.util.Collections;
import java.util.UUID;

@Service
public class UserService implements UserDetailsService {
    private final UserRepository userRepository;
    private final MailSenderService mailSenderService;

    public UserService(UserRepository userRepository,
                       MailSenderService mailSenderService) {
        this.userRepository = userRepository;
        this.mailSenderService = mailSenderService;
    }

    @Override
    public UserDetails loadUserByUsername(String s) throws UsernameNotFoundException {
        return userRepository.findUserByUsername(s);
    }

    public boolean addUser(User user) {
        User userByUsername = userRepository.findUserByUsername(user.getUsername());

        if (userByUsername != null) {
            return false;
        }

        String password = user.getPassword();
        user.setPassword(password);
        user.setCreatedAt(LocalDateTime.now());
        user.setActive(true);
        user.setRoles(Collections.singleton(Role.USER));
        user.setActivationCode(UUID.randomUUID().toString());

        userRepository.save(user);

        if (!StringUtils.isEmpty(user.getEmail())) {
            String message = String.format(
                    "Hello, %s! Welcome to Sticky Twits forum! Activation link: localhost:8080/registration/activation/%s"
                    , user.getUsername(), user.getActivationCode()
            );
            mailSenderService.send(user.getEmail(), "Account activation", message);
        }

        return true;
    }

    public boolean activateUser(String code) {
        User user = userRepository.findUserByActivationCode(code);
        if (user == null) {
            return false;
        }

        user.setActivationCode(null);
        userRepository.save(user);
        return true;
    }
}
