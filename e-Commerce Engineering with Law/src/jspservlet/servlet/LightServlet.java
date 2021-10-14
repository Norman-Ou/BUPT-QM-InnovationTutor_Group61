package jspservlet.servlet;

import java.util.ArrayList;

import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;


import jspservlet.dao.LightDAO;
import jspservlet.dao.impl.LightDAOImpl;
import jspservlet.vo.Light;


public class LightServlet extends HttpServlet{
	public void doGet(HttpServletRequest req,HttpServletResponse res) {
		LightDAO dao=new LightDAOImpl();
		HttpSession session=req.getSession();
		ArrayList<Light> linfo;
		ArrayList<String> ltype;

		try {
			res.setContentType("text/html;charset=utf-8");
			req.setCharacterEncoding("utf-8");

			String judge=req.getParameter("action");
			String id=req.getParameter("id");

			if (judge!=null) {
				if (judge.equals("changeon")) {
					dao.changeLight(id, "60");
				}
				if (judge.equals("changeoff")) {
					dao.changeLight(id, "0");
				}
				if (judge.equals("delete")) {
					dao.deleteLight(id);
				}
			}
			linfo=dao.allLightid();
			ltype=dao.allLightType();
			session.setAttribute("linfo", linfo);
			session.setAttribute("ltype",ltype);
			req.getRequestDispatcher("./light.jsp").forward(req,res);
			//res.sendRedirect("./light.jsp");
		}
		catch(Exception e){
			e.printStackTrace();
		}
	}
	
	public void doPost(HttpServletRequest req,HttpServletResponse res){
		String brightness;
		String id;
		try {
			LightDAO dao=new LightDAOImpl();
			req.setCharacterEncoding("utf-8");
			id=req.getParameter("id");
			brightness=dao.queryLight(id).getState();
			res.setCharacterEncoding("utf-8");
		}
		catch(Exception e){
			e.printStackTrace();
		}
	}
}
