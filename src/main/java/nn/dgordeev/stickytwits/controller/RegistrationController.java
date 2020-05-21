package nn.dgordeev.stickytwits.controller;

import nn.dgordeev.stickytwits.domain.User;
import nn.dgordeev.stickytwits.service.UserService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

@Controller
@RequestMapping("/registration")
public class RegistrationController {
    private final UserService userService;

    public RegistrationController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping
    public String getView() {
        return "registration";
    }

    @PostMapping
    public String registerUser(User user, Model model, RedirectAttributes redirectAttributes) {
        if (!userService.addUser(user)) {
            model.addAttribute("message", "User is already exists!");
            return "registration";
        }
        redirectAttributes
                .addFlashAttribute("message",
                        String.format("Activation code sent to your email: %s", user.getEmail()));
        return "redirect:/login";
    }

    @GetMapping("activation/{code}")
    public String activateUser(@PathVariable String code, Model model) {
        boolean isActivated = userService.activateUser(code);
        if (isActivated) {
            model.addAttribute("messageSuccess", "User successfully activated!");
        } else model.addAttribute("messageFail", "Activation code not found!");
        return "login";
    }
}
