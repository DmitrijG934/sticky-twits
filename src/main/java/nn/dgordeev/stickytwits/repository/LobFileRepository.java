package nn.dgordeev.stickytwits.repository;

import nn.dgordeev.stickytwits.domain.LobFile;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import javax.transaction.Transactional;
import java.util.UUID;

@Repository
public interface LobFileRepository extends JpaRepository<LobFile, UUID> {
    @Transactional
    LobFile findLobFileByFileName(String fileName);
}
