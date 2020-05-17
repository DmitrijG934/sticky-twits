package nn.dgordeev.stickytwits.controller;

import nn.dgordeev.stickytwits.domain.User;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.Map;

@Controller
public class HomeController {
    @GetMapping
    public String greeting(@AuthenticationPrincipal User user,
                           Map<String, Object> model) {
        String username = user != null ? user.getUsername() : "guest";
        model.put("username", username);
        return "home";
    }
}
