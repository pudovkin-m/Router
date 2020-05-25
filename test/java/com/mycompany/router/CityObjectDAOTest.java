/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.router;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import static org.hamcrest.CoreMatchers.is;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;

public class CityObjectDAOTest {
    
    public CityObjectDAOTest() {
    }
    
    @Before
    public void setUp() {
    }
    
    @After
    public void tearDown() {
    }

    @Test
    public void testCreate() throws SQLException {
        System.out.println("create");
        DAO<CityObject, String> cityDao;                      
        final Connection connection = DriverManager.getConnection("jdbc:postgresql://localhost:5432/gid", "postgres", "3114");       
        cityDao = new CityObjectDAO(connection);
        final CityObject obj1 = new CityObject(0, "Test", 1.1, 1.1, "Test", "test", 0);
        final boolean result = cityDao.create(obj1);
        assertThat(result, is(true));
        //Clear test data.
        cityDao.delete(cityDao.read("Test"));
        connection.close();
    }
    
    @Test
    public void testRead() throws SQLException {
        System.out.println("read");
        DAO<CityObject, String> cityDao;                      
        final Connection connection = DriverManager.getConnection("jdbc:postgresql://localhost:5432/gid", "postgres", "3114");       
        cityDao = new CityObjectDAO(connection);        
        final CityObject cityObject = cityDao.read("Monument to Tatishchev");
        final CityObject expected = new CityObject();
        expected.setName("Monument to Tatishchev");
        expected.setLat(53.47389);
        expected.setLon(49.34861);
        expected.setObj_type("monument");
        expected.setDescrirption("Monument to V. N. Tatishchev - the founder of the city of Togliatti");
        expected.setPrice(0);
        assertThat(cityObject, is(expected));
        connection.close();
    }
    
    @Test
    public void testUpdate() throws SQLException {
        System.out.println("update");
        DAO<CityObject, String> cityDao;                      
        final Connection connection = DriverManager.getConnection("jdbc:postgresql://localhost:5432/gid", "postgres", "3114");       
        cityDao = new CityObjectDAO(connection);
        final CityObject updatedCityObject = new CityObject(1,"Monument to Tatishchev", 53.473890, 49.348610,"monument", "Test", 0);
        cityDao.update(updatedCityObject);
        final CityObject expected = cityDao.read("Monument to Tatishchev");
        assertThat(updatedCityObject, is(expected));
        cityDao.update(new CityObject(1,"Monument to Tatishchev", 53.473890, 49.348610,"monument", "Monument to V. N. Tatishchev - the founder of the city of Togliatti", 0));
        connection.close();
    }

    @Test
    public void testDelete() throws SQLException {
        System.out.println("delete");
        DAO<CityObject, String> cityDao;                      
        final Connection connection = DriverManager.getConnection("jdbc:postgresql://localhost:5432/gid", "postgres", "3114");       
        cityDao = new CityObjectDAO(connection);
        final CityObject cityObject = new CityObject(0, "Test", 1.1, 1.1, "Test", "test", 0);
        cityDao.create(cityObject);
        final CityObject state = cityDao.read("Test");
        boolean before = state.getId() != -1;
        cityObject.setId(state.getId());
        final boolean after = cityDao.delete(cityObject);
        assertTrue(before);
        assertTrue(after);
        connection.close();
    }    
}
