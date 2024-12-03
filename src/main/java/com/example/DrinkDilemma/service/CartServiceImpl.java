package com.example.DrinkDilemma.service;

import com.example.DrinkDilemma.domain.Cart;
import com.example.DrinkDilemma.domain.Menu;
import com.example.DrinkDilemma.repository.CartRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CartServiceImpl implements CartService {
    @Autowired
    private CartRepository cartRepository;

    @Override
    public void addToCart(Menu menu, int quantity) {
        Cart cart = Cart.builder()
                .menu(menu)
                .quantity(quantity)
                .build();
        cartRepository.save(cart);
    }

    @Override
    public List<Cart> getAllItems() {
        return cartRepository.findAll();
    }

    @Override
    public void clearCart() {
        cartRepository.deleteAll();
    }
}
