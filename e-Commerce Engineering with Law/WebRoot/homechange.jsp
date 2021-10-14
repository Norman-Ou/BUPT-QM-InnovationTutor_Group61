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
	<!-- slick fader css -->
	<link rel="stylesheet" href="assets/css/slick.css">
	<link rel="stylesheet" href="assets/css/slick-theme.css">
	<!-- sidebar-menu -->
	<link rel="stylesheet" href="assets/css/sidebar-menu.css">
	<!--animate css-->
	<link rel="stylesheet" href="assets/css/animate.css">
	<!--style css-->
	<link rel="stylesheet" href="assets/css/style.css">
</head>
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
					<div class="primary_menu" id="menu">
						<nav class="main_menu">
							<ul>
								<li class="menu-item active_link"><a href="index.jsp">Home</a></li>
								<li class="menu-item"><a href="./light">Light</a></li>
								<li class="menu-item"><a href="./humidifier">Humidifier</a></li>
								<li class="menu-item"><a href="./door">Door</a></li>
								<li class="menu-item"><a href="./window">Window</a></li>
								<li class="menu-item"><a href="show.jsp" class="deneb_btn">Show Info</a></li>
							</ul>
						</nav>
					</div>
				</div>
			</div>
		</div>
	</div>
</header><!-- End header_area -->
<!-- Start deneb_breadcrumb section -->
<section class="deneb_breadcrumb bg_image" style="background-image: url(assets/images/breadcrumb_bg.jpg);">
	<div class="container">
		<div class="row">
			<div class="col-lg-12">
				<div class="breadcrumb_content">
					<h1>Home Information</h1>
					<ul>
						<li><a href="index.jsp">Home</a></li>
						<li>MY</li>
					</ul>
				</div>
			</div>
		</div>
	</div>
</section><!-- End deneb_breadcrumb section -->
<!-- Start deneb_contact section -->
<section class="deneb_contact cantact_v2 section_padding">
	<div class="container">
		<div class="row">
			<div class="col-lg-12">
				<div class="section_title">
					<h2 class="text-left">Home Information</h2>
					<p>Please complete the following information to better enjoy your intelligent home system&nbspModify your information below;</p>
				</div>
				<div class="contact_form">
					<form method="post" action="homechange">
						<div class="row">

							<div class="col-lg-6">
								<div class="form_group">
									<% String homeName = (String)session.getAttribute("homeName"); %>
									HomeName
									<input type="text" class="form_control" name="homeName"  value = <%= homeName %>   required>
								</div>
							</div>
							<div class="col-lg-6">
								<div class="form_group">
									<% String homeAdress = (String)session.getAttribute("homeAdress"); %>
									HomeAdress
									<input type="text" class="form_control"  name="homeAdress" value= <%= homeAdress %>  required>
								</div>
							</div>
							<div class="col-lg-12">
								<div class="form_group">
									*If you do not want to change your password, please ignore the "Old Password" and "New Password" options.<br>
									<p>
										Home Password
										<input type="text" class="form_control"  name="hpassword" >
								</div>
							</div>


							<div class="col-lg-12">
								<div class="form_group">
									New Password
									<input type="text" class="form_control"  name="newpassword">

								</div>
							</div>

							<div class="col-lg-12">
								<div class="form_group">
									<h3>Change to another home.</h3><br>
									<p>
										Home id
										<input type="text" class="form_control"  name="Nid" >
								</div>
							</div>

							<div class="col-lg-12">
								<div class="form_group">
									Change to another home.<br>
									<p>
										New Home password
										<input type="text" class="form_control"  name="Npassword" >
								</div>
							</div>

							<div class="col-lg-12">
								<!-- form method="post" action="./change"> -->
								<input type="SUBMIT" name="submit" value="Submit Now">



							</div>

					</form>
				</div>

			</div>
		</div>
	</div>
	</div>
</section>
<!-- End deneb_contact section -->

<!-- Start deneb_cta -->
<section class="deneb_cta">
	<div class="container">
		<div class="cta_wrapper">
			<div class="row align-items-center">
				<div class="col-lg-7">
					<div class="cta_content">
						<h3>Have Any Suggestion?</h3>
						<p>If you have any suggestions of our produc, just email to 123456@12.com. We would be very appreciated to receive your feedback!</p>
					</div>
				</div>
				<div class="col-lg-5">
					<div class="button_box">
						<a href="#" class="deneb_btn">TELL Us</a>
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
							<img src="assets/images/logo_1.png" class="img-fluid" alt="">
						</div>
						<p>Quisque orci nisl, viverra et sem ac, tincidunt egestas massa. Morbi est arcu, hendrerit ac vehicula condimentum, euismod nec tortor praesent consequat urna.</p>
						<ul class="social">
							<li><a href="#"><i class="fab fa-facebook-f"></i></a></li>
							<li><a href="#"><i class="fab fa-twitter"></i></a></li>
							<li><a href="#"><i class="fab fa-instagram"></i></a></li>
						</ul>
					</div>
				</div>
				<div class="col-lg-4 col-md-6 col-sm-12">
					<div class="widget widget_link">
						<div class="widget_title">
							<h4>Links</h4>
						</div>
						<ul>
							<li><a href="#">About Us</a></li>
							<li><a href="#">Services</a></li>
							<li><a href="#">Portfolio</a></li>
							<li><a href="#">Blog</a></li>
						</ul>
					</div>
				</div>
				<div class="col-lg-4 col-md-6 col-sm-12">
					<div class="widget widget_contact">
						<div class="widget_title">
							<h4>Contact Us</h4>
						</div>
						<div class="contact_info">
							<div class="single_info">
								<div class="icon">
									<i class="fas fa-phone-alt"></i>
								</div>
								<div class="info">
									<p><a href="tel:+919246147999">1800-121-3637</a></p>
									<p><a href="tel:+919246147999">+91 924-614-7999</a></p>
								</div>
							</div>
							<div class="single_info">
								<div class="icon">
									<i class="fas fa-envelope"></i>
								</div>
								<div class="info">
									<p><a href="mailto:info@deneb.com">info@deneb.com</a></p>
									<p><a href="mailto:services@deneb.com">services@deneb.com</a></p>
								</div>
							</div>
							<div class="single_info">
								<div class="icon">
									<i class="fas fa-map-marker-alt"></i>
								</div>
								<div class="info">
									<p>125, Park street aven, Brocklyn,<span>Newyork.</span></p>
								</div>
							</div>
						</div>
					</div>
				</div>
			</div>
		</div>
	</div>
	<div class="copyright_area">
		<div class="container">
			<div class="row">
				<div class="col-lg-12">
					<div class="copyright_text">
						<p>Copyright &copy; 2019 <span>Deneb</span>. All rights reserved.</p>
					</div>
				</div>
			</div>
		</div>
	</div>
</footer><!-- End footer -->
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
</html>