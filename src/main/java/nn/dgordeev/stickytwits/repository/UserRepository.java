package nn.dgordeev.stickytwits.repository;

import nn.dgordeev.stickytwits.domain.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.UUID;

@Repository
public interface UserRepository extends JpaRepository<User, UUID> {
    User findUserByUsername(String username);
    User findUserByActivationCode(String code);
}
