package com.example.DrinkDilemma.controller;

import com.example.DrinkDilemma.domain.Menu;
import com.example.DrinkDilemma.dto.CartDTO;
import com.example.DrinkDilemma.service.CartService;
import com.example.DrinkDilemma.service.MenuService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequestMapping("/cart")
public class CartController {

    @Autowired
    private CartService cartService;

    @Autowired
    private MenuService menuService;

    // 장바구니에 메뉴 추가
    @PostMapping("/add")
    public String addToCart(@RequestParam Long menuId, @RequestParam int quantity, Model model) {
        try {
            Menu menu = menuService.getMenuById(menuId); // 메뉴 조회
            cartService.addToCart(menu, quantity); // 장바구니에 추가
            model.addAttribute("message", "장바구니에 추가되었습니다.");
        } catch (Exception e) {
            model.addAttribute("error", "장바구니 추가 중 오류 발생: " + e.getMessage());
        }
        return "redirect:/cafe/search"; // 검색 결과 페이지로 리다이렉트
    }

    // 장바구니 조회
    @GetMapping("/view")
    public String viewCart(Model model) {
        List<CartDTO> cartItems = cartService.getAllItems();
        int totalAmount = cartItems.stream()
                .mapToInt(item -> item.getQuantity() * item.getPrice())
                .sum();
        model.addAttribute("cartItems", cartItems);
        model.addAttribute("totalAmount", totalAmount); // 총계 추가
        return "cart";
    }
    // 장바구니 비우기
    @PostMapping("/clear")
    public String clearCart(Model model) {
        cartService.clearCart();
        model.addAttribute("message", "장바구니가 비워졌습니다.");
        return "redirect:/cart/view";
    }
}
