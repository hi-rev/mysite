package com.douzone.mysite.controller.api;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
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
	
	// 2. list
	@GetMapping("")
	public JsonResult list(@RequestParam(value="sno", required=true, defaultValue="0") Long startNo) {
		List<GuestBookVo> list = new ArrayList<>();
		list = guestBookService.getMessageListByNo(startNo);
		return JsonResult.success(list);
	}
	
	// 3. delete
	@DeleteMapping("/{no}")
	public JsonResult delete(
			@PathVariable("no") Long no, 
			@RequestParam(value="password", required=true, defaultValue="") String password) {
		
		boolean result = guestBookService.deleteMessage(no, password);
		return JsonResult.success(result ? no : -1);
	}

}
