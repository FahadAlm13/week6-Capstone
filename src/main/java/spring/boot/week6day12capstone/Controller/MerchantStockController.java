package spring.boot.week6day12capstone.Controller;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.*;
import spring.boot.week6day12capstone.Api.ApiResponse;
import spring.boot.week6day12capstone.Model.MerchantStock;
import spring.boot.week6day12capstone.Service.MerchantStockService;

@RestController
@RequestMapping("/api/v1/merchantstock")
@RequiredArgsConstructor
public class MerchantStockController {

    private final MerchantStockService merchantStockService;

    @GetMapping("/get")
    public ResponseEntity getAllMerchantStocks() {
        return ResponseEntity.status(200).body(merchantStockService.getMerchantStocks());
    }

    @PostMapping("/add")
    public ResponseEntity addMerchantStock(@Valid @RequestBody MerchantStock merchantStock, Errors error) {
        if (error.hasErrors()) {
            String message = error.getFieldError().getDefaultMessage();
            return ResponseEntity.status(400).body(new ApiResponse(message));
        }
        merchantStockService.addMerchantStock(merchantStock);
        return ResponseEntity.status(200).body(new ApiResponse("MerchantStock added successfully"));
    }

    @PutMapping("/update/{id}")
    public ResponseEntity updateMerchantStock(@Valid @RequestBody MerchantStock merchantStock, @PathVariable int id, Errors error) {
        if (error.hasErrors()) {
            String message = error.getFieldError().getDefaultMessage();
            return ResponseEntity.status(400).body(new ApiResponse(message));
        }
        if (merchantStockService.updateMerchantStock(merchantStock, id)) {
            return ResponseEntity.status(200).body(new ApiResponse("MerchantStock updated successfully"));
        }
        return ResponseEntity.status(404).body(new ApiResponse("MerchantStock not found"));
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity deleteMerchantStock(@PathVariable int id) {
        if (merchantStockService.deleteMerchantStock(id)) {
            return ResponseEntity.status(200).body(new ApiResponse("MerchantStock deleted successfully"));
        }
        return ResponseEntity.status(404).body(new ApiResponse("MerchantStock not found"));
    }

    @PutMapping("/addStock")
    public ResponseEntity addStock(@RequestParam int productId, @RequestParam int merchantId, @RequestParam int amount) {
        if (merchantStockService.addMoreStock(productId, merchantId, amount)) {
            return ResponseEntity.status(200).body(new ApiResponse("MerchantStock added successfully"));
        }
        return ResponseEntity.status(404).body(new ApiResponse("MerchantStock not found"));
    }
}
