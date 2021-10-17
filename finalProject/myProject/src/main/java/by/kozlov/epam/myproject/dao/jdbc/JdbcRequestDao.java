package by.kozlov.epam.myproject.dao.jdbc;

import by.kozlov.epam.myproject.dao.DaoException.DaoException;
import by.kozlov.epam.myproject.dao.RequestDao;
import by.kozlov.epam.myproject.entity.Request;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class JdbcRequestDao implements RequestDao {
    private Connection connection;

    public void setConnection(Connection connection) {
        this.connection = connection;
    }

    private static final String SQL_INSERT_REQUEST = "INSERT INTO `request`(`login`,`phoneNumber`,`name`) " +
            "VALUES(?,?,?)";

    private static final String SQL_UPDATE_REQUEST = "UPDATE `request` SET `login`=?,`phoneNumber`=?,`name`=? WHERE `id` = ?";

    private static final String SQL_DELETE_REQUEST = "DELETE FROM `request` WHERE `id` = ?";

    @Override
    public Request findEntityById(Long id) throws DaoException {
        String sql = "SELECT `login`, `phoneNumber`, `name` FROM `request` WHERE `id` = " + id;
        Statement statement = null;
        ResultSet resultSet = null;
        try {
            statement = connection.createStatement();
            resultSet = statement.executeQuery(sql);
            Request request = null;
            if (resultSet.next()) {
                request = new Request();
                request.setId(id);
                request.setLogin(resultSet.getString("login"));
                request.setNumber(resultSet.getString("phoneNumber"));
                request.setName(resultSet.getString("name"));
            }
            return request;
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
            statement = connection.prepareStatement(SQL_DELETE_REQUEST);
            statement.setLong(1, id);
            statement.executeUpdate();
        }catch (SQLException e){
            throw new DaoException();
        } finally {
            try{ statement.close(); } catch (Exception e){}
        }
    }

    @Override
    public Long create(Request request) throws DaoException {
        PreparedStatement statement = null;
        ResultSet resultSet = null;
        try {
            statement = connection.prepareStatement(SQL_INSERT_REQUEST, Statement.RETURN_GENERATED_KEYS);
            statement.setString(1, request.getLogin());
            statement.setString(2, request.getNumber());
            statement.setString(3, request.getName());
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
    public void update(Request request) throws DaoException {
        PreparedStatement statement = null;
        try {
            statement = connection.prepareStatement(SQL_UPDATE_REQUEST);
            statement.setString(1, request.getLogin());
            statement.setString(2, request.getNumber());
            statement.setString(3, request.getName());
            statement.setLong(4, request.getId());
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
    public List<Request> findAllRequests() throws DaoException {
        String sql = "SELECT `id`, `login`, `phoneNumber`, `name` FROM `request`";
        Statement st = null;
        ResultSet resultSet = null;
        try{
            st = connection.createStatement();
            resultSet = st.executeQuery(sql);
            List<Request> requests = new ArrayList<>();
            while(resultSet.next()){
                Request request = new Request();
                request.setId(resultSet.getLong("id"));
                request.setLogin(resultSet.getString("login"));
                request.setNumber(resultSet.getString("phoneNumber"));
                request.setName(resultSet.getString("name"));
                requests.add(request);
            }
            return requests;
        }catch (SQLException e){
            throw new DaoException(e);
        }finally {
            try{ resultSet.close(); } catch (Exception e){}
            try{ st.close(); } catch (Exception e){}
        }
    }
}
