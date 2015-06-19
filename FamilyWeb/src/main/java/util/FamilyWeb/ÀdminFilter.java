package util.FamilyWeb;

import java.io.IOException;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;

import domain.FamilyWeb.Administrator;
import domain.FamilyWeb.User;

/**
 * Servlet Filter implementation class ÀdminFilter
 */
public class ÀdminFilter implements Filter {	

	/**
	 * @see Filter#doFilter(ServletRequest, ServletResponse, FilterChain)
	 */
	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
		HttpServletRequest req = (HttpServletRequest) request;
		Object userObject = req.getSession().getAttribute("user");
		if((userObject != null) && (userObject instanceof User)){
			if(userObject instanceof Administrator){
				chain.doFilter(req, response);
			}else{
				req.setAttribute("message", "Doesn't have the rights to go there.");
				req.getRequestDispatcher("/socialworker/startscreen_socialworker.html").forward(req, response);
			}
		}else{
			req.setAttribute("message", "You have to login to go there.");
			req.getRequestDispatcher("/login.html").forward(req, response);
		}		
	}

	/**
	 * @see Filter#init(FilterConfig)
	 */
	public void init(FilterConfig fConfig) throws ServletException {}
	
	/**
	 * @see Filter#destroy()
	 */
	public void destroy() {	}
}
