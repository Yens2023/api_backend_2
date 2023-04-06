package api.java.backend.repository.transaction;

import api.java.backend.domain.transaction.Order;
import org.springframework.data.jpa.repository.JpaRepository;

public interface OrderRepository extends JpaRepository<Order, Long> {
}
