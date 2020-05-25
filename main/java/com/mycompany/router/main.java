/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.router;

import java.sql.*;
import java.util.Comparator;
import java.util.List;
/**
 *
 * @author Михаил
 */
public class main {
    public static void main(String[] args) throws SQLException{
//        DAO<User, String> dao;                      
//        final Connection connection = DriverManager.getConnection("jdbc:postgresql://localhost:5432/gid", "postgres", "3114");       
//        dao = new UserDAO(connection);        
//        final User user = dao.read("Misha");
//        System.out.println(user.toString());        
//        final User user1 = new User(0, "test", "321", new User.Role(2, "user"));
//        final boolean result = dao.create(user1);    
//        dao.delete(dao.read("test"));


//        DAO<CityObject, String> cityDao;
//        final Connection connection = DriverManager.getConnection("jdbc:postgresql://localhost:5432/gid", "postgres", "3114");  
//        cityDao = new CityObjectDAO(connection);
////        final CityObject obj1 = new CityObject(0, "Test", 1.1, 1.1, "Test", "test", 0);
////        final boolean result = cityDao.create(obj1); 
//        
//
//        final CityObject obj1 = cityDao.read("Monument of Devotion");
//        System.out.println(obj1.toString());  
////        cityDao.delete(cityDao.read("Test"));
//        connection.close();

        SelectionObjectRepository<SelectionObject> repository;
        Connection connection = DriverManager.getConnection("jdbc:postgresql://localhost:5432/gid", "postgres", "3114");
        repository = new SelectionObjectRepositoryImpl(connection);
        Comparator<SelectionObject> comparator = Comparator.comparing(SelectionObject::getPrice);
        List<SelectionObject> result = repository.getSelectionPriceLess(0, comparator);
        for (int i = 0; i < result.size(); i++){
            System.out.println(result.get(i));
        }
        connection.close();
    }
}
