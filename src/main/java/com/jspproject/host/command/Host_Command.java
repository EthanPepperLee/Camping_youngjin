package com.jspproject.host.command;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public interface Host_Command {
	public void execute(HttpServletRequest request, HttpServletResponse response);
}
