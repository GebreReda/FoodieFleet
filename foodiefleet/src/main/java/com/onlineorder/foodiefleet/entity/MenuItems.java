package com.onlineorder.foodiefleet.entity;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name="t_menuitems")
@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class MenuItems {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String foodName;
    private String foodDescription;
    private double foodPrice;
}
