package kh.study.team2.shop.config;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

public class AdminMenuInterceptors implements HandlerInterceptor{
	//@Resource(name = "adminService")
	//private AdminServiceImpl adminService;
	
	@Override
	public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler,
			ModelAndView modelAndView) throws Exception {
		
		String menu = request.getParameter("menu");
		
		if(menu == null) {
			menu = "1";
		}
		
		request.setAttribute("menu", menu);
	}
}
