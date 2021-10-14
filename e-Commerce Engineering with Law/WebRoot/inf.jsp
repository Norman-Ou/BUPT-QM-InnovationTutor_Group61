<!DOCTYPE html>
<%@ page import="jspservlet.vo.Light" %>
<%@ page import="jspservlet.vo.Humidifier" %>
<%@ page import="jspservlet.vo.Door" %>
<%@ page import="jspservlet.vo.Window" %>

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

	<link rel="stylesheet" type="text/css" href="assets/css/default.css">
	<link rel="stylesheet" type="text/css" href="assets/css/styles.css">
	<script src="https://cdn.jsdelivr.net/npm/echarts@5.2.1/dist/echarts.min.js"></script>
	<script type="text/javascript" src="./jquery-easyui-1.7.0/jquery.min.js" charset="utf-8"></script>
	<script type="text/javascript" src="./jquery-easyui-1.7.0/jquery.easyui.min.js" charset="utf-8"></script>
	<link rel="stylesheet" type="text/css" href="./jquery-easyui-1.7.0/themes/default/easyui.css">
	<link rel="stylesheet" type="text/css" href="./jquery-easyui-1.7.0/themes/icon.css">
	<script type="text/javascript" src="./jquery-easyui-1.7.0/locale/easyui-lang-en.js"></script>

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
								<li class="menu-item"><a href="index">Home</a></li>
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
</header>
<!-- End header_area -->
<!-- Start deneb_breadcrumb section -->
<section class="deneb_breadcrumb bg_image" style="background-image: url(assets/images/breadcrumb_bg.jpg);">
	<div class="container">
		<div class="row">
			<div class="col-lg-12">
				<div class="breadcrumb_content">
					<h1>Device Statement</h1>
					<ul>
						<li><a href="index.jsp">Home</a></li>
						<li>Devices</li>
					</ul>
				</div>
			</div>
		</div>
	</div>
