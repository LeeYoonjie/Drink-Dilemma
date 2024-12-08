package com.example.DrinkDilemma.service;

import com.example.DrinkDilemma.domain.Cart;
import com.example.DrinkDilemma.domain.Menu;
import com.example.DrinkDilemma.dto.CartDTO;
import com.example.DrinkDilemma.repository.CartRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class CartServiceImpl implements CartService {

    @Autowired
    private CartRepository cartRepository;

    @Override
    public void addToCart(Menu menu, int quantity) {
        Cart cartItem = cartRepository.findByMenu(menu).orElse(null);
        if (cartItem != null) {
            cartItem.setQuantity(cartItem.getQuantity() + quantity);
        } else {
            cartItem = Cart.builder()
                    .menu(menu)
                    .menuName(menu.getMenuName()) // 메뉴 이름 설정
                    .price(menu.getPrice())       // 가격 설정
                    .quantity(quantity)
                    .build();
        }
        cartRepository.save(cartItem);
    }

    @Override
    public List<CartDTO> getAllItems() {
        return cartRepository.findAll().stream()
                .map(cart -> CartDTO.builder()
                        .menuId(cart.getMenu().getIdx())
                        .menuName(cart.getMenu().getMenuName())
                        .quantity(cart.getQuantity())
                        .price(cart.getMenu().getPrice()) // int 그대로 사용
                        .build())
                .collect(Collectors.toList());
    }

    @Override
    public void clearCart() {
        cartRepository.deleteAll();
    }
}
