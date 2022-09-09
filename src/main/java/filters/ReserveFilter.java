package filters;

import java.io.IOException;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import bo.User;

/**
 * Servlet Filter implementation class ReserveFilter
 */
@WebFilter("/reserve")
public class ReserveFilter extends HttpFilter implements Filter {

	/**
	 * @see Filter#doFilter(ServletRequest, ServletResponse, FilterChain)
	 */
	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
			throws IOException, ServletException {
		HttpServletRequest req = (HttpServletRequest) request;
		User signedin = (User) req.getSession().getAttribute("signedin");
		System.out.println("doFilter");
		Cookie[] cookies = req.getCookies();
//		if (cookies != null)
//		{
//			 for (Cookie current : cookies) {
//				 if (current.getName().equals("sesouviens"))
//				 {
//					 if (current.getValue().equals("1")) 
//						 signedin = true;
//				 }
//			 }
//		}

		if (signedin != null) {
			chain.doFilter(request, response);
		}

		else {
			HttpServletResponse resp = (HttpServletResponse) response;
			resp.sendRedirect("acceuil");
			// request.getRequestDispatcher("/WEB-INF/signin.jsp").forward(request,
			// response);
		}
	}

}
