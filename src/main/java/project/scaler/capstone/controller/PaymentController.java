package project.scaler.capstone.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import project.scaler.capstone.dto.PaymentDTOs.*;
import project.scaler.capstone.service.PaymentService;

@RestController
@RequestMapping("/api/v1/payments")
@Tag(name = "6. Payment", description = "Process secure transactions and generate receipts")
public class PaymentController {
    private final PaymentService paymentService;

    public PaymentController(PaymentService paymentService) { this.paymentService = paymentService; }

    @PostMapping("/process")
    @Operation(summary = "Process Payment", description = "Submit payment details to finalize an order and generate a receipt")
    public ResponseEntity<PaymentReceipt> processPayment(@RequestBody PaymentRequest request) {
        return ResponseEntity.ok(paymentService.processPayment(request));
    }
}