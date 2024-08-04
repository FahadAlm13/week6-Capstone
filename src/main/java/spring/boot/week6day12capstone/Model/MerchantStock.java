package spring.boot.week6day12capstone.Model;

import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class MerchantStock {

    @NotNull(message = "Id must be not empty")
    private int id;
    @NotNull(message = "Product id must be not empty")
    private int productId;
    @NotNull(message = "Merchant id must be not empty")
    private int merchantId;
    @NotNull(message = "Stock must be not empty")
    private int stock = 10;
}
