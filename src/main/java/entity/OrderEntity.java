package entity;

import lombok.*;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@NoArgsConstructor
@AllArgsConstructor
@Setter
@Getter

@Entity
@Table(name = "orderEntity")
public class OrderEntity {


    @Override
    public String toString() {
        return "OrderEntity{" +
                "orderId='" + orderId + '\'' +
                ", date='" + date + '\'' +
                ", Customer'"+customer.getName()+ '\''+
                '}';
    }

    @Id
    private String orderId;
    private String date;

    @ManyToOne
    @JoinColumn(name = "customer_Id")
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
