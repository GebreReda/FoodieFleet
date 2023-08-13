package com.onlineorder.foodiefleet.delivery;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name="t_deliveryregistration")
@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class DeliveryRegistration {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String deliveryNumber;
    private String firstName;
    private String lastName;
    private String email;

    @OneToOne(targetEntity = DeliveryLocation.class, cascade = CascadeType.ALL)
    @JoinColumn(name="deliverylocation", referencedColumnName = "id")
    private DeliveryLocation deliveryLocation;

    @OneToOne(targetEntity = DeliveryAccount.class, cascade = CascadeType.ALL)
    @JoinColumn(name="deliveryaccount", referencedColumnName = "id")
    private DeliveryAccount deliveryaccount;
}
