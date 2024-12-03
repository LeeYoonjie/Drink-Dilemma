package com.example.DrinkDilemma.controller;

import com.example.DrinkDilemma.domain.Cafe;
import com.example.DrinkDilemma.dto.MenuDTO;
import com.example.DrinkDilemma.repository.CafeRepository;
import com.example.DrinkDilemma.service.MenuService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

@Controller
@RequestMapping("/menu")
public class MenuController {

    @Autowired
    private MenuService menuService;

    @Autowired
    private CafeRepository cafeRepository;

    @GetMapping
    public String getMenuByCafeId(@RequestParam Long cafeId, Model model) {
        List<MenuDTO> menus = menuService.findByCafeId(cafeId);

        Cafe cafe = cafeRepository.findById(cafeId)
                .orElseThrow(() -> new RuntimeException("Cafe not found with ID: " + cafeId));
        model.addAttribute("menus", menus);
        model.addAttribute("cafeName", cafe.getStoreName());
        return "menu"; // menu.html 템플릿
    }
}
