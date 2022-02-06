package com.menu.menuserver.service;

import com.menu.menuserver.model.Menu;
import com.menu.menuserver.repository.MenuRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class MenuServiceImpl implements MenuService {
    @Autowired
    private MenuRepository menuRepository;

    public MenuServiceImpl(MenuRepository menuRepository) {
        this.menuRepository = menuRepository;
    }

    @Override
    public Menu saveMenu(Menu menu) {
        return menuRepository.save(menu);
    }

    @Override
    public void deleteMenu(Long id) {
        menuRepository.deleteById(id);
    }
    
}
