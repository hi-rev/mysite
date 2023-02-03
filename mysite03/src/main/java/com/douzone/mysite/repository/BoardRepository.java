package com.douzone.mysite.repository;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.douzone.mysite.vo.BoardVo;

@Repository
public class BoardRepository {

	@Autowired
	private SqlSession sqlSession;
	
	public List<BoardVo> findAllByPageAndKeyWord(int page, String keyword, int size) {
		Map<String, Object> map = new HashMap<>();
		map.put("startOffset", (page-1)*size);
		map.put("size", size);
		map.put("keyword", keyword);
		
		return sqlSession.selectList("board.findAllByPageAndKeyWord", map);
	}

	public int getTotalCount(String keyword) {
		return sqlSession.selectOne("board.getTotalCount", keyword);
	}

	public void insert(BoardVo vo) {
		sqlSession.insert("board.insert", vo);
	}

	public void deleteByNo(Long no) {
		sqlSession.delete("board.deleteByNo", no);
	}

	public BoardVo findByNo(Long no) {
		return sqlSession.selectOne("board.findByNo", no);
	}

	public void modifyByNo(Long no, String title, String content) {
		Map<String, Object> map = Map.of("no", no, "title", title, "contents", content);
		sqlSession.update("board.modifyByNo", map);
	}

	public void insertReply(BoardVo vo) {
		sqlSession.insert("board.insertReply", vo);
	}

}
