package com.example.DrinkDilemma.service;

import com.example.DrinkDilemma.domain.Cafe;
import com.example.DrinkDilemma.domain.Menu;
import com.example.DrinkDilemma.dto.MenuDTO;
import com.example.DrinkDilemma.repository.MenuRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class MenuServiceImpl implements MenuService {

    @Autowired
    private MenuRepository menuRepository;

    @Override
    public void saveMenu(String menuName, int price, Cafe cafe) { // int로 설정
        Menu menu = Menu.builder()
                .menuName(menuName)
                .price(price) // int로 처리
                .cafe(cafe)
                .build();
        menuRepository.save(menu);
    }

    @Override
    public void saveMenu(String menuName, String price, Cafe cafe) {

    }

    @Override
    public List<MenuDTO> findByCafeId(long cafeId) {
        return menuRepository.findByCafeIdx(cafeId).stream()
                .map(menu -> MenuDTO.builder()
                        .idx(menu.getIdx())
                        .menuName(menu.getMenuName())
                        .price(menu.getPrice()) // int로 처리
                        .build())
                .toList();
    }

    @Override
    public Menu getMenuById(Long menuId) {
        return menuRepository.findById(menuId)
                .orElseThrow(() -> new RuntimeException("Menu not found with ID: " + menuId));
    }
}
