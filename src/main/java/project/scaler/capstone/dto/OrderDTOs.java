package project.scaler.capstone.dto;

import project.scaler.capstone.model.Order.OrderStatus;
import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;

public class OrderDTOs {
    public record OrderItemResponse(Long productId, String productName, Integer quantity, BigDecimal price) {}
    public record OrderResponse(String orderReference, BigDecimal totalAmount, OrderStatus status, String deliveryAddress, LocalDateTime orderDate, List<OrderItemResponse> items) {}
    public record OrderTrackingResponse(String orderReference, OrderStatus status, String estimatedDeliveryDate) {}
}