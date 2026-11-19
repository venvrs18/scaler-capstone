package project.scaler.capstone.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import project.scaler.capstone.model.Order;
import java.util.List;
import java.util.Optional;

public interface OrderRepository extends JpaRepository<Order, Long> {
    List<Order> findByUserIdOrderByOrderDateDesc(Long userId);
    Optional<Order> findByOrderReference(String orderReference);
}