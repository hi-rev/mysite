package com.douzone.mysite.repository;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.douzone.mysite.vo.GuestBookVo;

@Repository
public class GuestBookRepository {
	@Autowired
	private SqlSession sqlSession;
	
	public int deleteByNoAndPassword(Long no, String password) {
		Map<String, Object> map = Map.of("no", no, "password", password);
		return sqlSession.delete("guestbook.deleteByNoAndPassword", map);
	}
	
	public void insert(GuestBookVo vo) {
		sqlSession.insert("guestbook.insert", vo);
	}
	
	public List<GuestBookVo> findAll() {
		return	sqlSession.selectList("guestbook.findAll");
	}

	public List<GuestBookVo> findAllByNo(Long startNo) {
		Map<String, Object> map = new HashMap<String, Object>();
        map.put("startNo", startNo);
        map.put("size", startNo + 5);
		return sqlSession.selectList("guestbook.findAllByNo", map);
	}
}