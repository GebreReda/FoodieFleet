package com.onlineorder.foodiefleet.entity;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name="t_restlocation")
@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class RestLocation {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String street;
    private String state;
    private String city;
    private String zip;
}
