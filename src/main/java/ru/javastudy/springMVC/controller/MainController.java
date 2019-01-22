package ru.javastudy.springMVC.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;
import ru.javastudy.springMVC.JDBC.SelectUser;
import ru.javastudy.springMVC.model.User;

import java.sql.*;
import java.util.ArrayList;
import java.util.HashMap;


@Controller
public class MainController {

    /*First method on start application*/
    /*Попадаем сюда на старте приложения (см. параметры аннтоции и настройки пути после деплоя) */
    @RequestMapping(value = "/", method = RequestMethod.GET)
    public ModelAndView main() {
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.addObject("userJSP", new User());
        modelAndView.setViewName("index");
        return modelAndView;
    }

    /*как только на index.jsp подтвердится форма
    <spring:form method="post"  modelAttribute="userJSP" action="check-user">,
    то попадем вот сюда
     */
    @RequestMapping(value = "/check-user")
    public ModelAndView checkUser(@ModelAttribute("userJSP") User user) {

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
        String SaveName=user.getName();
        String SavePassword=user.getPassword();
        try {
            statement.executeUpdate("insert into test (Name,Password) values ("+SaveName+","+SavePassword+")");
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

        ArrayList ArrayListResultSet= new ArrayList();
        //Map MapResultSet = new HashMap();
        ArrayList<HashMap<String,String>> listOfMap = new ArrayList<HashMap<String,String>>();
        HashMap<String,String> map;
        try {
            while (resultSet.next()) {
                ArrayListResultSet.add(resultSet.getString("Name"));
                map = new HashMap<String, String>();
                map.put("Code", resultSet.getString("Code"));
                map.put("Name", resultSet.getString("Name"));
                listOfMap.add(map);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        for (int i=0;i<ArrayListResultSet.size();i++)
            System.out.println("ArrayListResultSet:" + ArrayListResultSet.get(i));

        for (int i=0;i<listOfMap.size();i++)
            System.out.println("listOfMap:" + listOfMap.get(i));

        ModelAndView modelAndView = new ModelAndView();

        //имя представления, куда нужно будет перейти
        modelAndView.setViewName("secondPage");

        //записываем в атрибут userJSP (используется на странице *.jsp объект user
        modelAndView.addObject("userJSP", user);
        modelAndView.addObject("resSet", ArrayListResultSet);
        modelAndView.addObject("listOfMap", listOfMap);

        return modelAndView; //после уйдем на представление, указанное чуть выше, если оно будет найдено.
    }


   /**************************************************************************/

   @RequestMapping(value = "/view-user")
   public ModelAndView viewUser() {
       ResultSet resultSet = SelectUser.SelUser();
       ArrayList ArrayListResultSet= new ArrayList();
       //Map MapResultSet = new HashMap();
       ArrayList<HashMap<String,String>> listOfMap = new ArrayList<HashMap<String,String>>();
       HashMap<String,String> map;
       try {
           while (resultSet.next()) {
               ArrayListResultSet.add(resultSet.getString("Name"));
               map = new HashMap<String, String>();
               map.put("Code", resultSet.getString("Code"));
               map.put("Name", resultSet.getString("Name"));
               listOfMap.add(map);
           }
       } catch (SQLException e) {
           e.printStackTrace();
       }

       for (int i=0;i<ArrayListResultSet.size();i++)
           System.out.println("ArrayListResultSet:" + ArrayListResultSet.get(i));

       for (int i=0;i<listOfMap.size();i++)
           System.out.println("listOfMap:" + listOfMap.get(i));

       ModelAndView modelAndView = new ModelAndView();

       //имя представления, куда нужно будет перейти
       modelAndView.setViewName("thirdPage");

       //записываем в атрибут userJSP (используется на странице *.jsp объект user
       modelAndView.addObject("resSet", ArrayListResultSet);
       modelAndView.addObject("listOfMap", listOfMap);

       return modelAndView; //после уйдем на представление, указанное чуть выше, если оно будет найдено.
       //проверка Git
   }

}
