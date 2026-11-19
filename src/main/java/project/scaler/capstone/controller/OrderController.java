package project.scaler.capstone.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import project.scaler.capstone.dto.OrderDTOs.*;
import project.scaler.capstone.service.OrderService;
import java.util.List;

@RestController
@RequestMapping("/api/v1/orders")
@Tag(name = "5. Order Management", description = "View history and track orders")
public class OrderController {
    private final OrderService orderService;

    public OrderController(OrderService orderService) { this.orderService = orderService; }

    @GetMapping("/history")
    @Operation(summary = "Order History", description = "Fetch all past orders for the authenticated user")
    public ResponseEntity<List<OrderResponse>> getHistory(@RequestHeader("UserId") Long userId) {
        return ResponseEntity.ok(orderService.getOrderHistory(userId));
    }

    @GetMapping("/{orderReference}/track")
    @Operation(summary = "Track Order", description = "Check the delivery status of a specific order")
    public ResponseEntity<OrderTrackingResponse> trackOrder(@PathVariable String orderReference) {
        return ResponseEntity.ok(orderService.trackOrder(orderReference));
    }
}