package by.kozlov.epam.myproject;

import by.kozlov.epam.myproject.di.ServiceCreator;
import by.kozlov.epam.myproject.di.exception.ServiceCreatorException;
import by.kozlov.epam.myproject.entity.User;
import by.kozlov.epam.myproject.pool.ConnectionPool;
import by.kozlov.epam.myproject.service.UserService;
import by.kozlov.epam.myproject.service.exception.ServiceException;

import java.sql.*;
import java.util.List;

public class Test {
    public static void main(String[] args) throws Exception {
        ConnectionPool pool = ConnectionPool.getInstance();
        pool.init(
                "com.mysql.cj.jdbc.Driver",
                "jdbc:mysql://localhost:3306/travel_agency_db",
                "root",
                "root",
                5,
                100,
                0
        );
        Connection c = pool.getConnection();
//        Connection c = DriverManager.getConnection("jdbc:mysql://localhost:3306/travel_agency_db", "root", "root");
        System.out.println(c);
        try(ServiceCreator sc = new ServiceCreator()){
            UserService userService = sc.getUserService();
            List<User> users = userService.findAll();
            for(User user : users){
                System.out.println(user);
            }
            System.out.println("OK");
        } catch (ServiceException | ServiceCreatorException e){
            System.out.println("ERROR");
        }
    }
}
