package nn.dgordeev.stickytwits.controller;

import nn.dgordeev.stickytwits.controller.utils.ControllerUtils;
import nn.dgordeev.stickytwits.domain.User;
import nn.dgordeev.stickytwits.service.UserService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.StringUtils;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.validation.Valid;
import java.util.Map;

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
    public String registerUser(
            @RequestParam("passwordConfirm") String passwordConfirm,
            @Valid User user,
            BindingResult bindingResult,
            Model model,
            RedirectAttributes redirectAttributes) {

        if (StringUtils.isEmpty(passwordConfirm)) {
            model.addAttribute("passwordConfirmError", "Password confirmation cannot to be empty");
        }

        if (user.getPassword() != null && !user.getPassword().equals(passwordConfirm)) {
            model.addAttribute("passwordError", "Passwords are not equals!");
            return "registration";
        }
        if (bindingResult.hasErrors()) {
            Map<String, String> validationErrors = ControllerUtils.getValidationErrors(bindingResult);
            model.mergeAttributes(validationErrors);
            return "registration";
        } else {
            if (!userService.addUser(user)) {
                model.addAttribute("usernameError", "User is already exists!");
                return "registration";
            }
            if (!StringUtils.isEmpty(user.getEmail())) {
                redirectAttributes
                        .addFlashAttribute("message",
                                String.format("Activation code sent to your email: %s", user.getEmail()));
            }
        }
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
