package com.douzone.mysite.controller;

import java.util.Map;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.douzone.mysite.service.BoardService;
import com.douzone.mysite.vo.BoardVo;

@Controller
@RequestMapping("/board")
public class BoardController {
	@Autowired
	private BoardService boardService;
	
	// 1. 게시판 페이지 넘어가기
	@RequestMapping("/list/{i}")
	public String list(@PathVariable("i") int page, Model model) {
		Map<String, Object> map = boardService.getContentsList(page, "");
		
		model.addAttribute("map", map);
		model.addAttribute("p", page);
		return "board/list";
	}
	
	// 2. 글쓰기 페이지
	@RequestMapping(value="/write", method=RequestMethod.GET)
	public String write() {
		return "board/write";
	}
	
	// 3. 글 쓰기
	@RequestMapping(value="/write", method=RequestMethod.POST)
	public String write(HttpSession session, BoardVo vo) {
		boardService.addContents(session, vo);
		return "redirect:/board/list/1";
	}
	
	// 4. 삭제하기
	@RequestMapping("/delete/{no}")
	public String delete(@PathVariable("no") Long no) {
		boardService.deleteContents(no);
		return "redirect:/board/list/1";
	}
	
	// 5. 글 보기
	@RequestMapping("/view/{no}")
	public String view(@PathVariable("no") Long no, Model model) {
		BoardVo vo = boardService.getContents(no);
		model.addAttribute("board", vo);
		return "board/view";
	}
}