</section><!-- End deneb_breadcrumb section -->
<% String action = (String)session.getAttribute("judge"); %>
<% if(action!=null){%>
<% if(action.equals("light")) {%>
<% Light li= (Light)session.getAttribute("linf"); %>
<section class="deneb_contact cantact_v2 section_padding">
	<div class="container">
		<div class="row">
			<div class="col-lg-12">
				<div class="breadcrumb_content">
					<div class="tab-content">
						<div id="web" class="tab-pane active">
							<div class="row align-items-center">
								<div class="col-lg-12">
									<div class="deneb_content_box" >
										<form method="post" action="./light">
											<div class="widget widget_link">
												<div class="widget_title">
													<h2><%= li.getName() %></h2>
													<style>
														div{text-align:center}
													</style>
													<p>id: <%= li.getId() %></p>
													<p>state: <%= li.getState() %></p>
												</div>
											</div>
											<%    session.setAttribute("idtest",  li.getId());  %>
											<input type='range' id='range1' name='range1' class='tip fill' value='<%= li.getState() %>' step='1' min='0' max='9' onchange='b.value=this.value' />
											<output id="b" for="range1"></output>
											<input type="SUBMIT" name="submit" value="Submit Now">
										</form>
									</div>
								</div>
							</div>
						</div>
					</div>
					<div class="col-lg-6">
						<div class="deneb_img_box">
							<img src=<%= li.getImg() %> class="img-fluid" alt="">
						</div>
					</div>
				</div>
				<% if (li.getState().equals("0")){ %>
				<a href="./light?action=changeon&id=<%= li.getId() %>" style="font-size:20px">turn on</a>
				<% }else{ %>
				<a href="./light?action=changeoff&id=<%= li.getId() %>" style="font-size:20px">turn off</a>
				<% } %>
				<br><a href="./light?action=delete&id=<%= li.getId() %>" style="font-size:20px">delete</a>
			</div>
		</div>
	</div>
</section>
<%} %>
<% if(action.equals("humidifier")){ %>
<% Humidifier hum=(Humidifier)session.getAttribute("hinf"); %>
<section class="service_wrapper section_padding">
	<div class="tab-content">
		<div id="web" class="tab-pane active">
			<div class="row align-items-center">
				<div class="col-lg-12">
					<div class="deneb_content_box">
						<h2><%= hum.getName() %></h2>
						<style>
							div{text-align:center}
						</style>
						<p>id: <%= hum.getId() %></p>
						<p>Humidity: <%= hum.getHumidity() %></p>
						<p>Temperature: <%= hum.getTemperature() %></p>
					</div>
					<div id="" style="width: 1700px;height: 400px">
						<div class="row align-items-center">

							<div id="temperature" style="width: 850px;height:400px;float :left"></div>
							<div id="humidity" style="width: 850px;height:400px;float :left"></div>
						</div>
						<div class="col-lg-3">
					</div>
						<div class="row">
							<div class="col-lg-12">
								<div class="deneb_pagination text-center">
									<div class="deneb_pagination text-center">
										<ul>
											<li class="prev"><a href="./humidifier?action=hdelete&hid=<%= hum.getId() %>" style="font-size:20px">Delete</a></li>

											<li class="prev"><a href="./humidifier"  style="font-size:20px">Back</a></li>
										</ul>
									</div>
								</div>
							</div>
						</div>
				</div>
			</div>
			</div>

	</div>
	<script type="text/javascript">
		// 基于准备好的dom，初始化echarts实例
		let myChart1 = echarts.init(document.getElementById('temperature'));
		let myChart2 = echarts.init(document.getElementById('humidity'));
		var wendu;
		var shidu;
		// 指定图表的配置项和数据
		option1 = {
			title:{
				text:"Temperature",
				left:"center"
			},
			series: [
				{
					type: 'gauge',
					center: ['50%', '60%'],
					startAngle: 200,
					endAngle: -20,
					min: 0,
					max: 60,
					splitNumber: 12,
					itemStyle: {
						color: '#FFAB91'
					},
					progress: {
						show: true,
						width: 30
					},
					pointer: {
						show: false
					},
					axisLine: {
						lineStyle: {
							width: 30
						}
					},
					axisTick: {
						distance: -45,
						splitNumber: 5,
						lineStyle: {
							width: 2,
							color: '#999'
						}
					},
					splitLine: {
						distance: -52,
						length: 14,
						lineStyle: {
							width: 3,
							color: '#999'
						}
					},
					axisLabel: {
						distance: -20,
						color: '#999',
						fontSize: 20
					},
					anchor: {
						show: false
					},
					title: {
						show: false
					},
					detail: {
						valueAnimation: true,
						width: '60%',
						lineHeight: 40,
						borderRadius: 8,
						offsetCenter: [0, '-15%'],
						fontSize: 40,
						fontWeight: 'bolder',
						formatter: '{value} C',
						color: 'auto'
					},
					data: [
						{
							value: 20
						}
					]
				},
				{
					type: 'gauge',
					center: ['50%', '60%'],
					startAngle: 200,
					endAngle: -20,
					min: 0,
					max: 60,
					itemStyle: {
						color: '#FD7347'
					},
					progress: {
						show: true,
						width: 8
					},
					pointer: {
						show: false
					},
					axisLine: {
						show: false
					},
					axisTick: {
						show: false
					},
					splitLine: {
						show: false
					},
					axisLabel: {
						show: false
					},
					detail: {
						show: false
					},
					data: [
						{
							value: 20
						}
					]
				}
			]
		};
		option2 = {
			title:{
				text:"Humidity",
				left:"center"
			},
			tooltip: {
				formatter: '{a} <br/>{b} : {c}%'
			},
			series: [
				{
					name: 'Pressure',
					type: 'gauge',
					progress: {
						show: true
					},
					detail: {
						valueAnimation: true,
						formatter: '{value}'
					},
					data: [
						{
							value: 50,
							name: '%'
						}
					]
				}
			]
		};
		setInterval(function () {
			$.ajax({
				type:"post",
				async:true,
				url:"./humidifier",
				data:{id:"<%=hum.getId()%>"},
				datatype:"json",
				contentType:"application/x-www-form-urlencoded;utf-8",
				success:function (result){
					var a=JSON.parse(result);
					wendu=a.temperature;
					shidu=a.humidity;
				}
			})
			myChart1.setOption({
				series: [
					{
						data: [
							{
								value: wendu
							}
						]
					},
					{
						data: [
							{
								value: wendu
							}
						]
					}
				]
			});
			myChart2.setOption({
				series: [
					{
						data: [
							{
								value: shidu,
								name: '%'
							}
						]
					},
					{
						data: [
							{
								value: shidu,
								name: '%'
							}
						]
					}
				]
			});
		}, 1000);
		// 使用刚指定的配置项和数据显示图表。
		myChart1.setOption(option1);
		myChart2.setOption(option2);

	</script>
	</div>
</section>


<% } %>

<% if(action.equals("door")){ %>
<% Door door=(Door)session.getAttribute("dinf"); %>
<section class="service_wrapper section_padding">
	<div class="tab-content">
		<div id="web" class="tab-pane active">
			<div class="row align-items-center">
				<div class="col-lg-12">
					<div class="deneb_content_box">
						<h2> <%= door.getName() %></h2>
						<style>
							div{text-align:center}
						</style>
						<p>id: <%= door.getId() %></p>
						<p>Distance: <%= door.getDistance() %></p>
					</div>

						<div class="row align-items-center">
							<div id="distance" style="width: 1700px;height:400px;float :left">></div>
						</div>
					<div class="row">
						<div class="col-lg-12">
							<div class="deneb_pagination text-center">
								<ul>
									<li class="prev"><a href="./door?action=ddelete&did=<%= door.getId() %>" style="font-size:20px">Delete</a></li>

									<li class="prev"><a href="./door"  style="font-size:20px">Back</a></li>
								</ul>
							</div>
						</div>
					</div>
				</div>
			</div>
			</div>
	</div>
	<script type="text/javascript">
		let myChart = echarts.init(document.getElementById('distance'));
		var juli=[];
		var xx=[];
		// 指定图表的配置项和数据
		option = {
			title: {
				text: 'Current Door distance & Time axis',
				left:"center"
			},
			tooltip: {
				trigger: 'axis'
			},
			xAxis: {
				type: 'category',
				data: xx,
				axisLine: {
					onZero: false
				}
			},
			yAxis: {
				type: 'value'
			},
			series: [
				{
					data: juli,
					type: 'line',
					name: 'distance',
					smooth: true
				}
			]
		};
		setInterval(function () {
			$.ajax({
				type:"post",
				async:true,
				url:"./door",
				data:{id:"<%=door.getId()%>"},
				datatype:"json",
				contentType:"application/x-www-form-urlencoded;utf-8",
				success:function (result){
					var a=JSON.parse(result);
					var time = Date.now();
					var z = new Date(time);
					var d = z.getHours()+":"+z.getMinutes()+":"+z.getSeconds();
					while (juli.length<30){
						juli.push(a.distan);
						xx.push(d);
					}
					juli.shift();
					juli.push(a.distan);
					xx.shift();
					xx.push(d);
				}
			})
			myChart.setOption({
				series: [
					{
						data: juli,
						type: 'line',
						showSymbol: false,
					}
				],
				xAxis: {
					type: 'category',
					data: xx
				}
			});
		}, 1000);
		// 使用刚指定的配置项和数据显示图表。
		myChart.setOption(option);
	</script>
</section>
</section>

<% } %>

<% if(action.equals("window")){ %>
<% Window win=(Window)session.getAttribute("winf"); %>
<section class="service_wrapper section_padding">
	<div class="tab-content">
		<div id="web" class="tab-pane active">
			<div class="row align-items-center">
				<div class="col-lg-12">
					<div class="deneb_content_box">
						<h2><%= win.getName() %></h2>
						<style>
							div{text-align:center}
						</style>
						<p>id: <%= win.getId() %></p>
						<p>Decibel: <%= win.getDecibel() %></p>
						<div id="decibel" style="width: 1700px;height:400px;"></div>
					</div>
					<div class="row">
						<div class="col-lg-12">
							<div class="deneb_pagination text-center">
								<ul>
									<li class="prev"><a href="./window?action=wdelete&wid=<%= win.getId() %>" style="font-size:20px">Delete</a></li>

									<li class="prev"><a href="./window"  style="font-size:20px">Back</a></li>

								</ul>

							</div>
						</div>
					</div>
				</div>
			</div>
		</div>
	</div>
	<script type="text/javascript">
		// 基于准备好的dom，初始化echarts实例
		let myChart = echarts.init(document.getElementById('decibel'));
		var fenbei=[];
		var y=[];
		// 指定图表的配置项和数据
		option = {
			title: {
				text: 'Current Window decibel & Time axis',
				left:"center"
			},
			tooltip: {
				trigger: 'axis'
			},
			xAxis: {
				type: 'category',
				data: y,
				axisLine: {
					onZero: false
				}
			},
			yAxis: {
				type: 'value'
			},
			series: [
				{
					data: fenbei,
					type: 'line',
					name: 'decibel',
					smooth: true
				}
			]
		};
		setInterval(function () {
			$.ajax({
				type:"post",
				async:true,
				url:"./window",
				data:{id:"<%=win.getId()%>"},
				datatype:"json",
				contentType:"application/x-www-form-urlencoded;utf-8",
				success:function (result){
					var a=JSON.parse(result);
					var time = Date.now();
					var z = new Date(time);
					var d = z.getHours()+":"+z.getMinutes()+":"+z.getSeconds();
					while (fenbei.length<30){
						fenbei.push(a.decibel);
						y.push(d);
					}
					fenbei.shift();
					fenbei.push(a.decibel);
					y.shift();
					y.push(d);
				}
			})
			myChart.setOption({
				series: [
					{
						data: fenbei,
						type: 'line',
						showSymbol: false,
					}
				],
				xAxis: {
					type: 'category',
					data: y
				}
			});
		}, 1000);
		// 使用刚指定的配置项和数据显示图表。
		myChart.setOption(option);
	</script>
</section>

<% } %>

<% } %>
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
<script src='js/stopExecutionOnTimeout.js?t=1'></script>
<script>
	var range_els = document.querySelectorAll('input[type=range]'), n = range_els.length, style_el = document.createElement('style'), styles = [], track_sel = [
		'::-moz-range-track',
		'::-webkit-slider-runnable-track',
		' /deep/ #track'
	], thumb_sel = [
		'::-webkit-slider-thumb',
		' /deep/ #thumb'
	], a = ':after', b = ':before', s = [
		'',
		'%',
		'%'
	];
	document.body.appendChild(style_el);
	for (var i = 0; i < n; i++) {
		if (window.CP.shouldStopExecution(1)) {
			break;
		}
		styles.push('');
		range_els[i].addEventListener('input', function () {
			var idx = this.id.split('r')[1] - 1, base_sel = '.js #' + this.id, str = '', min = this.min || 0, max = this.max || 100, c_style, u, edge_w, val;
			this.setAttribute('value', this.value);
			if (this.classList.contains('tip')) {
				str += base_sel + thumb_sel[0] + a + ',' + base_sel + thumb_sel[1] + a + '{content:"' + this.value + s[idx] + '"}';
			}
			if (this.classList.contains('fill')) {
				if (idx == 0) {
					c_style = getComputedStyle(this);
					u = c_style.backgroundSize.split(' ')[0].split('px')[0];
					edge_w = (c_style.width.split('px')[0] - u * (max - min)) / 2;
					val = (this.value - min) * u + edge_w + 'px';
				} else {
					val = this.value + '%';
				}
				if (this.classList.contains('fill-replace')) {
					str += base_sel + track_sel[0] + '{background-size:' + val + '}';
				}
				str += base_sel + track_sel[1] + a + ',' + base_sel + track_sel[2] + a + '{width:' + val + '}';
			}
			styles[idx] = str;
			style_el.textContent = styles.join('');
		}, false);
	}
	window.CP.exitedLoop(1);
</script>
<script type="text/javascript">
	$(function () {
		$.messager.confirm("alarm", "sure?", function (data) {
			if (data) {
				alert("yes");
			}
			else {
				alert("no");
			}
		});
	});
</script>
</body>
</html>