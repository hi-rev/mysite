package com.douzone.mysite.web.mvc.board;

import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.douzone.mysite.dao.BoardDao;
import com.douzone.mysite.vo.BoardVo;
import com.douzone.mysite.vo.UserVo;
import com.douzone.web.mvc.Action;
import com.douzone.web.util.MvcUtil;

public class ReplyAction implements Action {

	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// Access Control(보안, 인증체크)
		HttpSession session = request.getSession();
		UserVo authUser = (UserVo)session.getAttribute("authUser");
		if(authUser == null) {
			MvcUtil.redirect(request.getContextPath(), request, response);
			return;
		}
		
		// 제목 & 내용
		String title = request.getParameter("title");
		String contents = request.getParameter("content");
		String gNoStr = request.getParameter("gno");
		Long gNo = Long.parseLong(gNoStr);
		String depthStr = request.getParameter("depth");
		Long depth = Long.parseLong(depthStr);
		
		BoardVo vo = new BoardVo();
		vo.setTitle(title);
		vo.setContents(contents);
		vo.setHit(3L);
		vo.setgNo(gNo);
		vo.setDepth(depth+1);
		
		Date today = new Date();
		Locale currentLocale = new Locale("KOREAN", "KOREA");
		String pattern = "yyyyMMddHHmmss";
		SimpleDateFormat formatter = new SimpleDateFormat(pattern, currentLocale);
		vo.setRegDate(formatter.format(today));
		vo.setUserNo(authUser.getNo()); // 현재 로그인 한 사용자

		new BoardDao().insertReply(vo);
		MvcUtil.redirect(request.getContextPath() + "/board?a=list&page=1", request, response);
	}

}
