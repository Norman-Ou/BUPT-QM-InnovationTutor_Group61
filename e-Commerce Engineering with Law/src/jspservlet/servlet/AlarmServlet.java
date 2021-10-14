package jspservlet.servlet;


import jspservlet.vo.Alarm;
import jspservlet.vo.UserLogin;

import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class AlarmServlet extends HttpServlet {

    public void doGet(HttpServletRequest req, HttpServletResponse res) {

    }

    public void doPost(HttpServletRequest req,HttpServletResponse res){
        String alarm;
        try {
            alarm = req.getParameter("alarm");
            res.setCharacterEncoding("utf-8");
            req.setCharacterEncoding("utf-8");
            if (alarm!=null){
                if (alarm.equals("1")) {
                    Alarm.clean();
                    UserLogin.alarm = 0;
                }
            }
            if (UserLogin.alarm == 1) {
                res.getWriter().print(1);
            }
            else{
                res.getWriter().print(0);
            }
        }
        catch (Exception e) {
            e.printStackTrace();
        }
    }

}
