package jspservlet.servlet;

import java.io.IOException;
import java.sql.SQLException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import jspservlet.dao.UserDAO;
import jspservlet.dao.impl.RegisterDao;
import jspservlet.dao.impl.UserDAOImpl;
import jspservlet.vo.Member;
import jspservlet.vo.UserLogin;


public class LoginServlet extends HttpServlet {

	public void doGet(HttpServletRequest req, HttpServletResponse res)
			throws IOException, ServletException{
//		 User user = new User();
//		 user.setUsername(req.getParameter("username"));
//		 user.setPassword(req.getParameter("password"));
//
//		 UserDAO dao = new UserDAOImpl();
//	     int flag = 0;
//	     try {
//				flag = dao.queryByUsername(user);
//			} catch (Exception e) {
//				// TODO Auto-generated catch block
//				e.printStackTrace();
//		}
//		 if(flag == 1){
//			 HttpSession session=req.getSession();
//	         session.setAttribute("username", user.getUsername());
//	         res.sendRedirect("./welcome.jsp");
//	        } else {
//	         res.sendRedirect("./error.jsp");
//	        }
	}

	public void doPost(HttpServletRequest req, HttpServletResponse res)
			throws IOException, ServletException{
		Member member = new Member();
		member.setMemberName(req.getParameter("name"));
		member.setMemberPWD(req.getParameter("pwd"));

		HttpSession session=req.getSession();
		UserDAO dao = new UserDAOImpl();
		RegisterDao re=new RegisterDao();
		req.setCharacterEncoding("utf-8");
		String rname=req.getParameter("rname");
		String rpwd=req.getParameter("rpwd");
		String rpwd1=req.getParameter("rpwd1");
		String gender=req.getParameter("gender");
		String phone=req.getParameter("phone");

		int flag=0;


		if(rname==null) {


			try {
				flag = dao.queryByUsername(member);
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			if(flag == 1){
				session.setAttribute("membername", member.getMemberName());
				UserLogin.setMemberName(member.getMemberName());
				/*res.sendRedirect("./welcome.jsp");*/
				req.getRequestDispatcher("./index.jsp").forward(req, res);
			} else {
				res.sendRedirect("./login.jsp");


			}
		}else {
			try {
				flag=re.register(rname,rpwd,rpwd1,gender,phone);
				member.setMemberName(req.getParameter("rname"));
				member.setMemberPWD(req.getParameter("rpwd"));
				flag = dao.queryByUsername(member);
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}



			session.setAttribute("rname", rname);
			session.setAttribute("rpwd", rpwd);
			session.setAttribute("rpwd1", rpwd1);
			res.sendRedirect("./register.jsp");
		}




	}
}


