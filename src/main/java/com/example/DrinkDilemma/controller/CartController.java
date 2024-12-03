package com.example.DrinkDilemma.controller;

import com.example.DrinkDilemma.domain.Menu;
import com.example.DrinkDilemma.service.CartService;
import com.example.DrinkDilemma.service.MenuService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/cart")
public class CartController {

    @Autowired
    private CartService cartService;

    @Autowired
    private MenuService menuService;

    // 장바구니에 메뉴 추가
    @PostMapping("/cart/add")
    public String addToCart(@RequestParam Long menuId, @RequestParam int quantity, Model model) {
        Menu menu = menuService.getMenuById(menuId); // 서비스 메서드를 통해 메뉴 조회
        cartService.addToCart(menu, quantity);
        model.addAttribute("message", "메뉴가 장바구니에 추가되었습니다.");
        return "redirect:/cart/view";
    }

    // 장바구니 항목 조회
    @GetMapping("/cart/view")
    public String viewCart(Model model) {
        model.addAttribute("cartItems", cartService.getAllItems());
        return "cart";
    }

    // 장바구니 비우기
    @PostMapping("/cart/clear")
    public String clearCart(Model model) {
        cartService.clearCart();
        model.addAttribute("message", "장바구니가 비워졌습니다.");
        return "redirect:/cart/view";
    }
}
