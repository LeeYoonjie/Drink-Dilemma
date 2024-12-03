package com.example.DrinkDilemma.service;

import com.example.DrinkDilemma.domain.Menu;
import com.example.DrinkDilemma.domain.Cart;

import java.util.List;

public interface CartService {
    void addToCart(Menu menu, int quantity); // 장바구니에 추가
    List<Cart> getAllItems(); // 장바구니 모든 항목 조회
    void clearCart(); // 장바구니 비우기
}
