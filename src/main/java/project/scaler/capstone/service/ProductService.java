package project.scaler.capstone.service;

import org.springframework.stereotype.Service;
import project.scaler.capstone.dto.ProductDTOs.ProductResponse;
import project.scaler.capstone.model.Product;
import project.scaler.capstone.repository.ProductRepository;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class ProductService {

    private final ProductRepository productRepository;

    public ProductService(ProductRepository productRepository) {
        this.productRepository = productRepository;
    }

    public List<ProductResponse> getAllProducts() {
        return productRepository.findAll().stream().map(this::mapToDTO).collect(Collectors.toList());
    }

    public List<ProductResponse> getProductsByCategory(Long categoryId) {
        return productRepository.findByCategoryId(categoryId).stream().map(this::mapToDTO).collect(Collectors.toList());
    }

    public ProductResponse getProductDetails(Long productId) {
        Product product = productRepository.findById(productId)
                .orElseThrow(() -> new RuntimeException("Product not found"));
        return mapToDTO(product);
    }

    public List<ProductResponse> searchProducts(String keyword) {
        return productRepository.searchByKeyword(keyword).stream().map(this::mapToDTO).collect(Collectors.toList());
    }

    private ProductResponse mapToDTO(Product product) {
        return new ProductResponse(
                product.getId(),
                product.getName(),
                product.getDescription(),
                product.getSpecifications(),
                product.getImageUrl(),
                product.getPrice(),
                product.getCategory().getName()
        );
    }
}
