<!DOCTYPE html>
<%@ page import="java.util.ArrayList" %>
<%@ page import="jspservlet.vo.Light" %>
<%@ page import="java.text.SimpleDateFormat" %>
<%@ page import="java.util.Date" %>
<html lang="en">
<head><meta http-equiv="Content-Type" content="text/html; charset=utf-8">
	<!-- All Meta -->
	<meta http-equiv="x-ua-compatible" content="ie=edge">
	<meta name="description" content="">
	<meta name="viewport" content="width=device-width, initial-scale=1">
	<!-- page title -->
	<title>Deneb - A Digital Agency HTML Template</title>
	<!-- Favicon Icon -->
	<link rel="shortcut icon" href="assets/images/favicon.ico" type="image/png">
	<!-- All css -->
	<!--Bootstrap css-->
	<link rel="stylesheet" href="assets/css/bootstrap.min.css">
	<!-- Fontawesome css -->
	<link rel="stylesheet" href="assets/fonts/fontawesome/css/all.css">
	<!-- slick slider css -->
	<link rel="stylesheet" href="assets/css/slick.css">
	<link rel="stylesheet" href="assets/css/slick-theme.css">
	<!-- sidebar-menu -->
	<link rel="stylesheet" href="assets/css/sidebar-menu.css">
	<!--animate css-->
	<link rel="stylesheet" href="assets/css/animate.css">
	<!--style css-->
	<link rel="stylesheet" href="assets/css/style.css">
</head>
<% ArrayList<Light> l = (ArrayList<Light>)session.getAttribute("linfo"); %>
<body>
<!-- Preloader -->
<div class="preloader">
	<div class="lds-ripple">
		<div></div>
		<div></div>
	</div>
</div>
<!-- Start header_area -->
<header class="header_area header_v1 transparent_header">
	<div class="container">
		<div class="site_menu">
			<div class="row align-items-center">
				<div class="col-lg-2">
					<div class="brand">
						<a href="#" class="logo"><img src="assets/images/logo_1.png" class="img-fluid" alt=""></a>
					</div>
				</div>
				<div class="col-lg-10">
					<div class="primary_menu">
						<nav class="main_menu">
							<ul>
								<li class="menu-item"><a href="index.jsp">Home</a></li>
								<li class="menu-item active_link"><a href="./light">Light</a></li>
								<li class="menu-item"><a href="./humidifier">Humidifier</a></li>
								<li class="menu-item"><a href="./door">Door</a></li>
								<li class="menu-item"><a href="./window">Window</a></li>
								<li class="menu-item"><a href="./history.jsp"target="_blank">History</a></li>
								<li class="menu-item"><a href="./show" class="deneb_btn">Show Info</a></li>
							</ul>
						</nav>
					</div>
				</div>
			</div>
		</div>
	</div>
</header>
<!-- End header_area -->
<!-- Start deneb_breadcrumb section -->
<section class="deneb_breadcrumb bg_image" style="background-image: url(assets/images/light_bg.jpg);">
	<div class="container">
		<div class="row">
			<div class="col-lg-12">
				<div class="breadcrumb_content">
					<h1>Light Controller</h1>
					<ul>
						<li><a href="index.jsp">Home</a></li>
						<li>Light</li>
					</ul>
				</div>
			</div>
		</div>
	</div>
