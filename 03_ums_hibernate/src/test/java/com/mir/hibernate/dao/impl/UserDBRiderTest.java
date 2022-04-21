package com.mir.hibernate.dao.impl;

import com.github.database.rider.core.DBUnitRule;
import com.github.database.rider.core.api.dataset.DataSet;
import com.github.database.rider.core.api.dataset.ExpectedDataSet;
import com.github.database.rider.core.api.dataset.SeedStrategy;
import com.mir.hibernate.dao.GenericHibernateDao;
import com.mir.hibernate.entity.Role;
import com.mir.hibernate.entity.User;
import org.hibernate.engine.jdbc.connections.spi.ConnectionProvider;
import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.JUnit4;

import java.sql.Date;
import java.sql.SQLException;
import java.util.List;

import static org.junit.Assert.assertEquals;

@RunWith(JUnit4.class)
@DataSet(strategy = SeedStrategy.INSERT, cleanBefore = true, cleanAfter = true, executeScriptsBefore = {
        "testScript.sql"}
)
public class UserDBRiderTest {
    HibernateUserDao userDao = new HibernateUserDao();
    GenericHibernateDao dao = new HibernateRoleDao();

    @Rule
    public DBUnitRule dbUnitRule =  DBUnitRule.instance(dao.getSessionFactory()
            .getSessionFactoryOptions().getServiceRegistry()
            .getService(ConnectionProvider.class).getConnection());

    public UserDBRiderTest() throws SQLException {
    }

    @Test
    @ExpectedDataSet(value = "userDataset/datasetUser.xml")
    public void testFindAllUsers() {
        List<User> userList = userDao.findAll();
        assertEquals(2, userList.size());
    }

    @Test
    @ExpectedDataSet(value = "userDataset/datasetRemoveUser.xml")
    public void testRemoveUser() {
        User user = new User();
        user.setId(2l);
        userDao.remove(user);
    }

    @Test
    @ExpectedDataSet(value = "userDataset/datasetCreateUser.xml")
    public void testCreateUser() {
        User user = new User("login3", "pass3",
                "email3", "fname3",
                "lname3", new Date(2000-10-10),
                new Role(1l, "Admin"));
        userDao.create(user);
    }


    @Test
    @ExpectedDataSet(value = "userDataset/datasetFindUserByLogin.xml")
    public void testFindUserByLogin() {
        userDao.findByLogin("login1");
    }

    @Test
    @ExpectedDataSet(value = "userDataset/datasetFindUserByLogin.xml")
    public void testFindUserByEmail() {
        userDao.findByEmail("email1");
    }
}
