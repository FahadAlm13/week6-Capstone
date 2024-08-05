package spring.boot.week6day12capstone.Controller;

import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import spring.boot.week6day12capstone.Api.ApiResponse;
import spring.boot.week6day12capstone.Service.DiscountService;

@RestController
@RequestMapping("/api/v1/discount")
@RequiredArgsConstructor
public class DiscountController {

    private final DiscountService discountService;

    @PostMapping("/apply")
    public ResponseEntity<ApiResponse> applyDiscount(@RequestParam int userId, @RequestParam int productId,@RequestParam int merchantId, @RequestParam String code) {
        boolean isDiscountApplied = discountService.applyDiscount(userId, productId, merchantId,code);
        if (isDiscountApplied) {
            return ResponseEntity.status(200).body(new ApiResponse("Discount applied successfully"));
        } else {
            return ResponseEntity.status(400).body(new ApiResponse("Failed to apply discount"));
        }
    }
}
