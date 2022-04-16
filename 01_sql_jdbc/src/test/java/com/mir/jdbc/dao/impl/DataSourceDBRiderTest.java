package com.mir.jdbc.dao.impl;

import com.github.database.rider.core.DBUnitRule;
import com.github.database.rider.core.api.dataset.DataSet;
import com.github.database.rider.core.api.dataset.ExpectedDataSet;
import com.github.database.rider.core.api.dataset.SeedStrategy;
import com.mir.jdbc.entity.Role;
import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.JUnit4;

import java.util.List;

import static org.junit.Assert.assertEquals;

@RunWith(JUnit4.class)
@DataSet(strategy = SeedStrategy.INSERT, cleanBefore = true, cleanAfter = true, executeScriptsBefore = {
        "testScript.sql"}
)
public class DataSourceDBRiderTest extends JdbcRoleDaoTest {
    public JdbcRoleDao dao = new JdbcRoleDao(configManager);

    @Rule
    public DBUnitRule dbUnitRule = DBUnitRule.instance(dao.getConnection());

    @Test
    @ExpectedDataSet(value = "dataset/dataset.xml")
    public void testFindAllRoles() {
        List<Role> roleList = dao.findAll();
        assertEquals(2, roleList.size());
        assertEquals("Admin", roleList.get(0).getName());
        assertEquals("user", roleList.get(1).getName());
    }

    @Test
    @ExpectedDataSet(value = "dataset/datasetRemoveRole.xml")
    public void testRemoveRole() {
        Role role = new Role();
        role.setId(3l);
        role.setName("patient");
        dao.remove(role);
    }

    @Test
    @ExpectedDataSet(value = "dataset/datasetCreate.xml")
    public void testCreateRole() {
        Role role = new Role();
        role.setId(3l);
        role.setName("patient");
        dao.create(role);
    }
}
