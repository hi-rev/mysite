package com.douzone.mysite.web.mvc.board;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.douzone.mysite.dao.BoardDao;
import com.douzone.mysite.vo.BoardVo;
import com.douzone.mysite.vo.UserVo;
import com.douzone.web.mvc.Action;
import com.douzone.web.util.MvcUtil;

public class ListAction implements Action {
	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// Access Control(보안, 인증체크)
		HttpSession session = request.getSession();
		UserVo authUser = (UserVo)session.getAttribute("authUser");
		
		int totalPage = new BoardDao().getTotalCount() / 5 + 1;
		request.setAttribute("totalPage", totalPage);
		
		String pageStr = request.getParameter("page");
		int page = Integer.parseInt(pageStr);
		request.setAttribute("p", page);
		
		List<BoardVo> list = new BoardDao().findAll(page);
		request.setAttribute("list", list);
		request.setAttribute("authUser", authUser);
		
		MvcUtil.forward("board/list", request, response);
	}
}
