package com.example.DrinkDilemma.repository;

import com.example.DrinkDilemma.domain.Cart;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface CartRepository extends JpaRepository<Cart, Long> {
    Optional<Cart> findByMenuIdx(Long menuIdx); // 특정 메뉴와 관련된 장바구니 항목 조회
    void deleteByMenuIdx(Long menuIdx); // 메뉴의 기본 키(idx)를 기준으로 삭제
}
