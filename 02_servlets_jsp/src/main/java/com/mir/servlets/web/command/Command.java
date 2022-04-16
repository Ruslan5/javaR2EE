package com.mir.servlets.web.command;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.Serializable;

/**
 * abstract class Command
 *
 * @author R.Mirzoiev
 * @see Serializable
 * @since 31.01.2022
 */
public abstract class Command implements Serializable {

	public abstract String execute(HttpServletRequest request,
			HttpServletResponse response) throws IOException, ServletException;

	@Override
	public final String toString() {
		return getClass().getSimpleName();
	}
}