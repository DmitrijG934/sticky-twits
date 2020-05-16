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
    public String getMessages(Map<String, Object> model) {
        List<Message> messages = messageRepository.findAll();
        if(messages.isEmpty()) {
            model.put("notification", "There are no messages yet.");
        }
        model.put("messages", messages);
        return "main";
    }

    @PostMapping("/main")
    public String createMessage(
            @AuthenticationPrincipal User user,
            @RequestParam String text,
            @RequestParam String tag,
            Map<String, Object> model)
    {
        Message message = new Message(text, tag, user);
        message.setCreatedAt(LocalDateTime.now());
        messageRepository.save(message);

        List<Message> messages = messageRepository.findAll();
        model.put("messages", messages);
        return "main";
    }
}
