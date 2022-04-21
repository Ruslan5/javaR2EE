package com.nix.mirzoiev.docker.dao.impl;

import com.github.database.rider.core.DBUnitRule;
import com.github.database.rider.core.api.dataset.DataSet;
import com.github.database.rider.core.api.dataset.ExpectedDataSet;
import com.github.database.rider.core.api.dataset.SeedStrategy;
import com.mir.servlets.dao.impl.JdbcUserDao;
import com.mir.servlets.entity.Role;
import com.mir.servlets.entity.User;
import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.JUnit4;

import java.sql.Date;
import java.util.List;

import static org.junit.Assert.assertEquals;

@RunWith(JUnit4.class)
@DataSet(strategy = SeedStrategy.INSERT, cleanBefore = true, cleanAfter = true, executeScriptsBefore = {
        "testScript.sql"}
)
public class UserDBRiderTest extends JdbcRoleDaoTest {

    public JdbcUserDao dao = new JdbcUserDao(configManager);

    @Rule
    public DBUnitRule dbUnitRule = DBUnitRule.instance(dao.getConnection());

    @Test
    @ExpectedDataSet(value = "userDataset/datasetUser.xml")
    public void testFindAllUsers() {
        List<User> userList = dao.findAll();
        assertEquals(2, userList.size());
    }

    @Test
    @ExpectedDataSet(value = "userDataset/datasetRemoveUser.xml")
    public void testRemoveUser() {
        User user = new User();
        user.setId(2l);
        dao.remove(user);
    }

    @Test
    @ExpectedDataSet(value = "userDataset/datasetCreateUser.xml")
    public void testCreateUser() {
        User user = new User();
        user.setLogin("login2");
        user.setPassword("pass2");
        user.setEmail("email2");
        user.setFirstName("fname2");
        user.setLastName("lname2");
        user.setBirthday(new Date(100, 10, 02));
        Role role = new Role();
        role.setName("Admin");
        role.setId(1l);
        user.setRole(role);
        dao.create(user);
    }

    @Test
    @ExpectedDataSet(value = "userDataset/datasetFindUserByLogin.xml")
    public void testFindUserByLogin() {
        dao.findByLogin("login2");
    }

    @Test
    @ExpectedDataSet(value = "userDataset/datasetFindUserByLogin.xml")
    public void testFindUserByEmail() {
        dao.findByEmail("email1");
    }
}
