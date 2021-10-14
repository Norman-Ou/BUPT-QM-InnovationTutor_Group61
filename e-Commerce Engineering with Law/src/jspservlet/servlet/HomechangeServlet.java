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
import jspservlet.dao.impl.UserDAOImpl;
import jspservlet.vo.Home;
import jspservlet.vo.Member;
import jspservlet.vo.UserLogin;

public class HomechangeServlet extends HttpServlet{
    public void doGet(HttpServletRequest req, HttpServletResponse res)
            throws IOException, ServletException{


    }

    public void doPost(HttpServletRequest req, HttpServletResponse res)
            throws IOException, ServletException{

        int flag = 0;
        HttpSession session=req.getSession();

        //old home information
        Home home = new Home();

        home.setHomeID(UserLogin.homeID);
        home.setHomeName(req.getParameter("homeName"));
        home.setHomeAddress(req.getParameter("homeAdress"));

        //new home changes
        Home home1 = new Home();
        home1.setHomeID(req.getParameter("Nid"));
        home1.setHomePWD(req.getParameter("Npassword"));

        String newpassword = new String();
        newpassword = req.getParameter("newpassword");
        home.setHomePWD(req.getParameter("hpassword"));
        HomeDAO hdao = new HomeDAOImpl();





        int temp=0;
        int flag1=0;
        //if user want to change to another home
        if( (!home1.getHomePWD().equals("")) && (!home1.getHomeID().equals("")) ) {
            flag1=1;
            try {
                flag = hdao.anohomechange(home1);
                if(flag==1) {
                    temp=1;
                }
            }catch(SQLException e) {
                e.printStackTrace();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }


        //if user want to change password
        if(flag1==0) {
            if(!home.getHomePWD().equals("")) {
                try {
                    flag = hdao.Userhomechange(home, newpassword);

                }catch(SQLException e) {
                    e.printStackTrace();
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
            //overwrite
            if(flag==1 || home.getHomePWD().equals("")) {
                try {
                    flag = hdao.create(home);

                } catch (Exception e) {
                    e.printStackTrace();
                }
            }

        }


        if(flag==1) {
            if(temp==0) {
                try {
                    hdao.showHome(home.getHomeID());
                    home.setHomeName(hdao.showHome(home.getHomeID()).getHomeName());
                    home.setHomeAddress(hdao.showHome(home.getHomeID()).getHomeAddress());

                } catch (Exception e1) {
                    e1.printStackTrace();
                }
                session.setAttribute("userHomeID", home.getHomeID());
                session.setAttribute("homeName", home.getHomeName());
                session.setAttribute("homeAdress", home.getHomeAddress());


            }else {
                try {
                    System.out.println("home1:"+home1.getHomeID());
                    hdao.showHome(home1.getHomeID());
                    home1.setHomeName(hdao.showHome(home1.getHomeID()).getHomeName());
                    home1.setHomeAddress(hdao.showHome(home1.getHomeID()).getHomeAddress());

                } catch (Exception e1) {
                    e1.printStackTrace();
                }

                session.setAttribute("userHomeID", home1.getHomeID());
                session.setAttribute("homeName", home1.getHomeName());
                session.setAttribute("homeAdress", home1.getHomeAddress());
            }

            req.getRequestDispatcher("./show.jsp").forward(req, res);
        }else {
            res.sendRedirect("./portfolio.jsp");
        }


    }
}
