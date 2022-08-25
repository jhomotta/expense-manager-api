package in.bushansirgur.expensetrackerapi.service;

import in.bushansirgur.expensetrackerapi.entity.User;
import in.bushansirgur.expensetrackerapi.entity.dto.UserDto;
import org.springframework.security.core.userdetails.UserDetails;


public interface UserService {
    User createUser(UserDto user);
    User read();
    User update(UserDto userIn);
    void delete();
    User getLoggedInUser();

}
