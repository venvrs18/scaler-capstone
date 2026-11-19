package project.scaler.capstone.service;

import org.springframework.stereotype.Service;
import project.scaler.capstone.dto.OrderDTOs.*;
import project.scaler.capstone.model.Order;
import project.scaler.capstone.repository.OrderRepository;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class OrderService {
    private final OrderRepository orderRepository;

    public OrderService(OrderRepository orderRepository) { this.orderRepository = orderRepository; }

    public List<OrderResponse> getOrderHistory(Long userId) {
        return orderRepository.findByUserIdOrderByOrderDateDesc(userId).stream()
                .map(this::mapToResponse)
                .collect(Collectors.toList());
    }

    public OrderTrackingResponse trackOrder(String orderReference) {
        Order order = orderRepository.findByOrderReference(orderReference)
                .orElseThrow(() -> new RuntimeException("Order not found"));
        return new OrderTrackingResponse(order.getOrderReference(), order.getStatus(), order.getOrderDate().plusDays(5).toString());
    }

    private OrderResponse mapToResponse(Order order) {
        List<OrderItemResponse> items = order.getItems().stream()
                .map(i -> new OrderItemResponse(i.getProduct().getId(), i.getProduct().getName(), i.getQuantity(), i.getPrice()))
                .collect(Collectors.toList());
        return new OrderResponse(order.getOrderReference(), order.getTotalAmount(), order.getStatus(), order.getDeliveryAddress(), order.getOrderDate(), items);
    }
}