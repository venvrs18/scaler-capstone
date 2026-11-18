package project.scaler.capstone.dto;

import java.math.BigDecimal;
import java.util.List;

public class CartDTOs {
    public record AddToCartRequest(Long productId, Integer quantity) {}

    public record CartItemResponse(Long productId, String productName, Integer quantity, BigDecimal unitPrice, BigDecimal totalPrice) {}

    public record CartResponse(Long cartId, List<CartItemResponse> items, BigDecimal cartTotal) {}

    public record CheckoutRequest(String deliveryAddress, String paymentMethod) {}

    public record CheckoutResponse(String status, String orderReference, BigDecimal amountPaid, String message) {}
}