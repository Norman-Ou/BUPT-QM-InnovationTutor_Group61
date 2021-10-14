<%@ page import="java.util.ArrayList" %>
<!DOCTYPE html>
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
	<body class="service_page">
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
										<li class="menu-item"><a href="./light">Light</a></li>
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
		<section class="deneb_breadcrumb bg_image" style="background-image: url(assets/images/breadcrumb_bg.jpg);">
			<div class="container">
				<div class="row">
					<div class="col-lg-12">
						<div class="breadcrumb_content">
							<h1>Our Devices</h1>
							<ul>
								<li><a href="index.html">Home</a></li>
								<li>Devices</li>
							</ul>
						</div>
					</div>
				</div>
			</div>
		</section><!-- End deneb_breadcrumb section -->
		<!-- Start deneb_service section -->	
		<section class="service_wrapper section_padding">
		<% String pt=(String)session.getAttribute("pt"); %>
			<div class="container">
				<div class="row">
					<div class="col-lg-12">
						<div class="service_tab_wrap">
							<div class="service_tab_nav">
								<ul class="nav nav-tabs">
									<% ArrayList<String> type=(ArrayList<String>)session.getAttribute("type");%>
									<% int j=0;%>
									<% String zz=" active";%>
									<% while (j<type.size()){ %>
									<li class="nav-item<%= zz%>">
										<a class="nav-link" data-toggle="tab" href="#w<%= j%>"><img src="assets/images/<%= pt %>/<%= j+1%>.jpg" alt="" width="80" height="80"><span><%= type.get(j)%></span></a>
									</li>
									<% zz="";j++;} %>
								</ul>
							</div>
							<!-- Tab panes -->
							<div class="tab-content">
								<% int i=0;%>
								<% zz=" active";%>
								<% while (i<type.size()){ %>
								<div id="w<%= i%>" class="tab-pane<%= zz%>">
									<div class="row align-items-center">
										<div class="col-lg-6">
											<div class="deneb_content_box">
												<h2><%= type.get(i)%></h2>
												<p>Etiam suscipit sed sem nec elementum. Interdum et malesuada fames ac ante ipsum primis in faucibus. Sed accumsan, urna vel finibus sollicitudin, urna sem fringilla nisi.</p>
												<p>In vel ante tristique, blandit nisi in, feugiat nisl. Nunc sagittis pretium arcu, ac consectetur diam feugiat vitae. Pellentesque sit amet elit facilisis, sodales nisl non, luctus ex. Donec molestie consequat velit, id imperdiet arcu sollicitudin.</p>
												<p>Praesent ac tempus ipsum, ege consequat ante. Sed consequat, elit euismod finibus faucibus.</p>
											</div>
										</div>
										<div class="col-lg-6">
											<div class="deneb_img_box">
												<img src="assets/images/service.jpg" class="img-fluid" alt="">
											</div>
										</div>
									</div>
									<form method="post" action="./device?action=add<%= pt %>&type=<%= type.get(i)%>&img=assets/images/<%= pt %>/<%= i+1%>.jpg">
										<p>type : </p><input type="text" name="type" value="<%= type.get(i)%>" readonly="true"/>
										<p>give a name : </p><input name="name" type="text"  placeholder="Give a name!" value="item" style="border-radius: 6px;border: 1px solid;">
										<p>where you put : </p><input name="position" type="text" placeholder="Where you put?" value="bedroom" style="border-radius: 6px;border: 1px solid;">
										</br><button>ADD</button>
									</form>
								</div>
								<% zz=" fade";i++;} %>
							</div>
							<!-- tab pane -->
						</div>
					</div>
				</div>
			</div>
		</section>
		<!-- End deneb_service section -->
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
									<li><a href="./light">Go Light</a></li>
									<li><a href="./humidifier">Go Humidifier</a></li>
									<li><a href="./door">Go Door</a></li>
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
	</body>
</html>