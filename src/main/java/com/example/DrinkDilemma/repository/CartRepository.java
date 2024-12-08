package com.example.DrinkDilemma.repository;

import com.example.DrinkDilemma.domain.Cart;
import com.example.DrinkDilemma.domain.Menu;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface CartRepository extends JpaRepository<Cart, Long> {
    Optional<Cart> findByMenu(Menu menu); // 특정 메뉴와 관련된 장바구니 항목 조회
}
