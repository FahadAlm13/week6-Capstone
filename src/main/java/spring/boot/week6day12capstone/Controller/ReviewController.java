package spring.boot.week6day12capstone.Controller;

import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.*;
import spring.boot.week6day12capstone.Api.ApiResponse;
import spring.boot.week6day12capstone.Model.Review;
import spring.boot.week6day12capstone.Service.ReviewService;

import jakarta.validation.Valid;

@RestController
@RequestMapping("/api/v1/review")
@RequiredArgsConstructor
public class ReviewController {

    private final ReviewService reviewService;

    @PostMapping("/add")
    public ResponseEntity<ApiResponse> addReview(@Valid @RequestBody Review review, Errors error) {
        if (error.hasErrors()) {
            String message = error.getFieldError().getDefaultMessage();
            return ResponseEntity.status(400).body(new ApiResponse(message));
        }
        boolean isReviewAdded = reviewService.addReview(review);
        if (isReviewAdded) {
            return ResponseEntity.status(200).body(new ApiResponse("Review added successfully"));
        } else {
            return ResponseEntity.status(400).body(new ApiResponse("Failed to add review"));
        }
    }

    @PutMapping("/update")
    public ResponseEntity<ApiResponse> updateReview(@RequestParam int userId, @RequestParam int productId, @RequestParam String newReviewText, @RequestParam int newRating) {
        boolean isReviewUpdated = reviewService.updateReview(userId, productId, newReviewText, newRating);
        if (isReviewUpdated) {
            return ResponseEntity.status(200).body(new ApiResponse("Review updated successfully"));
        } else {
            return ResponseEntity.status(400).body(new ApiResponse("Failed to update review"));
        }
    }
    @GetMapping("/get")
    public ResponseEntity getAllReviews() {
        return ResponseEntity.status(200).body(reviewService.getAllReviews());
    }
}
