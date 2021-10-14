package jspservlet.servlet;

import java.util.ArrayList;

import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import jspservlet.dao.HumidifierDAO;
import jspservlet.dao.LightDAO;
import jspservlet.dao.impl.HumidifierDAOImpl;
import jspservlet.dao.impl.LightDAOImpl;
import jspservlet.vo.Humidifier;

public class HumidifierServlet extends HttpServlet{
	
	public void doGet(HttpServletRequest req,HttpServletResponse res) {
		HumidifierDAO dao=new HumidifierDAOImpl();
		HttpSession session=req.getSession();
		ArrayList<Humidifier> huinfo=new ArrayList<Humidifier>();
		ArrayList<String> htype=new ArrayList<>();
		try {
			res.setContentType("text/html;charset=utf-8");
			req.setCharacterEncoding("utf-8");
			huinfo=dao.allHumidifierid();
			htype=dao.allHumidifierType();
			session.setAttribute("huinfo", huinfo);
			session.setAttribute("htype",htype);
			req.getRequestDispatcher("./humidifier.jsp").forward(req,res);
		}
		catch(Exception e){
			e.printStackTrace();
		}
	}
	
	public void doPost(HttpServletRequest req,HttpServletResponse res){
		String temperature;
		String humidity;
		String id;
		String result;
		try {
			HumidifierDAO dao=new HumidifierDAOImpl();
			req.setCharacterEncoding("utf-8");
			id=req.getParameter("id");
			temperature=dao.queryHumidifier(id).getTemperature();
			humidity=dao.queryHumidifier(id).getHumidity();
			result="{\"temperature\":\""+temperature+"\",\"humidity\":\""+humidity+"\"}";
			res.setCharacterEncoding("utf-8");
			res.getWriter().print(result);
		}
		catch(Exception e){
			e.printStackTrace();
		}
	}
	
}
