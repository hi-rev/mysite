package com.douzone.mysite.web.mvc.board;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.douzone.mysite.dao.BoardDao;
import com.douzone.mysite.vo.BoardVo;
import com.douzone.web.mvc.Action;
import com.douzone.web.util.MvcUtil;

public class ReplyFormAction implements Action {

	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String noStr = request.getParameter("no");
		Long no = Long.parseLong(noStr);
		
		BoardVo vo = new BoardVo();
		vo.setNo(no);
		
		BoardVo board = new BoardDao().findByNo(vo);
		request.setAttribute("board", board);
		
		MvcUtil.forward("board/reply", request, response);
	}

}
