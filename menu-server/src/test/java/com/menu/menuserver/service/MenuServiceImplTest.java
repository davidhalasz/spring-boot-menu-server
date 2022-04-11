package com.menu.menuserver.service;

import com.menu.menuserver.model.Menu;
import com.menu.menuserver.model.Role;
import com.menu.menuserver.model.User;
import com.menu.menuserver.repository.MenuRepository;
import com.menu.menuserver.repository.projection.MenuItem;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.mockito.junit.jupiter.MockitoExtension;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class MenuServiceImplTest {

    private MenuService underTest;

    @Mock
    private MenuRepository repository;

    @InjectMocks
    private MenuServiceImpl service;

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.openMocks(this);
        underTest = new MenuServiceImpl(repository);
    }

    private static final MenuItem menuItem = new MenuItem() {
        @Override
        public Long getId() {
            return menu1.getId();
        }

        @Override
        public Long getUserId() {
            return menu1.getUserId();
        }

        @Override
        public String getCompanyName() {
            return menu1.getCompanyName();
        }

        @Override
        public String[] getTypes() {
            return menu1.getTypes();
        }

        @Override
        public String[] getProducts() {
            return menu1.getProducts();
        }

        @Override
        public Integer[] getPrices() {
            return menu1.getPrices();
        }
    };


    private static final String[] types = {"drink"};
    private static final String[] products = {"coffee"};
    private static final Integer[] prices = {200};

    private static final Menu menu1 = new Menu(
            1L, 2L, "Company", types, products, prices);
    private static final Menu menu2 = new Menu(
            3L, 2L, "Company", types, products, prices);

    private static final List<MenuItem> menuItems = List.of(menuItem);



    @Test
    public void testSaveMenu() {
        when(repository.save(menu1)).thenReturn(menu1);
        Menu actual = underTest.saveMenu(menu1);
        assertThat(actual, equalTo(menu1));
    }

    @Test
    public void testDeleteMenu() {
        service.deleteMenu(menu1.getId());
        verify(repository, times(1)).deleteById(menu1.getId());
    }

    @Test
    public void testFindById() {
        when(repository.findById(menu1.getId())).thenReturn(Optional.of(menu1));
        Optional<Menu> actual = underTest.findById(menu1.getId());
        assertTrue(actual.isPresent() && menu1.equals(actual.get()));
    }

    @Test
    public void testFindMenusByUserId() {
        User user1 = new User();
        user1.setId(2L);
        user1.setUsername("urename22");
        user1.setPassword("123456");
        user1.setCreateTime(LocalDateTime.now());
        user1.setRole(Role.USER);
        when(repository.findAllMenusByUserId(user1.getId())).thenReturn(menuItems);
        List<MenuItem> current = underTest.findMenusByUserId(user1.getId());
        assertThat(current,equalTo(menuItems));
    }
}