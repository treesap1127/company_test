package kr.ac.kopo.controller;

import java.io.IOException;

import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import kr.ac.kopo.model.User;
import kr.ac.kopo.service.MemberService;

@Controller
@RequestMapping("/member")
public class MemberController {
	@Autowired
	MemberService service;
	@GetMapping("/signup")
	public String signup() {
		return "/member/signup";
	}
	@PostMapping("/signup")
	public String signup(User item,HttpServletResponse response,RedirectAttributes ra) throws IOException {
		String passwd=service.signup(item);
		ra.addFlashAttribute("passwd", passwd);
		return "redirect:/";
	}
	@GetMapping("/findpasswd")
	public String findpasswd() {
		return "/member/findpasswd";
	}
	@PostMapping("/findpasswd")
	public String findpasswd(User user,RedirectAttributes ra) {
		
		String passwd=service.findpasswd(user);
		if(passwd!="") {
			ra.addFlashAttribute("passwd", passwd);
			return "redirect:/";
		}
		ra.addFlashAttribute("passwd", 1);
		return "redirect:/member/findpasswd";
	}
	@GetMapping("/login")
	public String login() {
		return "/member/login";
	}
	@PostMapping("/login")
	public String login(User item,RedirectAttributes ra,HttpSession session) {
		item = service.login(item);
		if(item.getTel()=="1") {
			ra.addFlashAttribute("login", "false");
			return "redirect:/member/login";
		}
		session.setAttribute("member", item);
		session.setMaxInactiveInterval(5*60);
		return "redirect:/";
	}
	@RequestMapping("/logout")
	public String logout(HttpSession session) {
		session.invalidate();
		return "redirect:/";
	}
	@GetMapping("/updatepass")
	public String updatepass(HttpSession session,Model model,RedirectAttributes ra) {
		User user=(User) session.getAttribute("member");
		if(user==null) {
			ra.addFlashAttribute("update", 0);
			return "redirect:/";
		}
		model.addAttribute("id",user.getId());
		return "/member/updatepass";
	}
	@PostMapping("/updatepass")
	public String updatepass(User user,RedirectAttributes ra) {
		boolean check=service.updatepass(user);
		if(check) {
			return "redirect:/";
		}
		ra.addFlashAttribute("update", 1);
		return "redirect:/";
	}
	@ResponseBody
	@PostMapping("/idcheck")
	public boolean idcheck(@RequestBody String id) {
		String idval=id.replaceAll("\"", "");
		if(idval.isEmpty())return false;
		if(service.checkId(idval))return true;
		else return false;
	}
}