package in.bushansirgur.expensetrackerapi.service.impl;

import com.fasterxml.jackson.databind.util.BeanUtil;
import in.bushansirgur.expensetrackerapi.entity.User;
import in.bushansirgur.expensetrackerapi.entity.dto.UserDto;
import in.bushansirgur.expensetrackerapi.exceptions.ItemAreadyExistsExeption;
import in.bushansirgur.expensetrackerapi.exceptions.ResourceNotFoundException;
import in.bushansirgur.expensetrackerapi.repository.UserRepository;
import in.bushansirgur.expensetrackerapi.service.UserService;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.http.HttpStatus;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.ResponseStatus;

import java.util.ArrayList;

@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Override
    public User read() throws ResourceNotFoundException {
        Long id = getLoggedInUser().getId();
        return this.userRepository.findById(id).orElseThrow(()
                -> new ResourceNotFoundException("User not found for the id: " + id));
    }

    @Override
    public User update(UserDto userIn) {
        User user = this.read();
        user.setName(userIn.getName() != null ? userIn.getName() : user.getName());
        user.setEmail(userIn.getEmail() != null ? userIn.getEmail() : user.getEmail());
        user.setPassword(userIn.getPassword() != null ? this.passwordEncoder.encode(userIn.getPassword()) : user.getPassword());
        user.setAge(userIn.getAge() != null ? userIn.getAge() : user.getAge());
        return this.userRepository.save(user);
    }

    @Override
    public void delete() {
        User user = this.read();
        this.userRepository.delete(user);
    }

    @Override
    public User getLoggedInUser() {
        Authentication  authentication = SecurityContextHolder.getContext().getAuthentication();
        String email = authentication.getName();
        User user = this.userRepository.findByEmail(email);
        if(user == null)
            new UsernameNotFoundException("User not found for email: " + email);

        return user;
    }


    @Override
    public User createUser(UserDto userIn) {
        if (this.userRepository.existsByEmail(userIn.getEmail())) {
            throw new ItemAreadyExistsExeption("User is already registered whit email: " + userIn.getEmail());
        }
        User user = new User();
        BeanUtils.copyProperties(userIn, user);
        user.setPassword(this.passwordEncoder.encode(userIn.getPassword()));
        return this.userRepository.save(user);
    }




}
