package com.uncodigo.blogspringapi.controller;

import com.uncodigo.blogspringapi.payload.CategoryDto;
import com.uncodigo.blogspringapi.service.CategoryService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Tag(name = "Category", description = "Categories blog management APIs")
@RestController
@RequestMapping("/api/categories")
public class CategoryController {

    private CategoryService categoryService;

    public CategoryController(CategoryService categoryService) {
        this.categoryService = categoryService;
    }

    // Build add category REST API
    @Operation(summary = "Create a Category", description = "Create Category")
    @PreAuthorize("hasRole('ADMIN')")
    @PostMapping
    public ResponseEntity<CategoryDto> addCategory(@RequestBody CategoryDto categoryDto) {
        CategoryDto savedCategory = categoryService.addCategory(categoryDto);
        return new ResponseEntity<>(savedCategory, HttpStatus.CREATED);
    }

    // Build Get Category REST API
    @Operation(summary = "Get a Category by ID", description = "Get one Category by ID")
    @GetMapping("/{id}")
    public ResponseEntity<CategoryDto> getCategory(@PathVariable(name = "id") Long categoryId) {
        CategoryDto categoryDto = categoryService.getCategory(categoryId);
        return ResponseEntity.ok(categoryDto);
    }

    // Build Get All Categories REST API
    @Operation(summary = "Get all Categories", description = "Get all categories registry")
    @GetMapping
    public ResponseEntity<List<CategoryDto>> getCategories() {
        return ResponseEntity.ok(categoryService.getAllCategories());
    }

    // Build Update Category REST API
    @Operation(summary = "Edit a Category", description = "Edit one category by ID")
    @PreAuthorize("hasRole('ADMIN')")
    @PutMapping("/{id}")
    public ResponseEntity<CategoryDto> updateCategory(@RequestBody CategoryDto categoryDto,
                                                      @PathVariable(name = "id") Long categoryId) {
        return ResponseEntity.ok(categoryService.updateCategory(categoryDto, categoryId));
    }

    // Build Delete Category REST API
    @Operation(summary = "Delete a Category by ID", description = "Delete a Category by ID")
    @PreAuthorize("hasRole('ADMIN')")
    @DeleteMapping("/{id}")
    public ResponseEntity<CategoryDto> deleteCategory(@PathVariable(name = "id") Long categoryId) {
        return new ResponseEntity<>(categoryService.deleteCategory(categoryId), HttpStatus.OK);
    }
}
