package com.douzone.mysite.repository;

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
	
	public void deleteByNoAndPassword(Long no, String password) {
		Map<String, Object> map = Map.of("no", no, "password", password);
		sqlSession.delete("guestbook.deleteByNoAndPassword", map);
	}
	
	public void insert(GuestBookVo vo) {
		sqlSession.insert("guestbook.insert", vo);
	}
	
	public List<GuestBookVo> findAll() {
		return	sqlSession.selectList("guestbook.findAll");
	}
}