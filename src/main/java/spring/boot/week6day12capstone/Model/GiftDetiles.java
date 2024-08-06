package spring.boot.week6day12capstone.Model;

import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class GiftDetiles {
    @NotNull(message = "user id should be not empty")
    private int userId;
    @NotNull(message = "product id should be not empty")
    private int productId;
    @Positive
    private double price;
    @Size(max = 50,message = "note must be not largest than 50 characters")
    private String note;
}
