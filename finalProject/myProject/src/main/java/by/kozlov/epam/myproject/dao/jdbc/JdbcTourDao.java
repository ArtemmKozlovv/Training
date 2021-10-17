package by.kozlov.epam.myproject.dao.jdbc;

import by.kozlov.epam.myproject.dao.DaoException.DaoException;
import by.kozlov.epam.myproject.dao.TourDao;
import by.kozlov.epam.myproject.entity.Tour;
import by.kozlov.epam.myproject.entity.User;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class JdbcTourDao implements TourDao {
    private Connection connection;

    public void setConnection(Connection connection) {
        this.connection = connection;
    }

    private static final String SQL_INSERT_TOUR = "INSERT INTO `tour`(`name`,`country`,`cost`, `aboutTour`, `date`) " +
            "VALUES(?,?,?,?,?)";

    private static final String SQL_UPDATE_TOUR = "UPDATE `tour` SET `name`=?,`country`=?,`cost`=?,`aboutTour`=?,`date`=? WHERE `id` = ?";

    private static final String SQL_DELETE_TOUR = "DELETE FROM `tour` WHERE `id` = ?";



    @Override
    public Tour findEntityById(Long id) throws DaoException {
        String sql = "SELECT `name`, `country`, `cost`, `aboutTour`, `date` FROM `tour` WHERE `id` = " + id;
        Statement statement = null;
        ResultSet resultSet = null;
        try {
            statement = connection.createStatement();
            resultSet = statement.executeQuery(sql);
            Tour tour = null;
            if (resultSet.next()) {
                tour = new Tour();
                tour.setId(id);
                tour.setName(resultSet.getString("name"));
                tour.setCountry(resultSet.getString("country"));
                tour.setCost(resultSet.getLong("cost"));
                tour.setAboutTour(resultSet.getString("aboutTour"));
                tour.setDateSql(resultSet.getDate("date"));
            }
            return tour;
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
            statement = connection.prepareStatement(SQL_DELETE_TOUR);
            statement.setLong(1, id);
            statement.executeUpdate();
        }catch (SQLException e){
            throw new DaoException();
        } finally {
            try{ statement.close(); } catch (Exception e){}
        }
    }

    @Override
    public Long create(Tour tour) throws DaoException {
        PreparedStatement statement = null;
        ResultSet resultSet = null;
        try {
            statement = connection.prepareStatement(SQL_INSERT_TOUR, Statement.RETURN_GENERATED_KEYS);
            statement.setString(1, tour.getName());
            statement.setString(2, tour.getCountry());
            statement.setLong(3, tour.getCost());
            statement.setString(4, tour.getAboutTour());
            statement.setDate(5, tour.getDateSql());
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
    public void update(Tour tour) throws DaoException {
        PreparedStatement statement = null;
        try {
            statement = connection.prepareStatement(SQL_UPDATE_TOUR);
            statement.setString(1, tour.getName());
            statement.setString(2, tour.getCountry());
            statement.setLong(3, tour.getCost());
            statement.setString(4, tour.getAboutTour());
            statement.setDate(5, tour.getDateSql());
            statement.setLong(6, tour.getId());
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
    public List<Tour> findAllTours() throws DaoException {
        String sql = "SELECT `id`, `name`, `country`, `cost`, `aboutTour`, `date` FROM `tour`";
        Statement st = null;
        ResultSet resultSet = null;
        try{
            st = connection.createStatement();
            resultSet = st.executeQuery(sql);
            List<Tour> tours = new ArrayList<>();
            while(resultSet.next()){
                Tour tour = new Tour();
                tour.setId(resultSet.getLong("id"));
                tour.setName(resultSet.getString("name"));
                tour.setCountry(resultSet.getString("country"));
                tour.setCost(resultSet.getLong("cost"));
                tour.setAboutTour(resultSet.getString("aboutTour"));
                tour.setDateSql(resultSet.getDate("date"));
                tours.add(tour);
            }
            return tours;
        }catch (SQLException e){
            throw new DaoException(e);
        }finally {
            try{ resultSet.close(); } catch (Exception e){}
            try{ st.close(); } catch (Exception e){}
        }
    }

    @Override
    public Long findTourIdByName(String name) throws DaoException {
        String sql = "SELECT id FROM tour WHERE name=?";;
        Long a = null;
        try(PreparedStatement ps = connection.prepareStatement(sql)) {
            ps.setString(1,name);
            ResultSet resultSet = ps.executeQuery();
            if (resultSet.next()) {
                a = resultSet.getLong("id");
            }
        } catch (SQLException e){
            throw new DaoException();
        }
        return a;
    }

}
