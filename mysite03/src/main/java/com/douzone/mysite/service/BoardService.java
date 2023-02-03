package com.douzone.mysite.service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.douzone.mysite.repository.BoardRepository;
import com.douzone.mysite.vo.BoardVo;
import com.douzone.mysite.vo.UserVo;

@Service
public class BoardService {
	private static final int LIST_SIZE = 5; // 리스팅 되는 게시물의 수
	
	@Autowired
	private BoardRepository boardRepository;
	
	// 게시물 추가하기
	public void addContents(HttpSession session, BoardVo vo) {
		UserVo authUser = (UserVo)session.getAttribute("authUser");
		vo.setHit(3L);
		vo.setUserNo(authUser.getNo());
		boardRepository.insert(vo);
	}
	
	// 게시물 보기
	public BoardVo getContents(Long no) {
		return boardRepository.findByNo(no);
	}
	
	// 게시글 삭제
	public void deleteContents(Long no) {
		boardRepository.deleteByNo(no);
	}
	
	// 게시글 출력
	public Map<String, Object> getContentsList(int page, String keyword) {
		int toTalCount = boardRepository.getTotalCount(keyword);
//		System.out.println(toTalCount);

		int beginPage = 0;
		
		List<BoardVo> list = boardRepository.findAllByPageAndKeyWord(page, keyword, LIST_SIZE);
		
		Map<String, Object> map = new HashMap<>(); 
		map.put("list", list);
		map.put("beginPage", beginPage);
		map.put("totalPage", toTalCount / LIST_SIZE + 1);
		
		return map;
	}
	
	// 게시글 수정
	public void modifyContents(Long no, String title, String content) {
		boardRepository.modifyByNo(no, title, content);
	}
	
	// 댓글 달기
	public void addReply(HttpSession session, BoardVo vo) {
		UserVo authUser = (UserVo)session.getAttribute("authUser");
		vo.setHit(3L);
		vo.setUserNo(authUser.getNo());
		boardRepository.insertReply(vo);
	}
}
