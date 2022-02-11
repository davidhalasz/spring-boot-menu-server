package com.menu.menuserver.repository;

import com.menu.menuserver.model.Menu;
import com.menu.menuserver.repository.projection.MenuItem;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface MenuRepository extends JpaRepository<Menu, Long> {
    @Query("select " +
            "m.id as id, m.products as products " +
            "from Menu m " +
            "where m.userId = :userId")
    List<MenuItem> findAllMenusByUserId(@Param("userId") Long userId);
}
