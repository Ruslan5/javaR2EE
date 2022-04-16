package com.mir.servlets.dao.impl;

import com.mir.servlets.dao.ConfigManager;
import com.mir.servlets.dao.GenericJdbcDao;
import com.mir.servlets.dao.RoleDao;
import com.mir.servlets.entity.Role;
import org.apache.log4j.Logger;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import static com.mir.servlets.dao.queryUtil.QueryUtil.SQL_CREATE_ROLE;
import static com.mir.servlets.dao.queryUtil.QueryUtil.SQL_FIND_ALL_ROLES;
import static com.mir.servlets.dao.queryUtil.QueryUtil.SQL_FIND_BY_ID_ROLE;
import static com.mir.servlets.dao.queryUtil.QueryUtil.SQL_REMOVE_ROLE;
import static com.mir.servlets.dao.queryUtil.QueryUtil.SQL_UPDATE_ROLE;
/**
 * class JdbcRoleDao.
 *
 * @author R.M.
 * @see GenericJdbcDao
 * @see RoleDao
 * @see Role
 * @since 2022
 */
public class JdbcRoleDao extends GenericJdbcDao<Role> implements RoleDao<Role> {
    private static Logger logger = Logger.getLogger(JdbcRoleDao.class);

    public JdbcRoleDao(ConfigManager configManager) {
        super(configManager);
    }

    @Override
    public List<Role> findAll() {
        logger.trace("start method findAll Roles");
        List<Role> roleList = new ArrayList<>();
        try (Connection connection = getConnection()) {
            ResultSet resultSet = null;
            try (Statement statement = connection.createStatement()) {
                resultSet = statement.executeQuery(SQL_FIND_ALL_ROLES);
                while (resultSet.next()) {
                    roleList.add(rsToRole(resultSet));
                }
                connection.commit();
            } catch (SQLException e) {
                logger.error("error from findAll: {} ", e);
                throw new RuntimeException("error from findAll: {} ", e);
            } finally {
                closeResultSet(resultSet);
            }
        } catch (SQLException e) {
            logger.error("error: {} ", e);
            throw new RuntimeException("error: {} ", e);
        }
        return roleList;
    }

    @Override
    public Role findById(Long id) {
        logger.trace("start method findById from Role");
        Role role = null;
        try (Connection connection = getConnection()) {
            ResultSet resultSet = null;
            try (PreparedStatement preparedStatement = connection.prepareStatement(SQL_FIND_BY_ID_ROLE)) {
                preparedStatement.setLong(1, id);
                resultSet = preparedStatement.executeQuery();

                while (resultSet.next()) {
                    role = rsToRole(resultSet);
                }
                connection.commit();
            } catch (SQLException e) {
                logger.debug("error from findAll: {} ", e);
            } finally {
                closeResultSet(resultSet);
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return role;
    }

    @Override
    public void create(Role role) {
        logger.trace("start method create Role");
        try (Connection connection = getConnection()) {
            try (PreparedStatement preparedStatement = connection.prepareStatement(SQL_CREATE_ROLE)) {
                preparedStatement.setLong(1, role.getId());
                preparedStatement.setString(2, role.getName());
                preparedStatement.executeUpdate();
                connection.commit();
            }
        } catch (SQLException e) {
            logger.debug("error from create: {} ", e);
            throw new RuntimeException(e);
        }
    }

    @Override
    public void remove(Role entity) {
        logger.trace("start method remove Role");
        try (Connection connection = getConnection()) {
            try (PreparedStatement preparedStatement = connection.prepareStatement(SQL_REMOVE_ROLE)) {
                preparedStatement.setLong(1, entity.getId());
                preparedStatement.executeUpdate();
            }
        } catch (SQLException e) {
            logger.debug("error from remove: {} ", e);
            throw new RuntimeException(e);
        }
    }

    @Override
    public void update(Role role) {
        logger.trace("start method update Role");
        try (Connection connection = getConnection()) {
            try (PreparedStatement preparedStatement = connection.prepareStatement(SQL_UPDATE_ROLE)) {
                preparedStatement.setString(1, role.getName());
                preparedStatement.setLong(2, role.getId());
                preparedStatement.executeUpdate();
                connection.commit();
            }
        } catch (SQLException e) {
            logger.debug("error from update:{} ", e);
            throw new RuntimeException(e);
        }
    }

    private Role rsToRole(ResultSet resultSet) throws SQLException {
        Long roleId = resultSet.getLong("ID");
        String roleName = resultSet.getString("Name");

        Role role = new Role();
        role.setId(roleId);
        role.setName(roleName);
        return role;
    }
}
