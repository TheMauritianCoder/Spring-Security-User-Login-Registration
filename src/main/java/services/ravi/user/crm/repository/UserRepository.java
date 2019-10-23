package services.ravi.user.crm.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import services.ravi.user.crm.model.User;

public interface UserRepository extends JpaRepository<User, Long> {

    User findByEmail(String email);
    @Override
    void delete(User user);
}