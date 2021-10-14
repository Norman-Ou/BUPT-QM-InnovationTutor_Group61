package jspservlet.servlet;

import java.io.IOException;
import java.sql.SQLException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import jspservlet.dao.HomeDAO;
import jspservlet.dao.UserDAO;
import jspservlet.dao.impl.HomeDAOImpl;
import jspservlet.dao.impl.RegisterDao;
import jspservlet.dao.impl.UserDAOImpl;
import jspservlet.vo.Home;
import jspservlet.vo.Member;
import jspservlet.vo.UserLogin;



public class ChangeServlet extends HttpServlet{
	public void doGet(HttpServletRequest req, HttpServletResponse res)
			throws IOException, ServletException{


	}

	public void doPost(HttpServletRequest req, HttpServletResponse res)
			throws IOException, ServletException{

		int flag = 0;
		int index = 0;


		//old user information
		Member member = new Member();
		//new user information(successfully changed new information)
		Member member1 = new Member();
		Home home = new Home();

		String newpassword = new String();

		UserDAO dao = new UserDAOImpl();
		HomeDAO hdao = new HomeDAOImpl();
		HttpSession session=req.getSession();

		String nhname = new String();
		String nhaddress = new String();
		String nhpassword = new String();
		String rnhpassword = new String();
		String ahomeid = new String();
		nhname = req.getParameter("NhomeName");
		nhaddress = req.getParameter("NhomeAdress");
		nhpassword = req.getParameter("Nhpassword");
		rnhpassword = req.getParameter("RNhpassword");
		ahomeid=req.getParameter("Ahomeid");



		if(nhpassword==null) {
			index = 1;
		}else {
			if(ahomeid==null) {
				index = 2;
			}else {
				index = 3;
			}


		}


		switch(index) {
			case 1: //user login in
				//member get old user information
				member.setMemberID(UserLogin.memberID);
				member.setMemberName(req.getParameter("userNamee"));
				member.setMemberPWD(req.getParameter("password"));
				member.setMemberGender(req.getParameter("userGender"));
				member.setMemberPhone(req.getParameter("userPhone"));
				member.setHomeID(req.getParameter("userHomeID"));
				newpassword = req.getParameter("newpassword");


				//if user want to change password
				if(!member.getMemberPWD().equals("")) {
					try {
						flag = dao.infochange(member, newpassword);

					}catch(SQLException e) {
						e.printStackTrace();
					} catch (Exception e) {
						e.printStackTrace();
					}
				}
				//overwrite
				if(flag==1 || member.getMemberPWD().equals("")) {
					try {
						flag = dao.create(member);

					} catch (Exception e) {
						e.printStackTrace();
					}
				}




				if(flag == 1) {
					try {
						//member1 get changed new information
						member1.setMemberName(dao.showInfo(member).getMemberName());
						member1.setMemberGender(dao.showInfo(member).getMemberGender());
						member1.setMemberPhone(dao.showInfo(member).getMemberPhone());
						member1.setHomeID(dao.showInfo(member).getHomeID());




					} catch (Exception e) {
						e.printStackTrace();
					}


					home.setHomeID(member1.getHomeID());
					try {
						hdao.showHome(home.getHomeID());
						home.setHomeName(hdao.showHome(home.getHomeID()).getHomeName());
						home.setHomeAddress(hdao.showHome(home.getHomeID()).getHomeAddress());

					} catch (Exception e1) {
						e1.printStackTrace();
					}


					//show
					session.setAttribute("username", member.getMemberID());
					session.setAttribute("userNamee", member1.getMemberName());
					session.setAttribute("userGender", member1.getMemberGender());
					session.setAttribute("userPhone", member1.getMemberPhone());
					session.setAttribute("userHomeID", member1.getHomeID());
					session.setAttribute("homeName", home.getHomeName());
					session.setAttribute("homeAdress", home.getHomeAddress());



					req.getRequestDispatcher("./show.jsp").forward(req, res);
				}else {
					res.sendRedirect("./portfolio.jsp");
				}

				break;

			case 2://register-create home
				if(nhpassword.equals(rnhpassword)) {
					String homeID = new String();

					try {
						homeID = hdao.register(nhname, nhaddress, nhpassword);
						UserLogin.setHomeID(homeID);
					} catch (Exception e) {
						e.printStackTrace();
					}

					req.getRequestDispatcher("./index.jsp").forward(req, res);


					//System.out.println("register");
				}else {
					res.sendRedirect("./portfolio.jsp");
				}

				break;
			case 3://register-join home
				Home home2 = new Home();
				home2.setHomeID(ahomeid);
				home2.setHomePWD(nhpassword);

				try {
					hdao.anohomechange(home2);
				} catch (Exception e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				req.getRequestDispatcher("./index.jsp").forward(req, res);
				break;
		}

	}
}
