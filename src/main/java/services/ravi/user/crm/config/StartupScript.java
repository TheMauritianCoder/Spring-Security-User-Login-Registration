package services.ravi.user.crm.config;

import org.springframework.beans.factory.InitializingBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationListener;
import org.springframework.context.event.ContextRefreshedEvent;
import org.springframework.stereotype.Component;
import services.ravi.user.crm.constant.UserRoles;
import services.ravi.user.crm.model.Role;
import services.ravi.user.crm.service.RoleService;

@Component
public class StartupScript implements ApplicationListener<ContextRefreshedEvent> {

    @Autowired
    RoleService roleService;

    @Override public void onApplicationEvent(ContextRefreshedEvent event) {
        if(roleService.findAll().isEmpty()){
            Role role = new Role();
            role.setName(UserRoles.USER);
            roleService.addRole(role);
        }
    }
}