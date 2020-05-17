package nn.dgordeev.stickytwits.controller;

import nn.dgordeev.stickytwits.domain.Message;
import nn.dgordeev.stickytwits.domain.User;
import nn.dgordeev.stickytwits.repository.MessageRepository;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Map;

@Controller
public class MessageController {
    private final MessageRepository messageRepository;

    public MessageController(MessageRepository messageRepository) {
        this.messageRepository = messageRepository;
    }

    @GetMapping("/main")
    public String getMessages(
            @RequestParam(required = false, defaultValue = "") String search,
            Map<String, Object> model) {
        List<Message> messages;
        if (search != null && !search.isEmpty()) {
            messages = messageRepository.findAllByTag(search);
            if (messages.isEmpty()) model.put("reportMessage", "Result is empty.");
        } else {
            messages = messageRepository.findAll();
            if (messages.isEmpty()) model.put("reportMessage", "There are no messages yet!");
        }

        model.put("messages", messages);
        model.put("search", search);

        return "main";
    }

    @PostMapping("/main")
    public String createMessage(
            @AuthenticationPrincipal User user,
            @RequestParam String text,
            @RequestParam String tag,
            Map<String, Object> model) {
        Message message = new Message(text, tag, user);
        message.setCreatedAt(LocalDateTime.now());
        messageRepository.save(message);

        List<Message> messages = messageRepository.findAll();
        model.put("messages", messages);
        return "main";
    }
}
