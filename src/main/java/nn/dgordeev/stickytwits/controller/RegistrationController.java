package nn.dgordeev.stickytwits.controller;

import nn.dgordeev.stickytwits.domain.Role;
import nn.dgordeev.stickytwits.domain.User;
import nn.dgordeev.stickytwits.repository.UserRepository;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.time.LocalDateTime;
import java.util.Collections;
import java.util.Map;

@Controller
@RequestMapping("/registration")
public class RegistrationController {
    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;

    public RegistrationController(UserRepository userRepository,
                                  PasswordEncoder passwordEncoder) {
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
    }

    @GetMapping
    public String getView() {
        return "registration";
    }

    @PostMapping
    public String registerUser(User user, Map<String, Object> model) {
        User userByUsername = userRepository.findUserByUsername(user.getUsername());
        if (userByUsername != null) {
            model.put("message", "User is already exists!");
            return "registration";
        }

        String password = user.getPassword();
        user.setPassword(passwordEncoder.encode(password));
        user.setCreatedAt(LocalDateTime.now());
        user.setActive(true);
        user.setRoles(Collections.singleton(Role.USER));
        userRepository.save(user);
        return "redirect:/login";
    }
}
