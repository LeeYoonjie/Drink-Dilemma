package com.example.DrinkDilemma.domain;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "menu")
@ToString
@Getter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Menu {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long idx;

    @Column(name = "menu_name", nullable = false)
    private String menuName;

    @Column(nullable = false)
    private int price;

    @ManyToOne
    @JoinColumn(name = "cafe_id", nullable = false)
    private Cafe cafe;
}
