/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.router;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.SortedSet;
import java.util.TreeSet;

public class SelectionObjectRepositoryImpl implements SelectionObjectRepository<SelectionObject> {

    private final Connection connection;

    public SelectionObjectRepositoryImpl(final Connection connection) throws SQLException {
        this.connection = connection;
    }

    @Override
    public List<SelectionObject> getSelectionPriceLess(final Integer threshold, 
                                            final Comparator<SelectionObject> comp) {

        final ArrayList<SelectionObject> statistics = new ArrayList();

        try (PreparedStatement statement = connection.prepareStatement(StatSQL.GET_STAT.QUERY)) {

            execute(threshold, statement, statistics);

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return statistics;
    }

    private void execute( final Integer threshold,
                          final PreparedStatement statement,
                          final ArrayList<SelectionObject> result) throws SQLException {

        statement.setInt(1, threshold);

        final ResultSet set = statement.executeQuery();

        while (set.next()) {
            final String objName = set.getString(1);
            final Integer price = set.getInt(2);
            result.add(new SelectionObject(objName, price));
//            System.out.println(result.size());
        }
    }

    enum StatSQL {

        GET_STAT("SELECT name, price from city_objects WHERE price <= (?) ORDER BY lat, lon ;");

        String QUERY;

        StatSQL(String QUERY) {
            this.QUERY = QUERY;
        }
    }
}
