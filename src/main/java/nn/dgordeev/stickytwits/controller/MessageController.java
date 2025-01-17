package nn.dgordeev.stickytwits.controller;

import nn.dgordeev.stickytwits.domain.LobFile;
import nn.dgordeev.stickytwits.domain.Message;
import nn.dgordeev.stickytwits.domain.User;
import nn.dgordeev.stickytwits.repository.LobFileRepository;
import nn.dgordeev.stickytwits.repository.MessageRepository;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Map;
import java.util.UUID;

@Controller
public class MessageController {
    private final MessageRepository messageRepository;
    private final LobFileRepository lobFileRepository;

    public MessageController(MessageRepository messageRepository,
                             LobFileRepository lobFileRepository) {
        this.messageRepository = messageRepository;
        this.lobFileRepository = lobFileRepository;
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
            @RequestParam("file") MultipartFile file,
            Map<String, Object> model) throws IOException {
        Message message = new Message(text, tag, user);
        message.setCreatedAt(LocalDateTime.now());

        if (file != null && !file.getOriginalFilename().isEmpty()) {
            String normalizedFilename = StringUtils.cleanPath(file.getOriginalFilename());
            String fileIdentifier = UUID.randomUUID().toString();
            String resultFilename = fileIdentifier + "-" + normalizedFilename;

            LobFile lobFile = new LobFile(resultFilename, file.getContentType(), file.getBytes());
            lobFile.setCreatedAt(LocalDateTime.now());
            lobFileRepository.save(lobFile);

            message.setFile(lobFile);
        }
        messageRepository.save(message);

        List<Message> messages = messageRepository.findAll();
        model.put("messages", messages);
        return "main";
    }
}
