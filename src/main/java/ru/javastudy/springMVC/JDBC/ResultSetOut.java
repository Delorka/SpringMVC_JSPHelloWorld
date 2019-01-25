package ru.javastudy.springMVC.JDBC;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;

public class ResultSetOut {
    public static ArrayList PrepareArrayList(ResultSet resultSet){
    ArrayList<String> ArrayListResultSet= new ArrayList();
        try {
        while (resultSet.next()) {
            ArrayListResultSet.add(resultSet.getString("Name"));
        }
    } catch (SQLException e) {
        e.printStackTrace();
    }
        //for (int i=0;i<ArrayListResultSet.size();i++)
        for ( String AL: ArrayListResultSet)
            //System.out.println("ArrayListResultSet:" + ArrayListResultSet.get(i));
            System.out.println("ArrayListResultSet:" + AL);
        return ArrayListResultSet;
    }

    public static ArrayList<HashMap<String,String>> PrepareArrayListHashMap(ResultSet resultSet){
        ArrayList<HashMap<String,String>> listOfMap = new ArrayList<HashMap<String,String>>();
        HashMap<String,String> map;
        try {
            while (resultSet.next()) {
                map = new HashMap<String, String>();
                map.put("Code", resultSet.getString("Code"));
                map.put("Name", resultSet.getString("Name"));
                listOfMap.add(map);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        for (int i=0;i<listOfMap.size();i++)
            System.out.println("listOfMap:" + listOfMap.get(i));
        return listOfMap;
    }
}
