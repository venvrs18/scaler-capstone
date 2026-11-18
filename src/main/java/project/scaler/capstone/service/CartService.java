package project.scaler.capstone.service;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import project.scaler.capstone.dto.CartDTOs.*;
import project.scaler.capstone.model.Cart;
import project.scaler.capstone.model.CartItem;
import project.scaler.capstone.model.Product;
import project.scaler.capstone.repository.CartRepository;
import project.scaler.capstone.repository.ProductRepository;

import java.math.BigDecimal;
import java.util.List;
import java.util.Optional;
import java.util.UUID;
import java.util.stream.Collectors;

@Service
public class CartService {

    private final CartRepository cartRepository;
    private final ProductRepository productRepository;

    public CartService(CartRepository cartRepository, ProductRepository productRepository) {
        this.cartRepository = cartRepository;
        this.productRepository = productRepository;
    }

    @Transactional
    public CartResponse addToCart(Long userId, AddToCartRequest request) {
        Product product = productRepository.findById(request.productId())
                .orElseThrow(() -> new RuntimeException("Product not found"));

        Cart cart = cartRepository.findByUserId(userId).orElseGet(() -> {
            Cart newCart = new Cart();
            newCart.setUserId(userId);
            return newCart;
        });

        // Check if item already exists in cart, update quantity if so
        Optional<CartItem> existingItem = cart.getItems().stream()
                .filter(item -> item.getProduct().getId().equals(product.getId()))
                .findFirst();

        if (existingItem.isPresent()) {
            existingItem.get().setQuantity(existingItem.get().getQuantity() + request.quantity());
        } else {
            CartItem newItem = new CartItem();
            newItem.setProduct(product);
            newItem.setQuantity(request.quantity());
            newItem.setPriceAtAddition(product.getPrice());
            cart.addItem(newItem);
        }

        cartRepository.save(cart);
        return getCartReview(userId);
    }

    public CartResponse getCartReview(Long userId) {
        Cart cart = cartRepository.findByUserId(userId)
                .orElseThrow(() -> new RuntimeException("Cart is empty"));

        List<CartItemResponse> itemResponses = cart.getItems().stream().map(item -> new CartItemResponse(
                item.getProduct().getId(),
                item.getProduct().getName(),
                item.getQuantity(),
                item.getProduct().getPrice(),
                item.getProduct().getPrice().multiply(BigDecimal.valueOf(item.getQuantity()))
        )).collect(Collectors.toList());

        BigDecimal total = itemResponses.stream()
                .map(CartItemResponse::totalPrice)
                .reduce(BigDecimal.ZERO, BigDecimal::add);

        return new CartResponse(cart.getId(), itemResponses, total);
    }

    @Transactional
    public CheckoutResponse checkout(Long userId, CheckoutRequest request) {
        Cart cart = cartRepository.findByUserId(userId)
                .orElseThrow(() -> new RuntimeException("No active cart found"));

        if (cart.getItems().isEmpty()) {
            throw new RuntimeException("Cart is empty");
        }

        BigDecimal totalAmount = getCartReview(userId).cartTotal();

        // MOCK: Generate Order ID and clear the cart
        String orderReference = "ORD-" + UUID.randomUUID().toString().substring(0, 8).toUpperCase();

        cart.getItems().clear();
        cartRepository.save(cart);

        return new CheckoutResponse(
                "SUCCESS",
                orderReference,
                totalAmount,
                "Order placed successfully using " + request.paymentMethod() + " to " + request.deliveryAddress()
        );
    }
}