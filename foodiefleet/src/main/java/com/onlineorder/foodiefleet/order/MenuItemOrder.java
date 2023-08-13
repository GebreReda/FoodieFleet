package com.onlineorder.foodiefleet.order;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name="t_menuitemorder")
@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class MenuItemOrder {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String itemName;
    private int quantity;
    private double price;
}
