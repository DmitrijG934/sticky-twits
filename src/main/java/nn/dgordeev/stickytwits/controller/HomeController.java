package nn.dgordeev.stickytwits.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.Map;

@Controller
public class HomeController {
    @GetMapping
    public String greeting(Map<String, Object> model) {
        model.put("username", "guest");
        return "home";
    }
}
