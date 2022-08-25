package in.bushansirgur.expensetrackerapi.entity.dto;

import lombok.Data;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

@Data
public class UserDto {
    @NotBlank(message = "Name should no be empty")
    private String name;
    @NotBlank(message = "Email should no be empty")
    @Email(message = "Enter a valid email")
    private String email;
    //@NotBlank(message = "Password should no be empty")
    //@Size(min =5, message = "Password should be at least 5 characters")
    //@Size(min =5, message = "Password should be at least 5 characters")
    private String password;
    private Long age = 0L;
}
