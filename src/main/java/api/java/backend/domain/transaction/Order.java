package api.java.backend.domain.transaction;

import api.java.backend.domain.security.User;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Entity
@AllArgsConstructor
@NoArgsConstructor
@Data
@Table(name = "orders")
public class Order {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @ManyToOne
    @JoinColumn(name = "user_id")
    private User user;
    private double total;
    private LocalDate date;
    @OneToMany(mappedBy="order")
    private List<OrderItem> items = new ArrayList<>();
    @Enumerated(EnumType.STRING)
    private  OrderStatus status;
    public enum OrderStatus { // Nueva enumeración para los estados de pago
        PENDING_PAYMENT,
        PAID,
        CANCELLED,
        REFUNDED,
        SHIPPED,
        DELIVERED
    }

    public void cancelOrder(){
        if(this.status==OrderStatus.PENDING_PAYMENT ||
        this.status == OrderStatus.PAID)
        {
            this.status = OrderStatus.CANCELLED;
        }else{
            throw new IllegalStateException("Cannot cancel order that has already been shipped or delivered");
        }
    }

    public void markAsShipped(){
        if(this.status == OrderStatus.PAID ){
            this.status = OrderStatus.SHIPPED;
        }else {
            throw new IllegalStateException("Cannot mark order as shipped before it has been paid.");
        }
    }

    public void markAsDelivered(){
        if(this.status == OrderStatus.SHIPPED ){
            this.status = OrderStatus.DELIVERED;
        }else {
            throw new IllegalStateException("Cannot mark order as delivered before it has been shipped.");
        }
    }

    public void refund() {
        if (this.status == OrderStatus.PAID || this.status == OrderStatus.SHIPPED || this.status == OrderStatus.DELIVERED) {
            this.status = OrderStatus.REFUNDED;
        } else {
            throw new IllegalStateException("Cannot request a refund for an order that has not been paid, shipped, or delivered.");
        }
    }

}
