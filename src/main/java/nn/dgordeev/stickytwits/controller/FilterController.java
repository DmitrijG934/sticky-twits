package nn.dgordeev.stickytwits.controller;

import nn.dgordeev.stickytwits.domain.Message;
import nn.dgordeev.stickytwits.repository.MessageRepository;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;
import java.util.Map;

@Controller
public class FilterController {
    private final MessageRepository messageRepository;

    public FilterController(MessageRepository messageRepository) {
        this.messageRepository = messageRepository;
    }

    @PostMapping("/filter")
    public String filterMessages(@RequestParam String filter,
                                 Map<String, Object> model) {
        List<Message> messages;
        if (filter != null && !filter.isEmpty()) {
            messages = messageRepository.findAllByTag(filter);
        } else messages = messageRepository.findAll();
        model.put("messages", messages);
        return "main";
    }
}
