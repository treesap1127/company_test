package kr.ac.kopo.interceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

import kr.ac.kopo.model.User;

public class LoginInterceptor extends HandlerInterceptorAdapter {

	@Override
	public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler)
			throws Exception {
		HttpSession session = request.getSession();
		
		User user=(User) session.getAttribute("member");
		
		if (user!=null) {
			return true;
		}
		String query=request.getQueryString();
		session.setAttribute("target", request.getRequestURI()+(query != null ? "?" + query:"" ));
		response.sendRedirect("/member/login");
		
		return false;
		
		
	}

}
