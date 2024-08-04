package spring.boot.week6day12capstone.Model;

import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class Category {
    @NotNull(message = "Id must be not empty")
    private int id;
    @NotEmpty(message = "Name must be not empty")
    @Size(min = 3,message = "Name must have to be more than 3 length long")
    private String name;
}
