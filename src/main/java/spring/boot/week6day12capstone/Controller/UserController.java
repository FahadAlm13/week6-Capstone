package spring.boot.week6day12capstone.Controller;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.*;
import spring.boot.week6day12capstone.Api.ApiResponse;
import spring.boot.week6day12capstone.Model.GiftDetiles;
import spring.boot.week6day12capstone.Model.User;
import spring.boot.week6day12capstone.Service.UserService;

import java.util.ArrayList;

@RestController
@RequestMapping("/api/v1/user")
@RequiredArgsConstructor
public class UserController {
    private final UserService userService;

    @GetMapping("/get")
    public ResponseEntity getAllUsers() {
        return ResponseEntity.status(200).body(userService.getUsers());
    }

    @PostMapping("/add")
    public ResponseEntity addUser(@Valid @RequestBody User user, Errors error) {
        if (error.hasErrors()) {
            String message = error.getFieldError().getDefaultMessage();
            return ResponseEntity.status(400).body(new ApiResponse(message));
        }
        userService.addUser(user);
        return ResponseEntity.status(200).body(new ApiResponse("User added successfully"));
    }

    @PutMapping("/update/{id}")
    public ResponseEntity updateUser(@Valid @RequestBody User user, @PathVariable int id, Errors error) {
        if (error.hasErrors()) {
            String message = error.getFieldError().getDefaultMessage();
            return ResponseEntity.status(400).body(new ApiResponse(message));
        }
        if (userService.updateUser(user, id)) {
            return ResponseEntity.status(200).body(new ApiResponse("User updated successfully"));
        } else return ResponseEntity.status(404).body(new ApiResponse("User not found"));
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity deleteUser(@PathVariable int id) {
        if (userService.deleteUser(id)) {
            return ResponseEntity.status(200).body(new ApiResponse("User deleted successfully"));
        } else return ResponseEntity.status(404).body(new ApiResponse("User not found"));
    }
    @PostMapping("/buy")
    public ResponseEntity buyProduct(@RequestParam int userId, @RequestParam int productId, @RequestParam int merchantId) {
        String result = userService.buyProduct(userId, productId, merchantId);
        if (result.equals("Product purchased successfully")) {
            return ResponseEntity.status(200).body(new ApiResponse(result));
        } else {
            return ResponseEntity.status(400).body(new ApiResponse(result));
        }
    }
    //extra
    @PostMapping("/subscribePrime")
    public ResponseEntity subscribePrime(@RequestParam int userId) {
        boolean result = userService.subscribePrime(userId);
        if (result) {
            return ResponseEntity.status(200).body(new ApiResponse("Subscribed to Prime membership successfully"));
        } else {
            return ResponseEntity.status(400).body(new ApiResponse("Failed to subscribe to Prime membership"));
        }
    }

    @GetMapping("/giftByUserDetalis")
    public ResponseEntity getGiftByUserDetalis(@RequestParam int userId) {
        ArrayList<GiftDetiles> giftByUserDetalis = userService.getGiftByUserDetalis(userId);
        return ResponseEntity.status(200).body(giftByUserDetalis);
    }

    @PostMapping("/giftProduct")
    public ResponseEntity giftProduct(@RequestParam int giverId, @RequestParam int receiverId, @RequestParam int productId, @RequestParam int merchantId) {
        String result = userService.giftProduct(giverId, receiverId, productId, merchantId);
        if (result.equals("Product gifted successfully")) {
            return ResponseEntity.status(200).body(new ApiResponse(result));
        } else {
            return ResponseEntity.status(400).body(new ApiResponse(result));
        }
    }
}
