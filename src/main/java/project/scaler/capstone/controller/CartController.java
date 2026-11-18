package project.scaler.capstone.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import project.scaler.capstone.dto.CartDTOs.*;
import project.scaler.capstone.service.CartService;

@RestController
@RequestMapping("/api/v1/cart")
@Tag(name = "4. Cart & Checkout", description = "Endpoints for managing the shopping cart and checkout process")
public class CartController {

    private final CartService cartService;

    public CartController(CartService cartService) { this.cartService = cartService; }

    @PostMapping("/add")
    @Operation(summary = "Add to Cart", description = "Adds a product to the user's cart or updates the quantity if it already exists.")
    public ResponseEntity<CartResponse> addToCart(@RequestHeader("UserId") Long userId, @RequestBody AddToCartRequest request) {
        return ResponseEntity.ok(cartService.addToCart(userId, request));
    }

    @GetMapping
    @Operation(summary = "View Cart", description = "Retrieves the current user's cart including items, quantities, and totals.")
    public ResponseEntity<CartResponse> viewCart(@RequestHeader("UserId") Long userId) {
        return ResponseEntity.ok(cartService.getCartReview(userId));
    }

    @PostMapping("/checkout")
    @Operation(summary = "Checkout", description = "Finalizes the purchase, generates a mock order reference, and clears the cart.")
    public ResponseEntity<CheckoutResponse> checkout(@RequestHeader("UserId") Long userId, @RequestBody CheckoutRequest request) {
        return ResponseEntity.ok(cartService.checkout(userId, request));
    }
}