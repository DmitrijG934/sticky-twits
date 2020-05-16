package nn.dgordeev.stickytwits.repository;

import nn.dgordeev.stickytwits.domain.Message;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface MessageRepository extends JpaRepository<Message, Long> {
    List<Message> findAllByTag(String tag);
}
