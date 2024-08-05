package spring.boot.week6day12capstone.Service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import spring.boot.week6day12capstone.Model.User;
import spring.boot.week6day12capstone.Model.Product;

import java.util.List;

@Service
@RequiredArgsConstructor
public class WishlistService {

    private final UserService userService;
    private final ProductService productService;

    public boolean addToWishlist(int userId, int productId) {
        User user = userService.getUserById(userId);
        Product product = productService.getProductById(productId);

        if (user == null) {
            System.out.println("User not found.");
            return false;
        }

        if (product == null) {
            System.out.println("Product not found.");
            return false;
        }

        if (!user.getWishlist().contains(productId)) {
            user.getWishlist().add(productId);
            System.out.println("Product added to wishlist: " + productId);
            return true;
        }

        System.out.println("Product already in wishlist.");
        return false;
    }

    public boolean removeFromWishlist(int userId, int productId) {
        User user = userService.getUserById(userId);

        if (user == null) {
            System.out.println("User not found.");
            return false;
        }

        if (user.getWishlist().contains(productId)) {
            user.getWishlist().remove(Integer.valueOf(productId));
            System.out.println("Product removed from wishlist: " + productId);
            return true;
        }

        System.out.println("Product not in wishlist.");
        return false;
    }

    public List<Integer> getWishlist(int userId) {
        User user = userService.getUserById(userId);

        if (user == null) {
            System.out.println("User not found.");
            return null;
        }

        return user.getWishlist();
    }
}
