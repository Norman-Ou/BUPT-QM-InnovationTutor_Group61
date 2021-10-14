package jspservlet.servlet;

import java.util.ArrayList;

import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import jspservlet.dao.DoorDAO;
import jspservlet.dao.WindowDAO;
import jspservlet.dao.impl.DoorDAOImpl;
import jspservlet.dao.impl.WindowDAOImpl;
import jspservlet.vo.Window;

public class WindowServlet extends HttpServlet{

	public void doGet(HttpServletRequest req,HttpServletResponse res) {
		WindowDAO dao=new WindowDAOImpl();
		HttpSession session=req.getSession();
		ArrayList<Window> wininfo=new ArrayList<Window>();
		ArrayList<String> wtype=new ArrayList<>();
		try {
			wininfo=dao.allWindowid();
			wtype=dao.allWindowType();
			session.setAttribute("wininfo", wininfo);
			session.setAttribute("wtype", wtype);
			req.getRequestDispatcher("./window.jsp").forward(req,res);
		}
		catch(Exception e){
			e.printStackTrace();
		}
	}

	public void doPost(HttpServletRequest req,HttpServletResponse res){
		String decibel;
		String id;
		String result;
		try {
			WindowDAO dao=new WindowDAOImpl();
			req.setCharacterEncoding("utf-8");
			id=req.getParameter("id");
			decibel=dao.queryWindow(id).getDecibel();
			result="{\"decibel\":\""+decibel+"\"}";
			res.setCharacterEncoding("utf-8");
			res.getWriter().print(result);
		}
		catch(Exception e){
			e.printStackTrace();
		}
	}

}
