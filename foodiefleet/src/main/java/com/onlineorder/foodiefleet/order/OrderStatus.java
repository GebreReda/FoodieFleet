package com.onlineorder.foodiefleet.order;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name="t_orderstatus")
@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class OrderStatus {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private boolean processed;
    private boolean picked;
    private boolean delivered;
}
