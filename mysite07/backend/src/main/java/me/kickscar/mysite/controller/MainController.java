package me.kickscar.mysite.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import me.kickscar.mysite.dto.JsonResult;
import me.kickscar.mysite.service.SiteService;

@Controller
@RequestMapping("/api/site")
public class MainController {
	
	@Autowired
	private SiteService siteService;
	
	@GetMapping("")
	public ResponseEntity<JsonResult> index(Model model) {
		return ResponseEntity.status(HttpStatus.OK).body(JsonResult.success(siteService.getSite()));
		}
	
}
