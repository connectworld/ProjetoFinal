<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Home</title>
<link href="view/bootstrapHome/css/bootstrap.min.css" rel="stylesheet"
	media="screen" />
<script type="text/javascript" src="view/js/jquery-2.1.4.js"></script>
<script src="view/bootstrapHome/js/bootstrap.min.js"></script>
</head>
<body>
<c:import url="/view/principal/menu.jsp"></c:import>
	<div class="container">
		<div class="row">
			<!-- The carousel -->
			<div id="transition-timer-carousel"
				class="carousel slide transition-timer-carousel"
				data-ride="carousel">
				<!-- Indicators -->
				<ol class="carousel-indicators">
					<li data-target="#transition-timer-carousel" data-slide-to="0"
						class="active"></li>
					<li data-target="#transition-timer-carousel" data-slide-to="1"></li>
					<li data-target="#transition-timer-carousel" data-slide-to="2"></li>
				</ol>

				<!-- Wrapper for slides -->
				<div class="carousel-inner">
					<div class="item active">
						<img src="view/img/arco.png" />
						<div class="carousel-caption">
							<p class="carousel-caption-text hidden-sm hidden-xs">
						
							</p>
						</div>
					</div>

					<div class="item">
						<img src="view/img/teste.jpg" />
						<div class="carousel-caption">
							<p class="carousel-caption-text hidden-sm hidden-xs">
				
							</p>
						</div>
					</div>

					<div class="item">
						<img src="view/img/manu.jpg" />
						<div class="carousel-caption">
							<p class="carousel-caption-text hidden-sm hidden-xs">Lorem
								
							</p>
						</div>
					</div>
				</div>
				<!-- Controls -->
				<a class="left carousel-control" href="#transition-timer-carousel"
					data-slide="prev"> <span
					class="glyphicon glyphicon-chevron-left"></span>
				</a> <a class="right carousel-control" href="#transition-timer-carousel"
					data-slide="next"> <span
					class="glyphicon glyphicon-chevron-right"></span>
				</a>

				<!-- Timer "progress bar" -->
				<hr class="transition-timer-carousel-progress-bar animate" />
			</div>
		</div>
	</div>
</body>
</html>