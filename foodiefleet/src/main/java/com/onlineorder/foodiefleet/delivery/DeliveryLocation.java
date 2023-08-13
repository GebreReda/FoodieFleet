package com.onlineorder.foodiefleet.delivery;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name="t_deliverylocation")
@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class DeliveryLocation {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String street;
    private String state;
    private String city;
    private String zip;
}
