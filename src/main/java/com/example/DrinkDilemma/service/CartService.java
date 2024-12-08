package com.example.DrinkDilemma.service;

import com.example.DrinkDilemma.domain.Menu;
import com.example.DrinkDilemma.dto.CartDTO;

import java.util.List;

public interface CartService {
    void addToCart(Menu menu, int quantity);
    List<CartDTO> getAllItems();
    void clearCart();
    void deleteFromCart(Long menuId);
    int getMenuQuantity(Long menuId);
}
