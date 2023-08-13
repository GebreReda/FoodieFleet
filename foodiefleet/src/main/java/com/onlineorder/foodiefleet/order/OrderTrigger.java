package com.onlineorder.foodiefleet.order;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name="t_ordertrigger")
@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class OrderTrigger {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String delivManNumber;
    private String orderNumber;
    private boolean processed;
    private boolean picked;
    private boolean delivered;
}
