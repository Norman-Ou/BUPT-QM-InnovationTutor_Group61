package jspservlet.servlet;

import jspservlet.vo.Door;
import jspservlet.vo.Window;
import jspservlet.vo.Humidifier;
import jspservlet.vo.Light;

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

import java.util.ArrayList;

public class DeviceServlet extends HttpServlet {
	
	public void doGet(HttpServletRequest req,HttpServletResponse res) {
		HttpSession session=req.getSession();
		String judge=req.getParameter("action");
		try {
		if (judge.equals("light")) {
			LightDAO dao=new LightDAOImpl();
			ArrayList<String> ltype=new ArrayList<>();
			ltype=dao.allLightType();
			session.setAttribute("pt","light");
			session.setAttribute("type",ltype);
			res.sendRedirect("./device.jsp");
		}
		if (judge.equals("humidifier")) {
			HumidifierDAO dao=new HumidifierDAOImpl();
			ArrayList<String> htype=new ArrayList<>();
			htype=dao.allHumidifierType();
			session.setAttribute("pt","humidifier");
			session.setAttribute("type",htype);
			res.sendRedirect("./device.jsp");
		}
		if (judge.equals("door")) {
			DoorDAO dao=new DoorDAOImpl();
			ArrayList<String> dtype=new ArrayList<>();
			dtype=dao.allDoorType();
			session.setAttribute("pt","door");
			session.setAttribute("type",dtype);
			res.sendRedirect("./device.jsp");
		}
		if (judge.equals("window")) {
			WindowDAO dao=new WindowDAOImpl();
			ArrayList<String> wtype=new ArrayList<>();
			wtype=dao.allWindowType();
			session.setAttribute("pt","window");
			session.setAttribute("type",wtype);
			res.sendRedirect("./device.jsp");
		}
		}
		
		catch(Exception e){
			e.printStackTrace();
		}
	}
	
	public void doPost(HttpServletRequest req,HttpServletResponse res){
		HttpSession session=req.getSession();
		String judge=req.getParameter("action");
		try {
			if (judge.equals("addlight")) {
				Light Alight=new Light();
				Alight.setType(req.getParameter("type"));
				Alight.setImg(req.getParameter("img"));
				Alight.setName(req.getParameter("name"));
				Alight.setPosition(req.getParameter("position"));
				LightDAO dao=new LightDAOImpl();
				dao.addlight(Alight);
				res.sendRedirect("./light");
			}
			if (judge.equals("addhumidifier")) {
				Humidifier Ahumidifier=new Humidifier();
				Ahumidifier.setType(req.getParameter("type"));
				Ahumidifier.setImg(req.getParameter("img"));
				Ahumidifier.setName(req.getParameter("name"));
				Ahumidifier.setPosition(req.getParameter("position"));
				HumidifierDAO dao=new HumidifierDAOImpl();
				dao.addhumidifier(Ahumidifier);
				res.sendRedirect("./humidifier");
			}
			if (judge.equals("adddoor")) {
				Door Adoor=new Door();
				Adoor.setType(req.getParameter("type"));
				Adoor.setImg(req.getParameter("img"));
				Adoor.setName(req.getParameter("name"));
				Adoor.setPosition(req.getParameter("position"));
				DoorDAO dao=new DoorDAOImpl();
				dao.adddoor(Adoor);
				res.sendRedirect("./door");
			}
			if (judge.equals("addwindow")) {
				Window Awindow=new Window();
				Awindow.setType(req.getParameter("type"));
				Awindow.setImg(req.getParameter("img"));
				Awindow.setName(req.getParameter("name"));
				Awindow.setPosition(req.getParameter("position"));
				WindowDAO dao=new WindowDAOImpl();
				dao.addwindow(Awindow);
				res.sendRedirect("./window");
			}
		}

		catch(Exception e){
			e.printStackTrace();
		}
	}
	
}