package com.onlineorder.foodiefleet.order;

import com.fasterxml.jackson.annotation.JsonFormat;
import jakarta.persistence.*;
import lombok.*;

import java.util.List;

@Entity
@Table(name="t_order")
@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class Order {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn
    private User user;

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name="userAccount")
    private UserAccount userAccount;
    private String orderNumber;

    @JsonFormat(pattern = "yyyy-MM-dd", shape = JsonFormat.Shape.STRING)
    private String dateCreated;
    private String deliveryStatus;

    @OneToMany(targetEntity = MenuItemOrder.class, cascade = CascadeType.ALL)
    @JoinColumn(name="order_id",referencedColumnName = "id")
    private List<MenuItemOrder> menuitemorder;

    @OneToOne(targetEntity = OrderStatus.class, cascade = CascadeType.ALL)
    @JoinColumn(name="orderstat", referencedColumnName = "id")
    private OrderStatus orderStatus;
}


