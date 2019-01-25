package ru.javastudy.springMVC.JDBC;

import ru.javastudy.springMVC.model.User;

import java.sql.*;

public class SelectUser {
    public static ResultSet SelUser(Connection connection) {

        Statement statement = null;
        try {
            statement = connection.createStatement(ResultSet.TYPE_FORWARD_ONLY,ResultSet.CONCUR_UPDATABLE);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        ResultSet resultSet = null;
        try {
            resultSet = statement.executeQuery("select * from test");
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return resultSet;
    }
    public static void Upd(Connection connection, User user){
        Statement statement = null;
        try {
            statement = connection.createStatement(ResultSet.TYPE_FORWARD_ONLY,ResultSet.CONCUR_UPDATABLE);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        String SaveName=user.getName();
        String SavePassword=user.getPassword();
        try {
            statement.executeUpdate("insert into test (Name,Password) values ("+SaveName+","+SavePassword+")");
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
