package project.scaler.capstone.dto;

import project.scaler.capstone.model.Payment.PaymentStatus;
import java.math.BigDecimal;
import java.time.LocalDateTime;

public class PaymentDTOs {
    public record PaymentRequest(String orderReference, String paymentMethod, String cardToken) {}
    public record PaymentReceipt(String transactionId, String orderReference, BigDecimal amount, PaymentStatus status, LocalDateTime paymentDate) {}
}