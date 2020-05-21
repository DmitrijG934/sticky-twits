package nn.dgordeev.stickytwits.controller;

import nn.dgordeev.stickytwits.domain.Role;
import nn.dgordeev.stickytwits.domain.User;
import nn.dgordeev.stickytwits.service.UserService;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.util.Map;

@Controller
@RequestMapping("/user")
public class UserController {
    private final UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping
    @PreAuthorize("hasAuthority('ADMIN') ")
    public String getListUsers(Model model) {
        model.addAttribute("users", userService.findAll());
        return "userList";
    }

    @GetMapping("edit")
    @PreAuthorize("hasAuthority('ADMIN')")
    public String editUser(@RequestParam("id") User user, Model model) {
        model.addAttribute("user", user);
        model.addAttribute("roles", Role.values());
        return "editUser";
    }

    @GetMapping("remove")
    @PreAuthorize("hasAuthority('ADMIN')")
    public String deleteUser(@RequestParam("id") String userId) {
        userService.deleteById(userId);
        return "redirect:/user";
    }

    @PostMapping
    @PreAuthorize("hasAuthority('ADMIN')")
    public String updateUserByAdmin(
            @RequestParam String username,
            @RequestParam("id") User user,
            @RequestParam Map<String, String> formData
    ) {
        userService.updateUserByAdmin(username, user, formData);
        return "redirect:/user";
    }

    @GetMapping("profile")
    public String getProfileData(@AuthenticationPrincipal User user,
                                 Model model) {
        model.addAttribute("email", user.getEmail());
        model.addAttribute("username", user.getUsername());
        return "userForm";
    }

    @PostMapping("profile")
    public String editProfileData(@AuthenticationPrincipal User user,
                                  @RequestParam String password,
                                  @RequestParam String email, RedirectAttributes redirectAttributes) {
        userService.editProfileUser(user, password, email);
        redirectAttributes.addFlashAttribute("message", "User successfully updated!");
        return "redirect:/user/profile";
    }

}
