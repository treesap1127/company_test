package kr.ac.kopo.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class RootController {
	@RequestMapping("/")
	public String index() {
		return "index";
	}
	@GetMapping("/top_menu")
	public String top() {
		return "/topmenu";
	}
}