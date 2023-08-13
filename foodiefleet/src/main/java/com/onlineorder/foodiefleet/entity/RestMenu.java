package com.onlineorder.foodiefleet.entity;

import jakarta.persistence.*;
import lombok.*;

import java.util.List;

@Entity
@Table(name="t_restmenu")
@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class RestMenu {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String menuName;

    @OneToMany(targetEntity=MenuSection.class, cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    @JoinColumn(name="menu",referencedColumnName = "id")
    private List<MenuSection> menuSectionList;
}
