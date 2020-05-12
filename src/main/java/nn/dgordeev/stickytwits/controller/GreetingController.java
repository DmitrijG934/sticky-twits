package nn.dgordeev.stickytwits.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.Map;

@Controller
public class GreetingController {
    @GetMapping("/greeting")
    public String greeting(@RequestParam(required = false, defaultValue = "guest") final String name,
                           Map<String, Object> model) {
        model.put("name", name);
        return "greeting";
    }
}