</section><!-- End deneb_breadcrumb section -->
<!-- Start deneb_project section -->
<section class="deneb_project project_v2 section_padding" id="project">
	<div class="container">
		<div class="row">
			<div class="col-lg-12">
				<div class="section_title text-center">
					<h2>Lights in your home</h2>
					<p>You can observe and control your device in the following section</p>
				</div>
			</div>
		</div>
		<div class="row">
			<div class="col-lg-12">
				<div class="project_button_2 text-center">
					<button class="project_btn active_btn" data-filter="*">all</button>
					<% ArrayList<String> type = (ArrayList<String>)session.getAttribute("ltype"); %>
					<% int j=0;%>
					<% while (j<type.size()){ %>
					<button class="project_btn" data-filter=".cat<%=j+1%>"><%= type.get(j)%></button>
					<% j++;} %>
				</div>
			</div>
		</div>
		<div class="row grid_wrapper">
			<% int i=0;%>
			<% while (i<l.size()){ %>
			<% Light k=l.get(i); %>
			<% String cl=k.getType(); %>
			<% for (j=0;j<type.size();j++){ %>
			<% String t=type.get(j); %>
			<% if (cl.equals(t)){ %>
			<div class= "col-lg-4 col-md-6 col-sm-12 single_project cat<%=j+1%>">
				<div class="grid_item">
					<div class="deneb_img">
						<img src=<%= l.get(i).getImg() %> class="img-fluid" alt="">
					</div>
					<div class="deneb_info">
						<h4><a href="./inf?action=light&id=<%=k.getId() %>"><%= l.get(i).getName() %></a></h4>
						<p id="br<%=i%>"><l<%=i%>><%= l.get(i).getState() %></l<%=i%>></p>
					</div>
				</div>
			</div>
			<% }}i++;} %>
		</div>
		<div class="row">
			<div class="col-lg-12">
				<div class="deneb_pagination text-center">
					<ul>
						<li class="prev"><a href="./device?action=light">Add</a></li>
					</ul>
				</div>
			</div>
		</div>
	</div>
</section>
<!-- End deneb_project section -->
<!-- Start deneb_cta -->
<section class="deneb_cta">
	<div class="container">
		<div class="cta_wrapper">
			<div class="row align-items-center">
				<div class="col-lg-7">
					<div class="cta_content">
						<h3>Have Any Good Suggestions ?</h3>
						<p>Device optimization, device failure, control failure, adding object failure, and so on.</p>
					</div>
				</div>
				<div class="col-lg-5">
					<div class="button_box">
						<a href="contact.html" class="deneb_btn">Contact Us</a>
					</div>
				</div>
			</div>
		</div>
	</div>
</section>
<!-- End deneb_cta section -->
<!-- Start footer -->
<footer class="deneb_footer">
	<div class="widget_wrapper" style="background-image: url(assets/images/footer_bg.png);">
		<div class="container">
			<div class="row">
				<div class="col-lg-4 col-md-6 col-12">
					<div class="widget widegt_about">
						<div class="widget_title">
							<img src="assets/images/logo_1.png" class="img-fluid" height=40px width=200px alt="">
						</div>
						<p>Dream Home will provide you with intelligent home services, so that your family can monitor the equipment in the home, further can control and adjust the state of the equipment.</p>
						<ul class="social">
							<li><a></a></li>
							<li><a></a></li>
							<li><a></a></li>
						</ul>
					</div>
				</div>
				<div class="col-lg-3 col-md-6 col-sm-12">
					<div class="widget widget_link">
						<div class="widget_title">
							<h4>Links</h4>
						</div>
						<ul>
							<li><a href="#">Go Top</a></li>
							<li><a href="./door">Go Door</a></li>
							<li><a href="./humidifier">Go Humidifier</a></li>
							<li><a href="./window">Go Window</a></li>
						</ul>
					</div>
				</div>
				<div class="col-lg-3 col-md-6 col-sm-12">
					<div class="widget widget_contact">
						<div class="widget_title">
							<h4>Contact Us</h4>
						</div>
						<div class="contact_info">
							<div class="single_info">
								<div class="icon">
									<img src="assets/images/i1.png" height=20px width=20px alt="">
								</div>
								<div class="info">
									<p>654321</p>
									<p>123456</p>
								</div>
							</div>
							<div class="single_info">
								<div class="icon">
									<img src="assets/images/i2.png" height=20px width=20px alt="">
								</div>
								<div class="info">
									<p>info@deneb.com</p>
									<p>services@deneb.com</p>
								</div>
							</div>
							<div class="single_info">
								<div class="icon">
									<img src="assets/images/i3.png" height=20px width=20px alt="">
								</div>
								<div class="info">
									<p>info@deneb.com</p>
									<p>services@deneb.com</p>
								</div>
							</div>
						</div>
					</div>
				</div>
			</div>
		</div>
	</div>
