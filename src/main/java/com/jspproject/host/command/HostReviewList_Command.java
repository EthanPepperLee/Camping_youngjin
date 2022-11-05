package com.jspproject.host.command;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class HostReviewList_Command implements Host_Command {

	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) {
		String hSeq = request.getParameter(null);
		
		
	}

}
