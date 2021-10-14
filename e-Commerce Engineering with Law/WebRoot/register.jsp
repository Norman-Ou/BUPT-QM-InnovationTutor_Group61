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
    <title>HOME REGISTEER</title>
    <link rel="stylesheet" href="./style.css">
</head>
<body>
<script  src="https://code.jquery.com/jquery-3.1.1.min.js"  integrity="sha256-hVVnYaiADRTO2PzUGmuLJr8BLUSjGIZsDYGmIJLv2b8="  crossorigin="anonymous"></script>
<script src="https://code.jquery.com/ui/1.12.1/jquery-ui.min.js" ></script>
<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap.min.css" integrity="sha384-BVYiiSIFeK1dGmJRAkycuHAHRg32OmUcww7on3RYdg4Va+PmSTsz/K68vbdEjh4u" crossorigin="anonymous">

<%
    String rname = (String)session.getAttribute("rname");
    String rpwd = (String)session.getAttribute("rpwd");
    String rpwd1 = (String)session.getAttribute("rpwd1");
%>
<%
    if(rname.length() >= 1 && rpwd.length() >= 1 && rpwd.equals(rpwd1))
    {
%>

<div class="body">
    <div class="veen">
        <div class="login-btn splits">
            <p>Join the home</p>
            <button class="active">Join</button>
        </div>
        <div class="rgstr-btn splits">
            <p>Create a home</p>
            <button>Create</button>
        </div>
        <div class="wrapper">

            <form id="login" tabindex="500" method="post" action="./change">
                <h3>Join</h3>
                <div class="name">

                    <input type="text" class="form_control"  name="Ahomeid"   required>
                    <label>HomeID</label>
                </div>
                <div class="passwd">
                    <input type="text" class="form_control"  name="Nhpassword"    required>
                    <label>Home Password</label>
                </div>
                <div class="submit">
                    <button class="dark">Join</button>
                </div>
            </form>

            <form  id="register" tabindex="502" method="post" action="./change">	<h3>Create</h3>
                <div class="name">
                    <input type="text" class="form_control" name="NhomeName"    required>
                    <label>Home Name</label>
                </div>
                <div class="phone">
                    <input type="text" class="form_control"  name="NhomeAdress"   required>
                    <label>Home Address</label>
                </div>

                <div class="passwd">
                    <input  type="password"  class="form_control"  name="Nhpassword"    required>
                    <label>Home Password</label>
                </div>


                <div class="passwd1">
                    <input type="password"  class="form_control"  name="RNhpassword"    required>
                    <label>Confirm Your Password</label>
                </div>
                <div class="submit">
                    <button class="dark">Create</button>
                </div>

            </form>
        </div>
    </div>
</div>




<a href="index.jsp">You have registered successfully!Click to login. </a>
<%
}
else
{
%>
<a href="portfolio.jsp">Your information has something wrong.</a>
<%

    }

%>

<style type="text/css">
    .site-link{
        padding: 5px 15px;
        position: fixed;
        z-index: 99999;
        background: #fff;
        box-shadow: 0 0 4px rgba(0,0,0,.14), 0 4px 8px rgba(0,0,0,.28);
        right: 30px;
        bottom: 30px;
        border-radius: 10px;
    }
    .site-link img{
        width: 30px;
        height: 30px;
    }
</style>
<!-- partial -->
<script  src="./script.js"></script>

</body>
</html>