package com.example.DrinkDilemma.repository;

import com.example.DrinkDilemma.domain.Cart;
import com.example.DrinkDilemma.domain.Menu;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CartRepository extends JpaRepository<Cart, Long> {
    List<Cart> findByMenu(Menu menu); // 특정 메뉴와 관련된 장바구니 항목 조회
}
