package spring.boot.week6day12capstone.Controller;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.*;
import spring.boot.week6day12capstone.Api.ApiResponse;
import spring.boot.week6day12capstone.Model.Product;
import spring.boot.week6day12capstone.Service.ProductService;
import spring.boot.week6day12capstone.Service.UserService;

import java.util.ArrayList;

@RestController
@RequestMapping("/api/v1/product")
@RequiredArgsConstructor
public class ProductController {

    private final ProductService productService;
    private final UserService userService;


    @GetMapping("/get")
    public ResponseEntity getAllProducts() {
        return ResponseEntity.status(200).body(productService.getProducts());
    }

    @PostMapping("/add")
    public ResponseEntity addProduct(@Valid @RequestBody Product product, Errors error) {
        if (error.hasErrors()) {
            String message = error.getFieldError().getDefaultMessage();
            return ResponseEntity.status(400).body(new ApiResponse(message));
        }
        productService.addProducts(product);
        return ResponseEntity.status(200).body(new ApiResponse("Product added successfully"));
    }

    //update
    @PutMapping("/update/{id}")
    public ResponseEntity updateProduct(@Valid @RequestBody Product product, @PathVariable int id, Errors error) {
        if (error.hasErrors()) {
            String message = error.getFieldError().getDefaultMessage();
            return ResponseEntity.status(400).body(new ApiResponse(message));
        }
        if (productService.updateProduct(product, id)) {
            return ResponseEntity.status(200).body(new ApiResponse("Product updated successfully"));
        } else return ResponseEntity.status(404).body(new ApiResponse("Product not found"));

    }

    //delete
    @DeleteMapping("/delete/{id}")
    public ResponseEntity getProductById(@PathVariable int id) {
        if (productService.deleteProduct(id)) {
            return ResponseEntity.status(200).body(new ApiResponse("Product deleted successfully"));
        } else return ResponseEntity.status(404).body(new ApiResponse("Product not found"));
    }

    @GetMapping("/getByName/{name}")
    public ResponseEntity getProductByName(@PathVariable String name) {
        if (productService.getProductByName(name) != null) {
            return ResponseEntity.status(200).body(productService.getProductByName(name));
        }
        return ResponseEntity.status(404).body(new ApiResponse("Product not found"));
    }

    @GetMapping("/getProductByPrice")
    public ResponseEntity getProductByPrice() {
        ArrayList<Product> products = productService.getProductSortedByPrice();
        if (!products.isEmpty()) {
            return ResponseEntity.status(200).body(products);
        }
        return ResponseEntity.status(404).body(new ApiResponse("No products found in the store"));
    }
}
