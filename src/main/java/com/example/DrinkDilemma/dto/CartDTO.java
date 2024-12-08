package com.example.DrinkDilemma.dto;

import lombok.Builder;
import lombok.Getter;

@Builder
@Getter
public class CartDTO {
    private final Long menuId;
    private final String menuName;
    private final int quantity;
    private final int price; // int 타입

    // 총액 계산 메서드
    public int getTotalPrice() {
        return price * quantity;
    }
}
