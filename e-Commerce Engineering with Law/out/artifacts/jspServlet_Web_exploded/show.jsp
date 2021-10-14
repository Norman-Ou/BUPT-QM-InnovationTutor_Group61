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
                    <h1>Personal Information</h1>
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
                    <h2 class="text-left">Personal Information</h2>
                    <p>Your personal information are as follows.           &nbsp;</p>
                </div>
                <div class="contact_form">
                    <form>
                        <div class="row">
                            <div class="col-lg-12">
                                <div class="form_group">
                                    <% String usernamee = (String)session.getAttribute("userNamee"); %>
                                    Name: <%= usernamee %>


                                </div>
                            </div>

                            <div class="col-lg-12">
                                <div class="form_group">
                                    <% String userGender = (String)session.getAttribute("userGender"); %>
                                    Gender: <%= userGender %>
                                </div>
                            </div>
                            <div class="col-lg-12">
                                <div class="form_group">
                                    <% String username = (String)session.getAttribute("username"); %>
                                    MemberID:  <%= username %>
                                </div>
                            </div>

                            <div class="col-lg-12">
                                <div class="form_group">
                                    Password : ********
                                </div>
                            </div>

                            <div class="col-lg-12">
                                <div class="form_group">
                                    <% String userPhone = (String)session.getAttribute("userPhone"); %>

                                    Phone Number:  <%= userPhone %>


                                </div>

                            </div>

                            <div class="col-lg-12">
                                <% String userHomeID = (String)session.getAttribute("userHomeID"); %>
                                HomeID: <%= userHomeID %>
                                <div class="form_group"> </div>
                            </div>

                            <div class="col-lg-12">
                                <div class="form_group">
                                    <% String homeName = (String)session.getAttribute("homeName"); %>
                                    HomeName: <%= homeName %>
                                </div>
                            </div>


                            <div class="col-lg-12">
                                <div class="form_group">
                                    <% String homeAdress = (String)session.getAttribute("homeAdress"); %>
                                    HomeAdress: <%= homeAdress %>
                                </div>
                            </div>



                            <div class="col-lg-6">
                                <div class="button_box">

                                    <li class="deneb_btn"><a href="./change.jsp">Modify</a></li>
                                </div>
                            </div>

                            <div class="col-lg-6">
                                <div class="button_box">

                                    <li class="deneb_btn"><a href="./homechange.jsp">Modify home</a></li>
                                </div>
                            </div>
                            <div class="col-lg-12">

                            </div>
                        </div>
                    </form>
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
                        <a href="login.jsp" class="deneb_btn">Log Out</a>
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