package com.onlineorder.foodiefleet.order;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name="t_useraccount")
@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class UserAccount {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String accountNumber;
    private String accountName;
    private double availableBalance;

}
