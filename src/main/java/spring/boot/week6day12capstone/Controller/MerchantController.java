package spring.boot.week6day12capstone.Controller;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.*;
import spring.boot.week6day12capstone.Api.ApiResponse;
import spring.boot.week6day12capstone.Model.Merchant;
import spring.boot.week6day12capstone.Service.MerchantService;

@RestController
@RequestMapping("/api/v1/merchant")
@RequiredArgsConstructor
public class MerchantController {

    private final MerchantService merchantService;

    @GetMapping("/get")
    public ResponseEntity getAllMerchants() {
        return ResponseEntity.status(200).body(merchantService.getMerchants());
    }

    @PostMapping("/add")
    public ResponseEntity addMerchant(@Valid @RequestBody Merchant merchant, Errors error) {
        if (error.hasErrors()) {
            String message = error.getFieldError().getDefaultMessage();
            return ResponseEntity.status(400).body(new ApiResponse(message));
        }
        merchantService.addMerchant(merchant);
        return ResponseEntity.status(200).body(new ApiResponse("Merchant added successfully"));
    }

    @PutMapping("/update/{id}")
    public ResponseEntity updateMerchant(@Valid @RequestBody Merchant merchant, @PathVariable int id, Errors error) {
        if (error.hasErrors()) {
            String message = error.getFieldError().getDefaultMessage();
            return ResponseEntity.status(400).body(new ApiResponse(message));
        }
        if (merchantService.updateMerchant(merchant, id)) {
            return ResponseEntity.status(200).body(new ApiResponse("Merchant updated successfully"));
        } else return ResponseEntity.status(404).body(new ApiResponse("Merchant not found"));
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity deleteMerchant(@PathVariable int id) {
        if (merchantService.deleteMerchant(id)) {
            return ResponseEntity.status(200).body(new ApiResponse("Merchant deleted successfully"));
        } else return ResponseEntity.status(404).body(new ApiResponse("Merchant not found"));
    }
}
