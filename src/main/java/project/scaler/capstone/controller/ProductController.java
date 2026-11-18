package project.scaler.capstone.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import project.scaler.capstone.dto.ProductDTOs.ProductResponse;
import project.scaler.capstone.service.ProductService;

import java.util.List;

@RestController
@RequestMapping("/api/v1/products")
@Tag(name = "3. Product Catalog", description = "Endpoints for browsing and searching products")
public class ProductController {

    private final ProductService productService;

    public ProductController(ProductService productService) { this.productService = productService; }

    @GetMapping
    @Operation(summary = "Get All Products", description = "Retrieves a full list of all available products.")
    public ResponseEntity<List<ProductResponse>> getAllProducts() {
        return ResponseEntity.ok(productService.getAllProducts());
    }

    @GetMapping("/{id}")
    @Operation(summary = "Get Product Details", description = "Retrieves detailed information for a single product by its ID.")
    public ResponseEntity<ProductResponse> getProductDetails(@PathVariable Long id) {
        return ResponseEntity.ok(productService.getProductDetails(id));
    }

    @GetMapping("/category/{categoryId}")
    @Operation(summary = "Get Products by Category", description = "Retrieves all products belonging to a specific category.")
    public ResponseEntity<List<ProductResponse>> getByCategory(@PathVariable Long categoryId) {
        return ResponseEntity.ok(productService.getProductsByCategory(categoryId));
    }

    @GetMapping("/search")
    @Operation(summary = "Search Products", description = "Searches for products based on a keyword in the name or description.")
    public ResponseEntity<List<ProductResponse>> searchProducts(@RequestParam String keyword) {
        return ResponseEntity.ok(productService.searchProducts(keyword));
    }
}