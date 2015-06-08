package util.FamilyWeb;

import java.io.IOException;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;

import domain.FamilyWeb.Socialworker;
import domain.FamilyWeb.User;

/**
 * Servlet Filter implementation class SocialworkerFilter
 */
public class SocialworkerFilter implements Filter {
	/**
	 * @see Filter#destroy()
	 */
	public void destroy() {
		// TODO Auto-generated method stub
	}

	/**
	 * @see Filter#doFilter(ServletRequest, ServletResponse, FilterChain)
	 */
	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
		HttpServletRequest req = (HttpServletRequest) request;
		Object userObject = req.getSession().getAttribute("user");

		if((userObject != null) && (userObject instanceof User)){
			if(userObject instanceof Socialworker){
				chain.doFilter(req, response);
			}else{
				//not socialworker
				req.setAttribute("message", "Doesn't have the rights to go there.");
				req.getRequestDispatcher("/adinistrator/startscreen_administrator.html").forward(req, response);
				}
		}else{
			req.setAttribute("message", "You have to login to go there.");
			req.getRequestDispatcher("/login.html").forward(req, response);
		}
	}

	/**
	 * @see Filter#init(FilterConfig)
	 */
	public void init(FilterConfig fConfig) throws ServletException {
		// TODO Auto-generated method stub
	}

}
