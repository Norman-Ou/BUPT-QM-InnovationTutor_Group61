package jspservlet.servlet;

import java.util.ArrayList;

import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import jspservlet.dao.DoorDAO;
import jspservlet.dao.HumidifierDAO;
import jspservlet.dao.LightDAO;
import jspservlet.dao.WindowDAO;
import jspservlet.dao.impl.DoorDAOImpl;
import jspservlet.dao.impl.HumidifierDAOImpl;
import jspservlet.dao.impl.LightDAOImpl;
import jspservlet.dao.impl.WindowDAOImpl;
import jspservlet.vo.Door;
import jspservlet.vo.Humidifier;
import jspservlet.vo.Light;
import jspservlet.vo.Window;

public class InfServlet extends HttpServlet{

	public void doGet(HttpServletRequest req,HttpServletResponse res) {
		try {
			String judge=req.getParameter("action");
			String id=req.getParameter("id");

			if (judge.equals("light")) {
				LightDAO dao=new LightDAOImpl();
				HttpSession session=req.getSession();
				Light Alight=dao.queryLight(id);
				session.setAttribute("linf", Alight);
				session.setAttribute("judge", judge);
				res.sendRedirect("./inf.jsp");
			}

			if (judge.equals("humidifier")) {
				HumidifierDAO dao=new HumidifierDAOImpl();
				HttpSession session=req.getSession();
				Humidifier Ahum =dao.queryHumidifier(id);
				session.setAttribute("hinf", Ahum);
				session.setAttribute("judge", judge);
				res.sendRedirect("./inf.jsp");
			}

			if (judge.equals("door")) {
				DoorDAO dao=new DoorDAOImpl();
				HttpSession session=req.getSession();
				Door Adoor =dao.queryDoor(id);
				session.setAttribute("dinf", Adoor);
				session.setAttribute("judge", judge);
				res.sendRedirect("./inf.jsp");
			}

			if (judge.equals("window")) {
				WindowDAO dao=new WindowDAOImpl();
				HttpSession session=req.getSession();
				Window Awin =dao.queryWindow(id);
				session.setAttribute("winf", Awin);
				session.setAttribute("judge", judge);
				res.sendRedirect("./inf.jsp");
			}


		}
		catch(Exception e){
			e.printStackTrace();
		}
	}

	public void doPost(HttpServletRequest req,HttpServletResponse res){
	}
}
