package jspservlet.servlet;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import jspservlet.dao.HomeDAO;
import jspservlet.dao.UserDAO;
import jspservlet.dao.impl.HomeDAOImpl;
import jspservlet.dao.impl.UserDAOImpl;
import jspservlet.vo.Home;
import jspservlet.vo.Member;
import jspservlet.vo.UserLogin;

public class ShowServlet extends HttpServlet {


	public void doGet(HttpServletRequest req, HttpServletResponse res)
			throws IOException, ServletException{
		Member member = new Member();
		Member member1 = new Member();
		String homeID = new String();
		Home home1 = new Home();

		member.setMemberID(UserLogin.memberID);

		UserDAO dao = new UserDAOImpl();
		HomeDAO daoh = new HomeDAOImpl();
		int flag = 0;
		try {
			member1.setMemberName(dao.showInfo(member).getMemberName());
			member1.setMemberGender(dao.showInfo(member).getMemberGender());
			member1.setMemberPhone(dao.showInfo(member).getMemberPhone());


			member1.setHomeID(dao.showInfo(member).getHomeID());
			homeID = (dao.showInfo(member).getHomeID());
			home1.setHomeID(daoh.showHome(homeID).getHomeID());
			home1.setHomeName(daoh.showHome(homeID).getHomeName());
			home1.setHomeAddress(daoh.showHome(homeID).getHomeAddress());

			flag = 1;
		}catch(Exception e) {
			e.printStackTrace();
		}
		if(flag == 1) {
			HttpSession session=req.getSession();

			session.setAttribute("username", UserLogin.memberID);
			session.setAttribute("userNamee", member1.getMemberName());
			session.setAttribute("userGender", member1.getMemberGender());
			session.setAttribute("userPhone", member1.getMemberPhone());
			session.setAttribute("userHomeID", member1.getHomeID());
			session.setAttribute("homeName", home1.getHomeName());
			session.setAttribute("homeAdress", home1.getHomeAddress());



			session.setAttribute("bbbb", member.getMemberID());
			req.getRequestDispatcher("./show.jsp").forward(req, res);
		}else {
			res.sendRedirect("./portfolio.jsp");
		}


		/**	public void doPost(HttpServletRequest req, HttpServletResponse res)
		 throws IOException, ServletException{
		 User user = new User();
		 user.setUsername(req.getParameter("username"));
		 user.setPassword(req.getParameter("password"));

		 UserDAO dao = new UserDAOImpl();
		 int flag = 0;
		 try {
		 flag = dao.queryByUsername(user);
		 }catch(Exception e) {
		 e.printStackTrace();
		 }
		 if(flag == 1) {
		 HttpSession session=req.getSession();
		 session.setAttribute("username", user.getUsername());
		 res.sendRedirect("./welcome.jsp");
		 }else {
		 res.sendRedirect("./error.jsp");
		 }
		 }
		 */
	}
}
		
		
		/*
		Member member = new Member();
		 
		 }else {
			 res.sendRedirect("./error.jsp");
		 }
		 
	}
}
*/