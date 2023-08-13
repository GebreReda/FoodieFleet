package com.onlineorder.foodiefleet.entity;

import jakarta.persistence.*;
import lombok.*;

import java.util.List;

@Entity
@Table(name="t_menusection")
@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class MenuSection {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String foodSectionName;

   @OneToMany(targetEntity=MenuItems.class, cascade = CascadeType.ALL)
   @JoinColumn(name="menusection",referencedColumnName = "id")
    private List<MenuItems> foodMenuList;
}
