<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>
<%@page import="jspservlet.dao.impl.RegisterDao"%>
<!DOCTYPE html>
<html>
 <head>
  <meta charset="UTF-8">
  <title>Warning</title>
 </head>
<body>
<%
 RegisterDao re=new RegisterDao();
 request.setCharacterEncoding("utf-8");
 String rname=request.getParameter("rname");
 String rpwd=request.getParameter("rpwd");
 String rpwd1=request.getParameter("rpwd1");
 String gender=request.getParameter("gender");
 String phone=request.getParameter("phone");
 int count=re.register(rname,rpwd,rpwd1,gender,phone);
if(rname.length() >= 1 && rpwd.length() >= 1 && rpwd.equals(rpwd1))
 {
    %>
  <a href="login.jsp">You have registered successfully!Click to login. </a>
  <%
 }
 else
 {
  %>
  <a href="login.jsp">Your information has something wrong.</a>
  <%
 
 }
 
%>
</body>
</html>