package nn.dgordeev.stickytwits.controller;

import nn.dgordeev.stickytwits.domain.LobFile;
import nn.dgordeev.stickytwits.repository.LobFileRepository;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/file")
public class FileController {
    private final LobFileRepository lobFileRepository;

    public FileController(LobFileRepository lobFileRepository) {
        this.lobFileRepository = lobFileRepository;
    }

    @GetMapping(value = "{filename}", produces = MediaType.MULTIPART_FORM_DATA_VALUE)
    public byte[] getFile(@PathVariable String filename) {
        LobFile lobFileFromDb = lobFileRepository.findLobFileByFileName(filename);
        return lobFileFromDb.getData();
    }
}
