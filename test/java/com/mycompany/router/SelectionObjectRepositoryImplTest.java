package com.mycompany.router;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Comparator;
import java.util.List;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.*;

public class SelectionObjectRepositoryImplTest {
    
    public SelectionObjectRepositoryImplTest() {
    }
    
    @Before
    public void setUp() {
    }
    
    @After
    public void tearDown() {
    }

    @Test
    public void testGetSelectionPriceLess() throws SQLException {
        SelectionObjectRepository<SelectionObject> repository;
        Connection connection = DriverManager.getConnection("jdbc:postgresql://localhost:5432/gid", "postgres", "3114");
        repository = new SelectionObjectRepositoryImpl(connection);
        Comparator<SelectionObject> comparator = Comparator.comparing(SelectionObject::getPrice);
        List<SelectionObject> result = repository.getSelectionPriceLess(0, comparator);
        for (Integer i = 0; i < result.size(); i++){
            System.out.println(result.get(i));
        }
        boolean check = (5 == result.size());
        assertTrue(check);
        connection.close();
    } 
    
}
