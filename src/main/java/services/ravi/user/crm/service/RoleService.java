package services.ravi.user.crm.service;

import services.ravi.user.crm.model.Role;

import java.util.List;

public interface RoleService {

    Role addRole(Role role);

    List<Role> findAll();

}
