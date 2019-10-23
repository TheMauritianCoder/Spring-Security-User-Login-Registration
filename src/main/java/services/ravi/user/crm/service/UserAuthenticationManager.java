package services.ravi.user.crm.service;

import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

public interface UserAuthenticationManager {
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException;
}
