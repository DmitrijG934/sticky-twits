package nn.dgordeev.stickytwits.controller;

import nn.dgordeev.stickytwits.domain.Role;
import nn.dgordeev.stickytwits.domain.User;
import nn.dgordeev.stickytwits.repository.UserRepository;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.time.LocalDateTime;
import java.util.Map;
import java.util.Set;
import java.util.UUID;
import java.util.stream.Collectors;
import java.util.stream.Stream;

@Controller
@RequestMapping("/user")
@PreAuthorize("hasAuthority('ADMIN')")
public class UserController {
    private final UserRepository userRepository;

    public UserController(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @GetMapping
    public String getListUsers(Model model) {
        model.addAttribute("users", userRepository.findAll());
        return "userList";
    }

    @GetMapping("edit")
    public String editUser(@RequestParam("id") User user, Model model) {
        model.addAttribute("user", user);
        model.addAttribute("roles", Role.values());
        return "editUser";
    }

    @GetMapping("remove")
    public String deleteUser(@RequestParam("id") String userId) {
        userRepository.deleteById(UUID.fromString(userId));
        return "redirect:/user";
    }

    @PostMapping
    public String updateUser(
            @RequestParam String username,
            @RequestParam("id") User user,
            @RequestParam Map<String, String> formData
    ) {
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
        return "redirect:/user";
    }
}
