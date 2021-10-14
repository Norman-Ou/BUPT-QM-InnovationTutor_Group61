package jspservlet.servlet;

import java.util.ArrayList;
import javax.servlet.http.HttpServlet;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import jspservlet.dao.DoorDAO;
import jspservlet.dao.HumidifierDAO;
import jspservlet.dao.impl.HumidifierDAOImpl;
import jspservlet.vo.Door;
import jspservlet.dao.impl.DoorDAOImpl;

public class DoorServlet extends HttpServlet {
	
	public void doGet(HttpServletRequest req,HttpServletResponse res) {
		DoorDAO dao=new DoorDAOImpl();
		HttpSession session=req.getSession();
		ArrayList<Door> doorinfo=new ArrayList<Door>();
		ArrayList<String> dtype=new ArrayList<>();
		try {
			doorinfo=dao.allDoorid();
			dtype=dao.allDoorType();
			session.setAttribute("dinfo", doorinfo);
			session.setAttribute("dtype",dtype);
			req.getRequestDispatcher("./door.jsp").forward(req,res);
		}
		catch(Exception e){
			e.printStackTrace();
		}
	}
	
	public void doPost(HttpServletRequest req,HttpServletResponse res){
		String distance;
		String id;
		String result;
		try {
			DoorDAO dao=new DoorDAOImpl();
			req.setCharacterEncoding("utf-8");
			id=req.getParameter("id");
			distance=dao.queryDoor(id).getDistance();
			result="{\"distan\":\""+distance+"\"}";
			res.setCharacterEncoding("utf-8");
			res.getWriter().print(result);
		}
		catch(Exception e){
			e.printStackTrace();
		}
	}
	
}
