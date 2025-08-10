package org.example.capstone2.Controller;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.example.capstone2.ApiResponse.ApiResponse;
import org.example.capstone2.Model.Category;
import org.example.capstone2.Service.CategoryService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/category")
@RequiredArgsConstructor
public class CategoryController {

    private final CategoryService categoryService;

    @GetMapping("/get")
    public ResponseEntity<?> getAllCategories(){
        return ResponseEntity.ok(categoryService.getAllCategories());
    }

    @GetMapping("/get/{id}")
    public ResponseEntity<?> getSingleCategory(@PathVariable Integer id){
        return ResponseEntity.ok(categoryService.getSingleCategory(id));
    }

    @PostMapping("/add")
    public ResponseEntity<?> addCategory(@RequestBody @Valid Category category){
        categoryService.addCategory(category);
        return ResponseEntity.ok(new ApiResponse("<category-controller/addCategory>Category added successfully"));
    }

    @PutMapping("/update/{id}")
    public ResponseEntity<?> updateCategory(@PathVariable Integer id, @RequestBody @Valid Category category){
        categoryService.updateCategory(id, category);
        return ResponseEntity.ok(new ApiResponse("<category-controller/updateCategory>Category updated successfully"));
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<?> deleteCategory(@PathVariable Integer id){
        categoryService.deleteCategory(id);
        return ResponseEntity.ok(new ApiResponse("<category-controller/deleteCategory>Category deleted successfully"));
    }
}
