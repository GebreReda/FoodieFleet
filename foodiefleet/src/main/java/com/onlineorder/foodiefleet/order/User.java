package com.onlineorder.foodiefleet.order;

import jakarta.persistence.*;
import lombok.*;

import java.util.List;

@Entity
@Table(name="t_user")
@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    String firstName;
    String lastName;
    String email;
    String role;

    @OneToMany(mappedBy = "user")
    private List<Order> order;
}
