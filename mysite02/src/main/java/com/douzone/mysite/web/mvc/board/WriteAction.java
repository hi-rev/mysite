package com.douzone.mysite.web.mvc.board;

import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.douzone.mysite.dao.BoardDao;
import com.douzone.mysite.vo.BoardVo;
import com.douzone.web.mvc.Action;
import com.douzone.web.util.MvcUtil;

public class WriteAction implements Action {

	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// 제목 & 내용
		String title = request.getParameter("title");
		String contents = request.getParameter("content");
		
		BoardVo vo = new BoardVo();
		vo.setTitle(title);
		vo.setContents(contents);
		vo.setHit(3L);
		
		Date today = new Date();
		Locale currentLocale = new Locale("KOREAN", "KOREA");
		String pattern = "yyyyMMddHHmmss";
		SimpleDateFormat formatter = new SimpleDateFormat(pattern, currentLocale);
		vo.setRegDate(formatter.format(today));
		
		vo.setgNo(1L);
		vo.setoNo(1L);
		vo.setDepth(0L);
		vo.setUserNo(1L);
		
		new BoardDao().insert(vo);
		MvcUtil.redirect(request.getContextPath() + "/board", request, response);
	}
}
