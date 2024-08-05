package spring.boot.week6day12capstone.Controller;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.*;
import spring.boot.week6day12capstone.Api.ApiResponse;
import spring.boot.week6day12capstone.Model.Category;
import spring.boot.week6day12capstone.Service.CategoryService;

@RestController
@RequestMapping("/api/v1/category")
@RequiredArgsConstructor
public class CategoryController {

    private final CategoryService categoryService;

    @GetMapping("/get")
    public ResponseEntity getAllCategories() {
        return ResponseEntity.status(200).body(categoryService.getCategories());
    }

    @PostMapping("/add")
    public ResponseEntity addCategory(@Valid @RequestBody Category category, Errors error) {
        if (error.hasErrors()) {
            String message = error.getFieldError().getDefaultMessage();
            return ResponseEntity.status(400).body(new ApiResponse(message));
        }
        categoryService.addCategory(category);
        return ResponseEntity.status(200).body(new ApiResponse("Category added successfully"));
    }

    @PutMapping("/update/{id}")
    public ResponseEntity updateCategory(@Valid @RequestBody Category category, @PathVariable int id, Errors error) {
        if (error.hasErrors()) {
            String message = error.getFieldError().getDefaultMessage();
            return ResponseEntity.status(400).body(new ApiResponse(message));
        }
        if (categoryService.updateCategory(category, id)) {
            return ResponseEntity.status(200).body(new ApiResponse("Category updated successfully"));
        } else return ResponseEntity.status(404).body(new ApiResponse("Category not found"));
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity deleteCategory(@PathVariable int id) {
        if (categoryService.deleteCategory(id)) {
            return ResponseEntity.status(200).body(new ApiResponse("Category deleted successfully"));
        } else return ResponseEntity.status(404).body(new ApiResponse("Category not found"));
    }
}
