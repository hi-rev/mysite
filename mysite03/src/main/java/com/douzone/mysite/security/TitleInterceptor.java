package com.douzone.mysite.security;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.servlet.HandlerInterceptor;

import com.douzone.mysite.service.SiteService;
import com.douzone.mysite.vo.SiteVo;

// header에 공통된 title 값 넘겨주기
public class TitleInterceptor implements HandlerInterceptor {
	
	@Autowired
	private SiteService siteService;

	@Override
	public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler)
			throws Exception {
		SiteVo site = siteService.getSite();
		System.out.println(site.getTitle());
		
		HttpSession session = request.getSession(true);
		session.setAttribute("site", site);
		
		return true;
	}

}
