package nn.dgordeev.stickytwits.service;

import nn.dgordeev.stickytwits.domain.Role;
import nn.dgordeev.stickytwits.domain.User;
import nn.dgordeev.stickytwits.repository.UserRepository;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import java.time.LocalDateTime;
import java.util.Collections;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.UUID;
import java.util.stream.Collectors;
import java.util.stream.Stream;

@Service
public class UserService implements UserDetailsService {
    private final UserRepository userRepository;
    private final MailSenderService mailSenderService;
    private final PasswordEncoder passwordEncoder;

    public UserService(UserRepository userRepository,
                       MailSenderService mailSenderService,
                       PasswordEncoder passwordEncoder) {
        this.userRepository = userRepository;
        this.mailSenderService = mailSenderService;
        this.passwordEncoder = passwordEncoder;
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

        user.setPassword(passwordEncoder.encode(user.getPassword()));
        user.setCreatedAt(LocalDateTime.now());
        user.setActive(true);
        user.setRoles(Collections.singleton(Role.USER));
        user.setActivationCode(UUID.randomUUID().toString());

        userRepository.save(user);

        sendMail(user);

        return true;
    }

    public List<User> findAll() {
        return userRepository.findAll();
    }

    public void deleteById(String id) {
        userRepository.deleteById(UUID.fromString(id));
    }

    public void updateUserByAdmin(String username, User user, Map<String, String> formData) {
        user.setUsername(username);
        Set<String> roles = Stream.of(Role.values())
                .map(Role::name)
                .collect(Collectors.toSet());

        user.getRoles().clear();
        user.setUpdatedAt(LocalDateTime.now());

        for (String key : formData.keySet()) {
            if (roles.contains(key)) {
                user.getRoles()
                        .add(Role.valueOf(key));
            }
        }
        userRepository.save(user);
    }

    public void editProfileUser(User user, String password, String email) {
        String userEmail = user.getEmail();
        boolean isEmailChanged = (email != null && !email.equals(userEmail)) ||
                (userEmail != null && !userEmail.equals(email));
        if (isEmailChanged) {
            user.setEmail(email);
            if (!StringUtils.isEmpty(email)) {
                user.setActivationCode(UUID.randomUUID().toString());
            }
        }
        if (!StringUtils.isEmpty(password)) {
            user.setPassword(passwordEncoder.encode(password));
        }

        user.setUpdatedAt(LocalDateTime.now());
        userRepository.save(user);

        if (isEmailChanged) sendMail(user);
    }


    private void sendMail(User user) {
        if (!StringUtils.isEmpty(user.getEmail())) {
            String message = String.format(
                    "Hello, %s! Welcome to Sticky Twits forum! <a href=\"localhost:8080/registration/activation/%s\" target=\"_blank\">Activation link</a>"
                    , user.getUsername(), user.getActivationCode()
            );
            mailSenderService.send(user.getEmail(), "Account activation", message);
        }
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
