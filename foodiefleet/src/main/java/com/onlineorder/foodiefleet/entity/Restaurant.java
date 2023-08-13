package com.onlineorder.foodiefleet.entity;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name="t_restaurant")
@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class Restaurant {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String restaurantName;
    private String cuisineType;

    @OneToOne(targetEntity = RestLocation.class, cascade = CascadeType.ALL)
    @JoinColumn(name="location", referencedColumnName = "id")
    private RestLocation restLocation;

    @OneToOne(targetEntity = RestMenu.class, cascade = CascadeType.ALL)
    @JoinColumn(name="menu", referencedColumnName = "id")
    private RestMenu restaurantMenu;
}
