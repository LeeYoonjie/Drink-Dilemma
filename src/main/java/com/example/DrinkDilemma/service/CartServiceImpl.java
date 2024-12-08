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
        Cart cartItem = cartRepository.findByMenuIdx(menu.getIdx()).orElse(null);
        if (cartItem != null) {
            cartItem.setQuantity(cartItem.getQuantity() + quantity);
        } else {
            cartItem = Cart.builder()
                    .menu(menu)
                    .menuName(menu.getMenuName())
                    .price(menu.getPrice())
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
                        .price(cart.getMenu().getPrice())
                        .build())
                .collect(Collectors.toList());
    }

    @Override
    public int getMenuQuantity(Long menuId) {
        Optional<Cart> cartItem = cartRepository.findByMenuIdx(menuId);
        return cartItem.map(Cart::getQuantity).orElse(0); // 없으면 0 반환
    }

    @Override
    public void deleteFromCart(Long menuId) {
        Optional<Cart> cartItem = cartRepository.findByMenuIdx(menuId);
        cartItem.ifPresent(cart -> {
            if (cart.getQuantity() > 1) {
                cart.setQuantity(cart.getQuantity() - 1);
                cartRepository.save(cart);
            } else {
                cartRepository.delete(cart);
            }
        });
    }

    @Override
    public void clearCart() {
        cartRepository.deleteAll(); // 모든 장바구니 데이터 삭제
    }
}
