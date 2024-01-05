package entity;

import lombok.*;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@NoArgsConstructor
@AllArgsConstructor
@Setter
@Getter
@ToString
@Entity
@Table(name = "order")
public class OrderEntity {
    @Id
    private String orderId;
    private String date;

    @ManyToOne
    @JoinColumn(name = "customerId")
    private Customer customer;

    @OneToMany(mappedBy = "orderEntity")
    private List<OrderDetail> orderDetails = new ArrayList<>();

    public OrderEntity(String orderId, String date , Customer customer)  {
        this.orderId = orderId;
        this.date = date;
        this.customer=customer;
    }
    public OrderEntity(String orderId, String date){
        this.orderId = orderId;
        this.date = date;
    }
}
