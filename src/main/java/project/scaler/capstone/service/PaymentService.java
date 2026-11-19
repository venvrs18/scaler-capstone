package project.scaler.capstone.service;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import project.scaler.capstone.dto.PaymentDTOs.*;
import project.scaler.capstone.model.Order;
import project.scaler.capstone.model.Payment;
import project.scaler.capstone.repository.OrderRepository;
import project.scaler.capstone.repository.PaymentRepository;
import java.util.UUID;

@Service
public class PaymentService {
    private final PaymentRepository paymentRepository;
    private final OrderRepository orderRepository;

    public PaymentService(PaymentRepository paymentRepository, OrderRepository orderRepository) {
        this.paymentRepository = paymentRepository;
        this.orderRepository = orderRepository;
    }

    @Transactional
    public PaymentReceipt processPayment(PaymentRequest request) {
        Order order = orderRepository.findByOrderReference(request.orderReference())
                .orElseThrow(() -> new RuntimeException("Order not found"));

        // Mock payment gateway logic
        Payment payment = new Payment();
        payment.setOrder(order);
        payment.setAmount(order.getTotalAmount());
        payment.setPaymentMethod(request.paymentMethod());
        payment.setTransactionId("TXN-" + UUID.randomUUID().toString().substring(0, 10).toUpperCase());
        payment.setStatus(Payment.PaymentStatus.SUCCESS);

        paymentRepository.save(payment);

        order.setStatus(Order.OrderStatus.CONFIRMED);
        orderRepository.save(order);

        return new PaymentReceipt(payment.getTransactionId(), order.getOrderReference(), payment.getAmount(), payment.getStatus(), payment.getPaymentDate());
    }
}