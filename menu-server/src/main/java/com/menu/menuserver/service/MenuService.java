package com.menu.menuserver.service;

import com.menu.menuserver.model.Menu;

public interface MenuService {
    Menu saveMenu(Menu menu);

    void deleteMenu(Long id);
}
