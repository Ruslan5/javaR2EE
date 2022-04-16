package com.mir.servlets.dao.impl;

import com.mir.servlets.dao.ConfigManager;
import com.mir.servlets.dao.GenericJdbcDao;
import com.mir.servlets.dao.UserDao;
import com.mir.servlets.entity.Role;
import com.mir.servlets.entity.User;
import org.apache.log4j.Logger;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import static com.mir.servlets.dao.queryUtil.QueryUtil.SQL_CREATE_USER;
import static com.mir.servlets.dao.queryUtil.QueryUtil.SQL_FIND_ALL_USERS;
import static com.mir.servlets.dao.queryUtil.QueryUtil.SQL_FIND_USER_BY_EMAIL;
import static com.mir.servlets.dao.queryUtil.QueryUtil.SQL_FIND_USER_BY_LOGIN;
import static com.mir.servlets.dao.queryUtil.QueryUtil.SQL_REMOVE_USER;
import static com.mir.servlets.dao.queryUtil.QueryUtil.SQL_UPDATE_USER;

/**
 * class JdbcUserDao.
 *
 * @author R.Mirzoiev
 * @see GenericJdbcDao
 * @see UserDao
 * @see User
 * @since 31.01.2022
 */
public class JdbcUserDao extends GenericJdbcDao<User> implements UserDao<User> {
    private static Logger logger = Logger.getLogger(JdbcUserDao.class);

    public JdbcUserDao(ConfigManager configManager) {
        super(configManager);
    }

    @Override
    public void create(User user) {
        logger.trace("start method create User");
        try (Connection connection = getConnection()) {
            try (PreparedStatement preparedStatement = connection.prepareStatement(SQL_CREATE_USER)) {
                preparedStatement.setString(1, user.getLogin());
                preparedStatement.setString(2, user.getPassword());
                preparedStatement.setString(3, user.getEmail());
                preparedStatement.setString(4, user.getFirstName());
                preparedStatement.setString(5, user.getLastName());
                preparedStatement.setDate(6, user.getBirthday());
                preparedStatement.setLong(7, user.getRole().getId());
                preparedStatement.executeUpdate();
                logger.trace("finish success method create User" + user);

            }
        } catch (SQLException e) {
            logger.debug("error from create: {}", e);
            throw new RuntimeException(e);
        }
    }

    @Override
    public void update(User user) {
        logger.trace("start method update User");
        try (Connection connection = getConnection()) {
            try (PreparedStatement preparedStatement = connection.prepareStatement(SQL_UPDATE_USER)) {
                preparedStatement.setString(1, user.getLogin());
                preparedStatement.setString(2, user.getPassword());
                preparedStatement.setString(3, user.getEmail());
                preparedStatement.setString(4, user.getFirstName());
                preparedStatement.setString(5, user.getLastName());
                preparedStatement.setDate(6, user.getBirthday());
                preparedStatement.setLong(7, user.getRole().getId());
                preparedStatement.setLong(8, user.getId());

                preparedStatement.executeUpdate();
            }
        } catch (SQLException e) {
            logger.debug("error from update: {}", e);
            throw new RuntimeException(e);
        }
    }

    @Override
    public void remove(User user) {
        logger.trace("start method remove User");
        try (Connection connection = getConnection()) {
            try (PreparedStatement preparedStatement = connection.prepareStatement(SQL_REMOVE_USER)) {
                preparedStatement.setLong(1, user.getId());
                preparedStatement.executeUpdate();
            }
        } catch (SQLException e) {
            logger.debug("error from remove: {}", e);
            throw new RuntimeException(e);
        }
    }

    @Override
    public List<User> findAll() {
        logger.trace("start method findAll User");
        List<User> userList = new ArrayList<>();
        try (Connection connection = getConnection()) {
            ResultSet resultSet = null;
            try (Statement statement = connection.createStatement()) {
                resultSet = statement.executeQuery(SQL_FIND_ALL_USERS);
                while (resultSet.next()) {
                    userList.add(rsToUser(resultSet));
                }
            }finally {
                closeResultSet(resultSet);
            }

        } catch (SQLException e) {
            logger.debug("error from findAll: {}", e);
            throw new RuntimeException(e);
        }
        return userList;
    }

    @Override
    public User findByLogin(String login) {
        logger.trace("start method findByLogin User");
        User user = null;
        try (Connection connection = getConnection()) {
            ResultSet resultSet = null;
            try (PreparedStatement preparedStatement = connection.prepareStatement(SQL_FIND_USER_BY_LOGIN)) {
                preparedStatement.setString(1, login);
                resultSet = preparedStatement.executeQuery();
                while (resultSet.next()) {
                    user = rsToUser(resultSet);
                }
                connection.commit();
            } catch (SQLException e) {
                logger.debug("SQLException {}: ", e);
                throw new RuntimeException(e);
            } finally {
                closeResultSet(resultSet);
            }
        } catch (SQLException e) {
            logger.debug("SQLException {}: ", e);
            throw new RuntimeException(e);

        }
        return user;
    }

    @Override
    public User findByEmail(String email) {
        logger.trace("start method findByEmail User");
        User user = null;
        try (Connection connection = getConnection()) {
            ResultSet resultSet = null;
            try (PreparedStatement preparedStatement = connection.prepareStatement(SQL_FIND_USER_BY_EMAIL)) {
                preparedStatement.setString(1, email);
                resultSet = preparedStatement.executeQuery();
                while (resultSet.next()) {
                    user = rsToUser(resultSet);
                }
                connection.commit();
            } catch (SQLException e) {
                logger.debug("SQLException {}: ", e);
                throw new RuntimeException(e);
            } finally {
                closeResultSet(resultSet);
            }
        } catch (SQLException e) {
            logger.debug("SQLException {}: ", e);
            throw new RuntimeException(e);

        }
        return user;
    }

    private User rsToUser(ResultSet resultSet) throws SQLException {
        Long userId = resultSet.getLong("ID");
        String userLogin = resultSet.getString("Login");
        String userPassword = resultSet.getString("password");
        String userEmail = resultSet.getString("email");
        String userFirstname = resultSet.getString("firstname");
        String userLastname = resultSet.getString("lastname");
        Date userBirthday = resultSet.getDate("birthday");

        Long userRoleId = resultSet.getLong("ROLE_ID");
        String userRoleName = resultSet.getString("name");

        User user = new User();
        user.setId(userId);
        user.setLogin(userLogin);
        user.setPassword(userPassword);
        user.setEmail(userEmail);
        user.setFirstName(userFirstname);
        user.setLastName(userLastname);
        user.setBirthday(userBirthday);
        Role role = new Role();


        user.setRole(new Role(userRoleId, userRoleName));

        return user;
    }
}
