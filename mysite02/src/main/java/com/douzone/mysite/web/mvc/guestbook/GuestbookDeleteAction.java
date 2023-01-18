package com.douzone.mysite.web.mvc.guestbook;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.douzone.mysite.dao.GuestBookDao;
import com.douzone.web.mvc.Action;

public class GuestbookDeleteAction implements Action {

	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String noStr = request.getParameter("no");
		Long no = Long.parseLong(noStr);
		String password = request.getParameter("password");
		
		new GuestBookDao().deleteByEmail(no, password);
		response.sendRedirect(request.getContextPath() + "/guestbook");

	}

}