</footer>
<!-- End footer -->
<!-- jquery  -->
<script src="assets/js/vendor/jquery-1.12.4.min.js"></script>
<!--modernizr js-->
<script src="assets/js/vendor/modernizr-3.6.0.min.js"></script>
<!-- Bootstrap js  -->
<script src="assets/js/vendor/bootstrap.min.js"></script>
<script src="assets/js/vendor/popper.min.js"></script>
<!-- slick slider js  -->
<script src="assets/js/vendor/slick.min.js"></script>
<!-- isotope js  -->
<script src="assets/js/vendor/isotope.min.js"></script>
<!-- imageloaded js-->
<script src="assets/js/vendor/imagesloaded.min.js"></script>
<!--sidebar js-->
<script src="assets/js/vendor/sidebar-menu.js"></script>
<!--wow js-->
<script src="assets/js/vendor/wow.min.js"></script>
<!-- custom js  -->
<script src="assets/js/custom.js"></script>
</body>
<script type="text/javascript">
	var XMLHttpReq;
	var i=0;
	//创建XMLHttpRequest对象
	function createXMLHttpRequest() {
		if(window.XMLHttpRequest) { //Mozilla 浏览器
			XMLHttpReq = new XMLHttpRequest();
		}
		else if (window.ActiveXObject) { // IE浏览器
			try {
				XMLHttpReq = new ActiveXObject("Msxml2.XMLHTTP");
			} catch (e) {
				try {
					XMLHttpReq = new ActiveXObject("Microsoft.XMLHTTP");
				} catch (e) {}
			}
		}
	}
	//发送请求函数
	function sendRequest() {
		createXMLHttpRequest();
		var url = "./light";
		XMLHttpReq.open("GET", url, true);
		XMLHttpReq.onreadystatechange = processResponse;//指定响应函数
		XMLHttpReq.send(null);  // 发送请求
	}
	// 处理返回信息函数
	function processResponse() {
		if (XMLHttpReq.readyState == 4) { // 判断对象状态
			if (XMLHttpReq.status == 200) { // 信息已经成功返回，开始处理信息

				<% j=0; %>
				<% while (j<l.size()){%>
				var page=XMLHttpReq.responseText;

				var first=page.indexOf("<l<%=j%>>");
				//var tag=page.indexOf("<l<%=j%>>",first+100);
				var begin=page.indexOf(">",first);
				var end=page.indexOf("</l<%=j%>>",first);
				var want=page.substring(begin+1,end);
				document.getElementById("br<%=j%>").innerHTML="brightness: "+want;
				<% j++;}%>

			}
		}
	}

	function autorun(){
		sendRequest();
		i++;
	}
	setInterval("autorun()",1000);
</script>
<script type="text/javascript">
	var x=0;
	var judge=0;
	setInterval(function () {
		$.ajax({
			type:"post",
			async:true,
			url:"./alarm",
			data:{alarm:x},
			datatype:"json",
			contentType:"application/x-www-form-urlencoded;utf-8",
			success:function (result){
				x=0;
				if (result==="1"&&judge===0){
					var time = Date.now();
					var z = new Date(time);
					var month=z.getMonth()+1;
					var d = " "+z.getFullYear()+"-"+month+"-"+z.getDate()+" "+z.getHours()+":"+z.getMinutes()+":"+z.getSeconds();
					var alarm=confirm("Alarm!"+d);
					judge=1;
					if (alarm==true){
						x=1;
						judge=0;
					}
					else{
						judge=0;
					}
				}
			}
		})
	}, 5000);
</script>
</html>