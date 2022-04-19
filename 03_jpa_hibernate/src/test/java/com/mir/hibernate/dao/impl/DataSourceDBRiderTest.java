package com.mir.hibernate.dao.impl;

import com.github.database.rider.core.DBUnitRule;
import com.github.database.rider.core.api.dataset.DataSet;
import com.github.database.rider.core.api.dataset.ExpectedDataSet;
import com.github.database.rider.core.api.dataset.SeedStrategy;
import com.mir.hibernate.dao.GenericHibernateDao;
import com.mir.hibernate.entity.Role;

import org.hibernate.engine.jdbc.connections.spi.ConnectionProvider;
import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.JUnit4;

import java.sql.SQLException;
import java.util.List;

import static org.junit.Assert.assertEquals;

@RunWith(JUnit4.class)
@DataSet(strategy = SeedStrategy.INSERT, cleanBefore = true, cleanAfter = true, executeScriptsBefore = {
        "testScript.sql"}
)
public class DataSourceDBRiderTest {
    public HibernateRoleDao dao = new HibernateRoleDao();
    GenericHibernateDao genericHibernateDao = new HibernateRoleDao();

    public DataSourceDBRiderTest() throws SQLException {
    }

    @Rule
    public DBUnitRule dbUnitRule =  DBUnitRule.instance(genericHibernateDao
            .getSessionFactory()
            .getSessionFactoryOptions().getServiceRegistry()
            .getService(ConnectionProvider.class).getConnection());

    @Test
    @ExpectedDataSet(value = "dataset/dataset.xml")
    public void testFindAllRoles() {
        List<Role> roleList = dao.findAll();
        assertEquals(2, roleList.size());
        assertEquals("Admin", roleList.get(0).getName());
        assertEquals("user", roleList.get(1).getName());
    }

    @Test
    @ExpectedDataSet(value = "dataset/datasetCreate.xml")
    public void testCreateRole() {
        Role role = new Role();
        role.setId(3l);
        role.setName("patient");
        dao.create(role);
    }
    @Test
    @ExpectedDataSet(value = "dataset/datasetUpdate.xml")
    public void testUpdateRole() {
        Role role = new Role();
        role.setId(2l);
        role.setName("guest");
        dao.update(role);
    }

    @Test
    @ExpectedDataSet(value = "dataset/datasetRemoveRole.xml")
    public void testRemoveRole() {
        Role role = new Role();
        role.setId(2l);
        dao.remove(role);
    }

    @Test
    @ExpectedDataSet(value = "dataset/datasetFindByIdRole.xml")
    public void testRoleById() {
        dao.findById(1l);
    }
}
