package spring.boot.week6day12capstone.Service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import spring.boot.week6day12capstone.Model.Review;
import spring.boot.week6day12capstone.Model.User;
import spring.boot.week6day12capstone.Model.Product;

import java.util.ArrayList;

@Service
@RequiredArgsConstructor
public class ReviewService {

    private final UserService userService;
    private final ProductService productService;

    private ArrayList<Review> reviews = new ArrayList<>();

    public boolean addReview(Review review) {
        User user = userService.getUserById(review.getUserId());
        Product product = productService.getProductById(review.getProductId());
        if (user == null) {
            System.out.println("User not found.");
            return false;
        }

        if (product == null) {
            System.out.println("Product not found.");
            return false;
        }
        reviews.add(review);
        System.out.println("Review added: " + review);
        return true;
    }

    public boolean updateReview(int userId, int productId, String newReviewText, int newRating) {
        for (Review review : reviews) {
            if (review.getUserId() == userId && review.getProductId() == productId) {
                review.setReviewText(newReviewText);
                review.setRating(newRating);
                System.out.println("Review updated: " + review);
                return true;
            }
        }
        return false;
    }
    public ArrayList<Review> getAllReviews() {
        return reviews;
    }
}
