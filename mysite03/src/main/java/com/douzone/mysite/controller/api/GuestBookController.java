package com.douzone.mysite.controller.api;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.douzone.mysite.dto.JsonResult;
import com.douzone.mysite.service.GuestBookService;
import com.douzone.mysite.vo.GuestBookVo;

@RestController("guestbookApiController")
@RequestMapping("/guestbook/api")
public class GuestBookController {
	
	@Autowired
	private GuestBookService guestBookService;
	
	// 1. add
	@PostMapping("")
	public JsonResult add(@RequestBody GuestBookVo vo) {
		guestBookService.addMessage(vo);
		
		return JsonResult.success(vo);
	}

}
