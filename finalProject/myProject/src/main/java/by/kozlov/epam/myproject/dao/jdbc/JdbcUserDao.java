package by.kozlov.epam.myproject.dao.jdbc;

import by.kozlov.epam.myproject.dao.DaoException.DaoException;
import by.kozlov.epam.myproject.dao.UserDao;
import by.kozlov.epam.myproject.entity.Role;
import by.kozlov.epam.myproject.entity.User;


import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class JdbcUserDao implements UserDao {
    private Connection connection;

    public void setConnection(Connection connection) {
        this.connection = connection;
    }

    private static final String SQL_SELECT_ALL_USERS = "SELECT id_user,login,password,role,email,name,surname,discount,balance " +
            "FROM users";

    private static final String SQL_SELECT_ALL_USERS_WITH_ORDER_COUNT = "SELECT users.id_user,users.login,users.password,users.role," +
            "users.email,users.name,users.surname,users.discount,users.balance,COUNT(DISTINCT orders.id_order) AS orders FROM users " +
            "LEFT JOIN orders USING (id_user) GROUP BY users.id_user;";

    private static final String SQL_SELECT_USER_BY_NAME = "SELECT id_user,login,password,role,email,name,surname,discount,balance " +
            "FROM users WHERE login=?";

    private static final String SQL_SELECT_USER_BY_ID = "SELECT id_user,login,password,role,email,name,surname,discount,balance " +
            "FROM users WHERE id_user=?";

    private static final String SQL_SELECT_USER_PASSWORD_BY_ID = "SELECT password FROM users WHERE id_user=?";

    private static final String SQL_UPDATE_USER_PASSWORD_BY_ID = "UPDATE users SET password=? WHERE id_user=?";

    private static final String SQL_SELECT_MONEY_BY_USER_ID = "SELECT balance FROM users WHERE id_user=?";

    private static final String SQL_INSERT_USER = "INSERT INTO `user`(`login`,`password`,`name`, `surname`, `role`, `count_of_tours`) " +
            "VALUES(?,?,?,?,?,?)";

    private static final String SQL_UPDATE_USER = "UPDATE `user` SET `login`=?,`password`=?,`name`=?,`surname`=?,`role`=?,`count_of_tours`=? WHERE `id` = ?";

    private static final String SQL_UPDATE_USER_ID_TOUR = "UPDATE `user` SET `id_tour`=? WHERE `id` = ?";

    private static final String SQL_UPDATE_USER_NAME_AND_SURNAME = "UPDATE `user` SET `name`=?,`surname`=? WHERE `id` = ?";

    private static final String SQL_UPDATE_USER_BALANCE = "UPDATE users SET balance=? WHERE id_user=?";

    private static final String SQL_UPDATE_USER_BALANCE_ADDITION = "UPDATE users SET balance=balance + ? WHERE id_user=?";

    private static final String SQL_DELETE_USER = "DELETE FROM `user` WHERE `id` = ?";

    private static final String PARAM_ID_USER = "id";
    private static final String PARAM_LOGIN = "login";
    private static final String PARAM_PASSWORD = "password";
    private static final String PARAM_ROLE = "role";
    private static final String PARAM_NAME = "name";
    private static final String PARAM_SURNAME = "surname";
    private static final String PARAM_COUNT_OF_TOURS = "count_of_tours";
    private static final String PARAM_ID_TOUR = "id_tour";

    @Override
    public User findEntityById(Long id) throws DaoException {
        String sql = "SELECT `name`, `surname`, `login`, `password`, `role`, `count_of_tours`, `id_tour` FROM `user` WHERE `id` = " + id;
        Statement statement = null;
        ResultSet resultSet = null;
        try {
            statement = connection.createStatement();
            resultSet = statement.executeQuery(sql);
            User user = null;
            if (resultSet.next()) {
                user = new User();
                user.setId(id);
                user.setName(resultSet.getString("name"));
                user.setSurname(resultSet.getString("surname"));
                user.setLogin(resultSet.getString("login"));
                user.setPassword(resultSet.getString("password"));
                user.setRole(Role.values()[resultSet.getInt("role")]);
                user.setCount_of_tours(resultSet.getInt("count_of_tours"));
                user.setId_tour(resultSet.getInt("id_tour"));
            }
            return user;
        } catch(SQLException e){
            throw new DaoException(e);
        } finally {
            try { resultSet.close(); } catch (Exception e) {}
            try { statement.close(); } catch (Exception e) {}
        }
    }

    @Override
    public void delete(Long id) throws DaoException {
        PreparedStatement statement = null;
        try {
            statement = connection.prepareStatement(SQL_DELETE_USER);
            statement.setLong(1, id);
            statement.executeUpdate();
        }catch (SQLException e){
            throw new DaoException();
        } finally {
            try{ statement.close(); } catch (Exception e){}
        }
    }

    @Override
    public Long create(User user) throws DaoException {
        PreparedStatement statement = null;
        ResultSet resultSet = null;
        try {
            statement = connection.prepareStatement(SQL_INSERT_USER, Statement.RETURN_GENERATED_KEYS);
            statement.setString(1, user.getLogin());
            statement.setString(2, user.getPassword());
            statement.setString(3, user.getName());
            statement.setString(4, user.getSurname());
            statement.setInt(5, user.getRole().ordinal());
            statement.setInt(6, user.getCount_of_tours());
            statement.executeUpdate();
            resultSet = statement.getGeneratedKeys();
            resultSet.next();
            return resultSet.getLong(1);
        } catch (SQLException e) {
            throw new DaoException(e);
        } finally {
            try {
                statement.close();
            }catch (Exception e){}
        }
    }

    @Override
    public void update(User user) throws DaoException {
        PreparedStatement statement = null;
        try {
            statement = connection.prepareStatement(SQL_UPDATE_USER);
            statement.setString(1, user.getLogin());
            statement.setString(2, user.getPassword());
            statement.setString(3, user.getName());
            statement.setString(4, user.getSurname());
            statement.setInt(5, user.getRole().ordinal());
            statement.setInt(6, user.getCount_of_tours());
            statement.setLong(7, user.getId());
            statement.executeUpdate();
        } catch (SQLException e) {
            throw new DaoException(e);
        } finally {
            try {
                statement.close();
            }catch (Exception e){}
        }
    }

    @Override
    public void updateIdTour(User user) throws DaoException {
        PreparedStatement statement = null;
        try {
            statement = connection.prepareStatement(SQL_UPDATE_USER_ID_TOUR);
            statement.setInt(1, user.getId_tour());
            statement.setLong(2, user.getId());
            statement.executeUpdate();
        } catch (SQLException e) {
            throw new DaoException(e);
        } finally {
            try {
                statement.close();
            }catch (Exception e){}
        }
    }

    @Override
    public void updateNameAndSurname(User user) throws DaoException {
        PreparedStatement statement = null;
        try {
            statement = connection.prepareStatement(SQL_UPDATE_USER_NAME_AND_SURNAME);
            statement.setString(1, user.getName());
            statement.setString(2, user.getSurname());
            statement.setLong(3, user.getId());
            statement.executeUpdate();
        } catch (SQLException e) {
            throw new DaoException(e);
        } finally {
            try {
                statement.close();
            }catch (Exception e){}
        }
    }

    @Override
    public List<User> findAllUsers() throws DaoException {
        String sql = "SELECT `id`, `login`, `password`, `role`, `count_of_tours`, `id_tour` FROM `user`";
        Statement st = null;
        ResultSet resultSet = null;
        try{
            st = connection.createStatement();
            resultSet = st.executeQuery(sql);
            List<User> users = new ArrayList<>();
            while(resultSet.next()){
                User user = new User();
                user.setId(resultSet.getLong(PARAM_ID_USER));
                user.setLogin(resultSet.getString(PARAM_LOGIN));
                user.setPassword(resultSet.getString(PARAM_PASSWORD));
                user.setRole(Role.values()[resultSet.getInt(PARAM_ROLE)]);
                user.setCount_of_tours(resultSet.getInt(PARAM_COUNT_OF_TOURS));
                user.setId_tour(resultSet.getInt(PARAM_ID_TOUR));
                users.add(user);
            }
            return users;
        }catch (SQLException e){
            throw new DaoException(e);
        }finally {
            try{ resultSet.close(); } catch (Exception e){}
            try{ st.close(); } catch (Exception e){}
        }
    }

    @Override
    public User readByLoginAndPassword(String login, String password) throws DaoException {
        String sql = "SELECT `id`, `role` FROM `user` WHERE `login` = ? AND `password` = ?" ;
        PreparedStatement statement = null;
        ResultSet resultSet = null;
        try {
            statement = connection.prepareStatement(sql);
            statement.setString(1, login);
            statement.setString(2, password);
            resultSet = statement.executeQuery();
            User user = null;
            if (resultSet.next()) {
                user = new User();
                user.setId(resultSet.getLong("id"));
                user.setLogin(login);
                user.setPassword(password);
                user.setRole(Role.values()[resultSet.getInt("role")]);
            }
            return user;
        } catch(SQLException e){
            throw new DaoException(e);
        } finally {
            try { resultSet.close(); } catch (Exception e) {}
            try { statement.close(); } catch (Exception e) {}
        }
    }

    @Override
    public User findUserByName(String name) throws DaoException {
        return null;
    }

    @Override
    public String findPasswordByUserId(Long id) throws DaoException {
        return null;
    }

    @Override
    public boolean updatePasswordByUserId(Long id, String password) throws DaoException {
        return false;
    }

    private User createUser(ResultSet resultSet) throws DaoException {
        try {
            User user = new User();
            user.setId(resultSet.getLong(PARAM_ID_USER));
            user.setLogin(resultSet.getString(PARAM_LOGIN));
            user.setPassword(resultSet.getString(PARAM_PASSWORD));
            user.setRole(Role.values()[resultSet.getInt(PARAM_ROLE)]);
            user.setName(resultSet.getString(PARAM_NAME));
            user.setSurname(resultSet.getString(PARAM_SURNAME));
            user.setCount_of_tours(resultSet.getInt(PARAM_COUNT_OF_TOURS));
            return user;
        } catch (SQLException e) {
            throw new DaoException("SQL exception (request or table failed): " + e,e);
        }
    }
}
