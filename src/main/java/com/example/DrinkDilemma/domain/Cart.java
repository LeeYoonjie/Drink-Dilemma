package com.example.DrinkDilemma.domain;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "cart")
@Getter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Cart {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "menu_id", nullable = false)
    private Menu menu;

    @Column(name = "menu_name", nullable = false)
    private String menuName; // 메뉴 이름 필드 추가

    @Column(nullable = false)
    private int price; // 가격 필드 추가

    private int quantity;

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }
}
