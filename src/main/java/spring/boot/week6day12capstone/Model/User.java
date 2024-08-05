package spring.boot.week6day12capstone.Model;

import jakarta.validation.constraints.*;
import lombok.AllArgsConstructor;
import lombok.Data;

import java.util.ArrayList;

@Data
@AllArgsConstructor
public class User {
    @NotNull(message = "Id must be not empty")
    private int id;
    @NotEmpty(message = "User name must be not empty")
    private String username;

    @NotEmpty(message = "password must be not empty")
    @Size(min = 6,message = "password must have to be more than 6 length long")
    @Pattern(regexp = "^(?=.*[A-Za-z])(?=.*\\d)[A-Za-z\\d]{6,}$", message = "Password must contain at least 6 characters, including both characters and digits")
    private String password;
    @NotEmpty(message = "Email must be not empty")
    @Email
    private String email;

    @NotEmpty(message = "Role must be not empty")
    @Pattern(regexp = "^(Admin|Customer)$", message = "Role must be either 'Admin' or 'Customer'")
    private String role;

    @NotNull(message = "Balance must not be empty")
    @Positive(message = "Balance must be positive")
    private double balance;

    private ArrayList<Integer> wishlist = new ArrayList<>();
}
