package com.mycompany.router;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import static org.hamcrest.CoreMatchers.is;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.*;

public class UserDAOTest {
    
    public UserDAOTest() {
    }
    
    @Before
    public void setUp() {
//        try {
//            String user = "postgres";
//            String password = "1";
//            String url = "jdbc:postgresql://localhost:5432/phones_magazine";
//            connection = DriverManager.getConnection(url, user, password);
//            dao = new UserDAO(connection);
//        } catch (SQLException e) {
//            e.printStackTrace();
//        }
    }
    
    @After
    public void tearDown() {
//        try {
//            connection.close();
//        } catch (SQLException e) {
//            e.printStackTrace();
//        }
    }

    @Test
    public void testCreate() throws SQLException {
        System.out.println("create");
        DAO<User, String> dao;                      
        final Connection connection = DriverManager.getConnection("jdbc:postgresql://localhost:5432/gid", "postgres", "3114");       
        dao = new UserDAO(connection);
        final User user = new User(0, "test", "test", new User.Role(1, "admin"));
        final boolean result = dao.create(user);
        assertThat(result, is(true));
        dao.delete(dao.read("test"));
    }
    
    @Test
    public void testRead() throws SQLException {
        System.out.println("read");
        DAO<User, String> dao;                      
        final Connection connection = DriverManager.getConnection("jdbc:postgresql://localhost:5432/gid", "postgres", "3114");       
        dao = new UserDAO(connection);        
        final User user = dao.read("Misha");
        final User expected = new User();
        expected.setLogin("Misha");
        expected.setPassword("123");
        expected.setRole(new User.Role(1, "admin"));
        assertThat(user, is(expected));
        connection.close();
    }

    @Test
    public void testUpdate() throws SQLException {
        System.out.println("update");
        DAO<User, String> dao;                      
        final Connection connection = DriverManager.getConnection("jdbc:postgresql://localhost:5432/gid", "postgres", "3114");       
        dao = new UserDAO(connection);
        final User updatedTestUser = new User(2,"Anton", "321", new User.Role(1, "admin"));
        dao.update(updatedTestUser);
        final User expected = dao.read("Anton");
        assertThat(updatedTestUser, is(expected));
        dao.update(new User(2,"Anton", "1234", new User.Role(1, "admin")));
        connection.close();
    }

    @Test
    public void testDelete() throws SQLException {
        System.out.println("delete");
        DAO<User, String> dao;                      
        final Connection connection = DriverManager.getConnection("jdbc:postgresql://localhost:5432/gid", "postgres", "3114");       
        dao = new UserDAO(connection);
        final User user = new User(0, "test", "test", new User.Role(1, "admin"));
        dao.create(user);
        final User state = dao.read("test");
        boolean before = state.getId() != -1;
        user.setId(state.getId());
        final boolean after = dao.delete(user);
        assertTrue(before);
        assertTrue(after);
        connection.close();
    }
    
}
