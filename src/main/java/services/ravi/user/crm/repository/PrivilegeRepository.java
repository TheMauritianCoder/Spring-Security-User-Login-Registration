package services.ravi.user.crm.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import services.ravi.user.crm.model.Privilege;

public interface PrivilegeRepository extends JpaRepository<Privilege, Long> {

    Privilege findByName(String name);

    @Override
    void delete(Privilege privilege);

}