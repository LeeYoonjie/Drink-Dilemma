package com.example.DrinkDilemma.controller;

import com.example.DrinkDilemma.dto.MenuDTO;
import com.example.DrinkDilemma.service.CartService;
import com.example.DrinkDilemma.service.MenuService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;
import java.util.stream.Collectors;

@Controller
@RequestMapping("/menu")
public class MenuController {

    @Autowired
    private MenuService menuService;

    @Autowired
    private CartService cartService;

    @GetMapping("/cafe/{cafeId}")
    public String getMenuByCafeId(@PathVariable Long cafeId, Model model) {
        // 메뉴 리스트 조회
        List<MenuDTO> menus = menuService.findByCafeId(cafeId);

        // 각 메뉴에 수량 정보를 추가
        List<MenuDTO> menusWithQuantity = menus.stream()
                .map(menu -> {
                    int quantity = cartService.getMenuQuantity(menu.getIdx());
                    menu.setQuantity(quantity); // 수량 추가
                    return menu;
                })
                .collect(Collectors.toList());

        model.addAttribute("menus", menusWithQuantity);
        return "menu_list"; // 메뉴 리스트 뷰 이름
    }
}
