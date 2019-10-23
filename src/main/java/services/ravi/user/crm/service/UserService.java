package services.ravi.user.crm.service;

import services.ravi.user.crm.dto.UserDto;
import services.ravi.user.crm.exception.EmailExistsException;
import services.ravi.user.crm.model.User;

public interface UserService {

    User registerNewUser(UserDto userDto) throws EmailExistsException;
}
