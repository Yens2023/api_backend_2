package api.java.backend.repository.transaction;

import api.java.backend.domain.transaction.OrderItem;
import org.springframework.data.jpa.repository.JpaRepository;

public interface OrderItemRepository extends JpaRepository<OrderItem, Long> {
}
