package com.douzone.mysite.repository;

import java.util.List;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.douzone.mysite.vo.GalleryVo;

@Repository
public class GalleryRepository {
	
	@Autowired
	private SqlSession sqlSession;

	public List<GalleryVo> find() {
		return sqlSession.selectList("gallery.find");
	}

	public void insert(GalleryVo vo) {
		sqlSession.insert("gallery.insert", vo);
	}

}
