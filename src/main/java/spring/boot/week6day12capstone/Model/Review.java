package spring.boot.week6day12capstone.Model;

import jakarta.validation.constraints.*;
import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class Review {

    @NotNull(message = "User ID shouldn't be null")
    private int userId;

    @NotNull(message = "Product ID shouldn't be null")
    private int productId;

    @NotEmpty(message = "Review text shouldn't be empty")
    @Size(min = 10, message = "Review text should be at least 10 characters long")
    private String reviewText;

    @NotNull(message = "Rating shouldn't be null")
    @Min(value = 1, message = "Rating should be at least 1")
    @Max(value = 5, message = "Rating should be at most 5")
    private int rating;
}
