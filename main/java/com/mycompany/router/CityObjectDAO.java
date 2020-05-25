package com.mycompany.router;

import javax.management.relation.Role;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class CityObjectDAO implements DAO<CityObject, String> {
    
    private final Connection connection;

    public CityObjectDAO(final Connection connection) {
        this.connection = connection;
    }
    
    @Override
    public boolean create(CityObject cityObject) {
        boolean result = false;

        try (PreparedStatement statement = connection.prepareStatement(SQLCityObject.INSERT.QUERY)) {
            statement.setString(1, cityObject.getName());
            statement.setDouble(2, cityObject.getLat());
            statement.setDouble(3, cityObject.getLon());
            statement.setString(4, cityObject.getObj_type());
            statement.setString(5, cityObject.getDescrirption());
            statement.setInt(6, cityObject.getPrice());
            result = statement.executeQuery().next();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return result;
    }
    
    @Override
    public CityObject read(String name) {
        final CityObject result = new CityObject();
        result.setId(-1);

        try (PreparedStatement statement = connection.prepareStatement(SQLCityObject.GET.QUERY)) {
            statement.setString(1, name);
            final ResultSet rs = statement.executeQuery();
            if (rs.next()) {
                result.setId(Integer.parseInt(rs.getString("id")));
                result.setName(name);
                result.setLat(Double.parseDouble(rs.getString("lat")));
                result.setLon(Double.parseDouble(rs.getString("lon")));
                result.setObj_type(rs.getString("obj_type"));
                result.setDescrirption(rs.getString("description"));
                result.setPrice(Integer.parseInt(rs.getString("price")));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return result;
    }
    
    @Override
    public boolean update(CityObject cityObject) {
        boolean result = false;

        try (PreparedStatement statement = connection.prepareStatement(SQLCityObject.UPDATE.QUERY)) {
            statement.setString(1, cityObject.getDescrirption());
            statement.setInt(2, cityObject.getId());
            result = statement.executeQuery().next();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return result;
    }
    
    @Override
    public boolean delete(CityObject cityObject) {
        boolean result = false;

        try (PreparedStatement statement = connection.prepareStatement(SQLCityObject.DELETE.QUERY)) {
            statement.setInt(1, cityObject.getId());
            statement.setString(2, cityObject.getName());
            statement.setDouble(3, cityObject.getLat());
            statement.setDouble(4, cityObject.getLon());
            statement.setString(5, cityObject.getObj_type());
            statement.setString(6, cityObject.getDescrirption());
            statement.setInt(7, cityObject.getPrice());
            result = statement.executeQuery().next();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return result;
    }
    
    enum SQLCityObject {
        GET("SELECT id, name, lat, lon, obj_type, description, price FROM city_objects WHERE name = (?)"),
        INSERT("INSERT INTO city_objects (id, name, lat, lon, obj_type, description, price) VALUES (DEFAULT, (?), (?), (?), (?), (?), (?)) RETURNING id"),
        DELETE("DELETE FROM city_objects WHERE id = (?) AND name = (?) AND lat = (?) AND lon = (?) AND obj_type = (?) AND description = (?) AND price = (?) RETURNING id"),
        UPDATE("UPDATE city_objects SET description = (?) WHERE id = (?) RETURNING id");

        String QUERY;

        SQLCityObject(String QUERY) {
            this.QUERY = QUERY;
        }
    } 
}
