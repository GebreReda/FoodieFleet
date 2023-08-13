package com.onlineorder.foodiefleet.order;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name="t_myitemorder")
@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
public class MyItemOrder {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String itemName;
    private int quantity;
    private double price;
//    @ManyToOne
//    @JsonIgnore
//    private Order order;
}
