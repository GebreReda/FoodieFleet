package com.onlineorder.foodiefleet.delivery;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name="t_deliveryaccount")
@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class DeliveryAccount {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String accountNumber;
    private String accountName;
    private double availableBalance;

}
