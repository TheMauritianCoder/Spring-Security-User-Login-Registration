package services.ravi.user.crm.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import services.ravi.user.crm.model.Role;

public interface RoleRepository extends JpaRepository<Role, Long> {

    Role findByName(String name);

    @Override
    void delete(Role role);

}