package spring.boot.week6day12capstone.Service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import spring.boot.week6day12capstone.Model.MerchantStock;
import spring.boot.week6day12capstone.Model.Product;
import spring.boot.week6day12capstone.Model.User;

@Service
@RequiredArgsConstructor
public class DiscountService {
    //extra endpoint
    private final UserService userService;
    private final ProductService productService;
    private final MerchantStockService merchantStockService;

    public boolean applyDiscount(int userId, int productId,int merchantId, String code) {
        System.out.println("Applying discount with code: " + code);

        String discountCode = "SAVE10";
        if (!code.equals(discountCode)) {
            System.out.println("Invalid discount code.");
            return false;
        }

        User user = userService.getUserById(userId);
        Product product = productService.getProductById(productId);

        if (user == null) {
            System.out.println("User not found.");
            return false;
        } else {
            System.out.println("User found: " + user);
        }

        if (product == null) {
            System.out.println("Product not found.");
            return false;
        } else {
            System.out.println("Product found: " + product);
        }

        double discountRate = 0.10;
        double discountedPrice = product.getPrice() * (1 - discountRate);
        System.out.println("Discounted price: " + discountedPrice);

        if (user.getBalance() < discountedPrice) {
            System.out.println("Insufficient balance.");
            return false;
        }
        MerchantStock merchantStock = merchantStockService.getMerchantStock(productId, merchantId);

        user.setBalance(user.getBalance() - discountedPrice);
        merchantStock.setStock(merchantStock.getStock() - 1);
        System.out.println("Discount applied successfully.");
        return true;
    }

}
