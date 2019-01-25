package ru.javastudy.springMVC.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;
import ru.javastudy.springMVC.JDBC.ConnectionDB;
import ru.javastudy.springMVC.JDBC.ResultSetOut;
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
    то попадем вот сюда*/
    @RequestMapping(value = "/check-user")
    public ModelAndView checkUser(@ModelAttribute("userJSP") User user) {
        Connection connection = ConnectionDB.SetConnDB();
        SelectUser.Upd(connection,user);
        ResultSet resultSet = SelectUser.SelUser(connection);
        ArrayList ArrayListResultSet =ResultSetOut.PrepareArrayList(resultSet);
        resultSet = SelectUser.SelUser(connection);
        ArrayList<HashMap<String,String>> listOfMap = ResultSetOut.PrepareArrayListHashMap(resultSet);

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
       Connection connection = ConnectionDB.SetConnDB();
       ResultSet resultSet = SelectUser.SelUser(connection);
       ArrayList ArrayListResultSet =ResultSetOut.PrepareArrayList(resultSet);
       resultSet = SelectUser.SelUser(connection);
       ArrayList<HashMap<String,String>> listOfMap = ResultSetOut.PrepareArrayListHashMap(resultSet);

       ModelAndView modelAndView = new ModelAndView();
       //имя представления, куда нужно будет перейти
       modelAndView.setViewName("thirdPage");
       //записываем в атрибут userJSP (используется на странице *.jsp объект user
       modelAndView.addObject("resSet", ArrayListResultSet);
       modelAndView.addObject("listOfMap", listOfMap);

       return modelAndView; //после уйдем на представление, указанное чуть выше, если оно будет найдено.
   }

}
