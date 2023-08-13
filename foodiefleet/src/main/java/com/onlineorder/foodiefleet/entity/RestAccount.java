package com.onlineorder.foodiefleet.entity;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name="t_restaccount")
@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class RestAccount {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String accountNumber;
    private String accountName;
    private double availableBalance;

}
