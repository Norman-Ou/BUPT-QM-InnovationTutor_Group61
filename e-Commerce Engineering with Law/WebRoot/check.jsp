<%@ page language="java" contentType="text/html; charset=UTF-8"
 
    pageEncoding="UTF-8"%>
 
 <%@ page import="mywebproject.LoginDao"%>   
 
<!DOCTYPE html>
 
<html>
 
 <head>
 
  <meta charset="UTF-8">
 
  <title>Insert title here</title>
 
 </head>
 
<body>
 
<%
 
 request.setCharacterEncoding("utf-8");
response.setCharacterEncoding("utf-8");

 String name=request.getParameter("name");
 String pwd=request.getParameter("pwd");
 
 LoginDao dao=new LoginDao();
 
 boolean flag=dao.login(name,pwd);
 if(name.length() < 1 && pwd.length() < 1 )
	 flag=false;
 
 if(flag)
 
 {
	 
 
  %>

     <a href="./index.jsp">Login successfully!Click to enter.</a>
 
  <%
 
 }
 
 else
 
 {
 
  %>

   <a href="./login.jsp">Your information has something wrong.Click to go back.</a>
 
  <%
 
 }
 
 %>

 
</body>
 
</html>