package com.mir.servlets;

/**
 * Path holder (jsp pages, controller commands).
 * 
 * @author R.M.
 * @since 2022
 * 
 */
public final class Path {
	
	// pages
	public static final String PAGE_LOGIN = "/login.jsp";
	public static final String PAGE_LIST_USERS = "/listusers.jsp";
	public static final String PAGE_EDIT_USERS = "/WEB-INF/jsp/admin/editform.jsp";
	public static final String PAGE_ADD_USERS = "/WEB-INF/jsp/admin/add.jsp";
	public static final String PAGE_REMOVE_USERS = "/WEB-INF/jsp/admin/remove.jsp";
	public static final String PAGE_USER = "/WEB-INF/jsp/user/userPage.jsp";
	public static final String PAGE_ERROR_PAGE = "/error_page.jsp";

	// commands
	public static final String COMMAND_LIST_USERS = "/controller?command=userList";
	public static final String COMMAND_ADD_USER = "/controller?command=add";
	public static final String COMMAND_ADD_PAGE = "/controller?command=addPage";
}