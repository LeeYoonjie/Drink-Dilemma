package com.example.DrinkDilemma.controller;

import com.example.DrinkDilemma.dto.MenuDTO;
import com.example.DrinkDilemma.service.MenuService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

@Controller
@RequestMapping("/menu")
public class MenuController {

    @Autowired
    private MenuService menuService;

    @GetMapping("/cafe/{cafeId}")
    public String getMenuByCafeId(@PathVariable Long cafeId, Model model) {
        // 메뉴 리스트 조회
        List<MenuDTO> menus = menuService.findByCafeId(cafeId);
        model.addAttribute("menus", menus);
        return "menu_list"; // 메뉴 리스트 뷰 이름
    }
}
