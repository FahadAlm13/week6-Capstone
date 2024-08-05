package spring.boot.week6day12capstone.Model;

import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class Product {

    @NotNull(message = "Product id must not be empty")
    private int id;

    @NotEmpty(message = "Product name must not be empty")
    @Size(min = 3, message = "Product name  have to be more than 3 length long")
    private String name;

    @NotNull(message = "Price must be not empty")
    @Positive
    private double price;
    @NotNull(message = "category id must be not empty ")
    private int categoryID;


}
