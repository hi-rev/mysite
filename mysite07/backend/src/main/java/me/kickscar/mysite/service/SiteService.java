package me.kickscar.mysite.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import me.kickscar.mysite.repository.SiteRepository;
import me.kickscar.mysite.vo.SiteVo;

@Service
public class SiteService {

	@Autowired
	private SiteRepository siteRepository;

	public SiteVo getSite() {
		return siteRepository.find();
	}

}
