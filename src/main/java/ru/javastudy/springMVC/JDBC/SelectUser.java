package ru.javastudy.springMVC.JDBC;

import ru.javastudy.springMVC.model.User;

import java.sql.*;

public class SelectUser {
    public static ResultSet SelUser() {

        try {
            Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
        Connection con = null;
        String username = "JDBCLogin";
        String password = "4321";
        String URL = "jdbc:sqlserver://localhost\\SQLEXPRESS;database=Sample1;";
        Connection connection = null;
        try {
            connection = DriverManager.getConnection(URL, username, password);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        Statement statement = null;
        try {
            statement = connection.createStatement();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        ResultSet resultSet = null;
        try {
            resultSet = statement.executeQuery("select * from test");
        } catch (SQLException e) {
            e.printStackTrace();
        }
        //while(resultSet.next()){ System.out.println(resultSet.getInt("Code")+" "+resultSet.getString("Name"));}

        return resultSet;
    }
}
