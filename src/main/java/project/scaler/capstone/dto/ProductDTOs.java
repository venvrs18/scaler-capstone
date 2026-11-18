package project.scaler.capstone.dto;

import java.math.BigDecimal;

public class ProductDTOs {
    public record ProductResponse(Long id, String name, String description, String specifications, String imageUrl, BigDecimal price, String categoryName) {}
}