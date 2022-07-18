package kr.ac.kopo.controller;

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
	public String signup(User item, HttpServletResponse response, RedirectAttributes ra, HttpSession session){
		String passwd = service.signup(item);//회원가입+임시비밀번호 가져오기
		ra.addFlashAttribute("passwd", passwd);// 임시 비밀번호 전송
		session.invalidate();// 로그인 되어있는 경우 새로 로그인!
		return "redirect:/";
	}

	@GetMapping("/findpasswd")
	public String findpasswd() {
		return "/member/findpasswd";
	}

	@PostMapping("/findpasswd")
	public String findpasswd(User user, RedirectAttributes ra, HttpSession session) {

		String passwd = service.findpasswd(user);//회원 가입 및 임시 비밀번호
		if (passwd != "") {			//임시 비밀번호 생성시~
			ra.addFlashAttribute("passwd", passwd);// 임시 비밀번호
			session.invalidate();//로그인 되어 있으면 리셋~
			return "redirect:/";
		}
		ra.addFlashAttribute("passwd", 1);// 존재하는 아이디가 없습니다.
		return "redirect:/member/findpasswd";
	}

	@GetMapping("/login")
	public String login() {
		return "/member/login";
	}

	@PostMapping("/login")
	public String login(User item, RedirectAttributes ra, HttpSession session) {
		item = service.login(item);
		if (item.getTel() == "1") {//login데이터가 없을시 Tel이 1로 들어와서 실패를 선언
			ra.addFlashAttribute("login", "false");
			return "redirect:/member/login";
		}
		session.setAttribute("member", item);//로그인시 세션~
		session.setMaxInactiveInterval(5 * 60);//로그인시 5분!
		return "redirect:/";
	}

	@RequestMapping("/logout")
	public String logout(HttpSession session) {
		session.invalidate();
		return "redirect:/";
	}

	@GetMapping("/updatepass")
	public String updatepass(HttpSession session, Model model, RedirectAttributes ra) {
		User user = (User) session.getAttribute("member");//로그인을 해서 가져온 세션을 가져온다!
		model.addAttribute("id", user.getId());//id를 담아서 
		return "/member/updatepass";
	}

	@PostMapping("/updatepass")
	public String updatepass(User user, RedirectAttributes ra, HttpSession session) {
		try {// session이 없어서 null오류가 뜨는 경우 처리
			User sessionitem = (User) session.getAttribute("member");
			user.setId(sessionitem.getId());
			user = service.updatepass(user);
			if (user.getLogincheck() == 1) {				//질문! 성공-> 여기서
				session.setAttribute("member", user);//교체 하고 바로 로그인 되도록
				session.setMaxInactiveInterval(5 * 60);
				ra.addFlashAttribute("start", 1);
				return "redirect:/";
			}
		} catch (Exception e) {
			ra.addFlashAttribute("update", 1);// 실패
			return "redirect:/";
		}													//->여기로 보내짐..?			
		session.setAttribute("member", user);//교체 하고 바로 로그인 되도록
		session.setMaxInactiveInterval(5 * 60);
		ra.addFlashAttribute("start", 1);
		return "redirect:/";
	}

	@ResponseBody
	@PostMapping("/idcheck")
	public boolean idcheck(@RequestBody String id) {
		String idval = id.replaceAll("\"", "");//id 값이 ajax으로 넘어올 경우 ""가 생긴 상태로 넘어옴
		if (idval.isEmpty())// form에 required으로 거르는게 아니라 직접 걸러줘야함
			return false;
		if (service.checkId(idval))
			return true;
		else
			return false;
	}
}