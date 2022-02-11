package com.menu.menuserver.repository;

import com.menu.menuserver.model.Menu;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface MenuRepository extends JpaRepository<Menu, Long> {
    @Query("select * " +
            "from Menu" +
            "where m.userId = :userId")
    List<Menu> findAllMenusByUserId(@Param("userId") Long userId);
}
