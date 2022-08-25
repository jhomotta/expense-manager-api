package in.bushansirgur.expensetrackerapi.controller;

import in.bushansirgur.expensetrackerapi.entity.User;
import in.bushansirgur.expensetrackerapi.entity.dto.UserDto;
import in.bushansirgur.expensetrackerapi.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
@RequestMapping("/profile")
public class UserController {
    @Autowired
    private UserService userService;

    @PutMapping("")
    public ResponseEntity<User> update(@Valid @RequestBody UserDto user) {
        return new ResponseEntity<User>(this.userService.update(user), HttpStatus.OK);
    }


    @GetMapping("")
    public ResponseEntity<User> get() {
        return new ResponseEntity<User>(this.userService.read(), HttpStatus.OK);
    }

    @ResponseStatus(HttpStatus.NO_CONTENT)
    @DeleteMapping("/profile/deactivate")
    public void detele() {
        this.userService.delete();
    }
}
