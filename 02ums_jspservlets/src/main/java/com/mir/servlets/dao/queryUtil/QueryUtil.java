package com.mir.servlets.dao.queryUtil;

/**
 * class QueryUtil.
 *
 * @author R.M.
 * @since 2022
 */
public class QueryUtil {

    /**
     * Sql query from USERS_DB
     */
    public static final String SQL_CREATE_USER = "INSERT INTO USERS_DB (LOGIN, PASSWORD, EMAIL, " +
            "FIRSTNAME, LASTNAME, BIRTHDAY, ROLE_ID) " +
            "VALUES(?, ?, ?, ?, ?, ?, ?)";
    public static final String SQL_UPDATE_USER = "UPDATE USERS_DB SET LOGIN=?, PASSWORD=?, EMAIL=?, FIRSTNAME=?, " +
            "LASTNAME=?, BIRTHDAY=?, ROLE_ID=? WHERE ID=?";
    public static final String SQL_REMOVE_USER = "DELETE FROM USERS_DB WHERE ID=?";
    public static final String SQL_FIND_ALL_USERS = "Select u.id, u.login , u.password, u.email, u.firstName, " +
            "u.lastName, u.birthday, r.id as role_id, r.name FROM USERS_DB u LEFT OUTER JOIN ROLES_DB r ON u.role_id = r.id";
    public static final String SQL_FIND_USER_BY_LOGIN = "Select u.id, u.login , u.password, u.email, " +
            "u.firstName, u.lastName, u.birthday, r.id as ROLE_ID, r.name FROM USERS_DB u LEFT OUTER " +
            "JOIN ROLES_DB r ON u.ROLE_ID = r.id WHERE u.login=?";
    public static final String SQL_FIND_USER_BY_EMAIL = "Select u.id, u.login , u.password, " +
            "u.email, u.firstName, u.lastName, u.birthday,r.id as role_id, r.name FROM USERS_DB u " +
            "LEFT OUTER JOIN ROLES_DB r ON u.role_id = r.id WHERE u.email=?";

    /**
     * Sql query from ROLES_DB
     */
    public static final String SQL_FIND_ALL_ROLES = "SELECT id, name FROM ROLES_DB";
    public static final String SQL_FIND_BY_ID_ROLE = "SELECT * FROM ROLES_DB WHERE ID=?";
    public static final String SQL_CREATE_ROLE = "INSERT INTO ROLES_DB (ID, NAME) " +
            "VALUES(?, ?)";
    public static final String SQL_REMOVE_ROLE = "DELETE FROM ROLES_DB WHERE ID=?";
    public static final String SQL_UPDATE_ROLE = "UPDATE ROLES_DB SET NAME=? WHERE ID=?";


}
