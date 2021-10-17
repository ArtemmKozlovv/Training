package by.kozlov.epam.myproject.listener;

import by.kozlov.epam.myproject.pool.ConnectionPool;
import by.kozlov.epam.myproject.pool.Exception.ConnectionPoolException;

import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebListener;
import java.sql.SQLException;

@WebListener
public class ConnectionPoolInitializeListener implements ServletContextListener {

    public static ConnectionPool connectionPool;

    private static final int POOL_SIZE = 20;

    @Override
    public void contextInitialized(ServletContextEvent sce) {
        try {
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
        } catch (SQLException | ConnectionPoolException throwables) {
            throwables.printStackTrace();
        }
    }

    @Override
    public void contextDestroyed(ServletContextEvent sce) {
        ConnectionPool.getInstance().destroy();
    }
}
