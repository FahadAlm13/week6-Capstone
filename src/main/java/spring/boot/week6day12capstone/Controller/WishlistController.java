package spring.boot.week6day12capstone.Controller;

import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import spring.boot.week6day12capstone.Api.ApiResponse;
import spring.boot.week6day12capstone.Service.WishlistService;

import java.util.List;

@RestController
@RequestMapping("/api/v1/wishlist")
@RequiredArgsConstructor
public class WishlistController {

    private final WishlistService wishlistService;

    @PostMapping("/add")
    public ResponseEntity addToWishlist(@RequestParam int userId, @RequestParam int productId) {
        boolean isAdded = wishlistService.addToWishlist(userId, productId);
        if (isAdded) {
            return ResponseEntity.status(200).body(new ApiResponse("Product added to wishlist successfully"));
        } else {
            return ResponseEntity.status(400).body(new ApiResponse("Failed to add product to wishlist"));
        }
    }

    @DeleteMapping("/remove")
    public ResponseEntity<ApiResponse> removeFromWishlist(@RequestParam int userId, @RequestParam int productId) {
        boolean isRemoved = wishlistService.removeFromWishlist(userId, productId);
        if (isRemoved) {
            return ResponseEntity.status(200).body(new ApiResponse("Product removed from wishlist successfully"));
        } else {
            return ResponseEntity.status(400).body(new ApiResponse("Failed to remove product from wishlist"));
        }
    }

    @GetMapping("/get")
    public ResponseEntity<?> getWishlist(@RequestParam int userId) {
        List<Integer> wishlist = wishlistService.getWishlist(userId);
        if (wishlist != null) {
            return ResponseEntity.status(200).body(wishlist);
        } else {
            return ResponseEntity.status(400).body(new ApiResponse("Failed to retrieve wishlist"));
        }
    }
}
