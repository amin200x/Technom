<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@ taglib uri="http://www.springframework.org/security/tags"
	prefix="sec"%>
<!DOCTYPE html>

<html>

<head>
<meta charset="UTF-8">

<!-- SEO -->
<meta name="description" content="Windows Linux Operation System Programming ویندوز سیستم عامل برنامه نویسی لینوکس"/>
<link rel="canonical" href="" />
<meta property="og:locale" content="en_US" />
<meta property="og:type" content="article" />
<meta property="og:title" content="${contentModel.title}" />
<meta property="og:description" content="وب سایتی برای دانستن در باره تکنولوژی های جدید" />
<meta property="og:url" content="${pageContext.request.contextPath}/showContent/${contentModel.id}" />
<meta property="og:site_name" content="Pagezii" />
<meta property="article:publisher" content="" />
<meta property="article:section" content="Technology Website" />
<meta property="article:published_time" content="${contentModel.persianDate }" />
<meta property="article:modified_time" content="${contentModel.persianDate }" />
<meta property="og:updated_time" content="${contentModel.persianDate }" />
<meta property="og:image" content="" />
<meta property="og:image:secure_url" content="" />
<meta name="twitter:card" content="summary_large_image" />
<meta name="twitter:description" content="Windows, Linux, Technology, Amin Ghadimian, ویندوز لینوکس تکنولوژی امین قدیمیان" />
<meta name="twitter:title" content="${contentModel.title}" />
<meta name="twitter:site" content="@Technom تکنوم" />
<meta name="twitter:image" content="" />
<meta name="twitter:creator" content="@A.Ghadimian" />
<!-- SEO -->
<meta name="keywords" content="windows linux hardware technology سخت افزار نرم افزار کامپیوتر ویندوز لینوکس امین قدیمیان" />
<meta http-equiv="content-type"
	content="text/html; charset=windows-1252" />
<meta name="viewport" content="width=device-width, initial-scale=1.0" />

<script src="https://code.jquery.com/jquery-3.5.1.slim.min.js"
	integrity="sha384-DfXdz2htPH0lsSSs5nCTpuj/zy4C+OGpamoFVy38MVBnE+IbbVYUew+OrCXaRkfj"
	crossorigin="anonymous"></script>
	
<link rel="stylesheet" type="text/css"
	href="${pageContext.request.contextPath}/style/bootstrap.css"
	title="style" />
	
<script>window.jQuery || document.write('<script src="../assets/js/vendor/jquery.slim.min.js"><\/script>')</script>
<script
	src="${pageContext.request.contextPath}/style/bootstrap.bundle.js"></script>


</head>

<body class=" bg-light">
	<nav class="navbar fixed-top navbar-expand-md navbar-light "
		style="background-color: #e3f2fd;">
		<div class="container-fluid">
			<button class="navbar-toggler" type="button" data-toggle="collapse"
				data-target="#navbarCollapse" aria-controls="navbarCollapse"
				aria-expanded="false" aria-label="Toggle navigation">
				<span class="navbar-toggler-icon"></span>
			</button>
			<div class="collapse navbar-collapse" id="navbarCollapse">

				<form action="${pageContext.request.contextPath}/searchContent"
					class="form-inline" method="get">
					<sec:authorize access="!isAuthenticated()">
			&nbsp;&nbsp;	<a href="${pageContext.request.contextPath}/login"
							class="btn btn-outline-success form-inline mr-sm-4 ">ورود</a>
					</sec:authorize>

					<input class="form-control mr-sm-1" dir="rtl" type="search"
						name="searchParam" placeholder="جستجو در سایت" aria-label="Search"
						required="required">

					<button class="btn btn-outline-info mr-sm-1" type="submit">جستجو</button>

				</form>

				<ul class="navbar-nav mr-auto">

					<sec:authorize access="isAuthenticated()">
						<li class="nav-item dropdown"><a
							class="nav-link dropdown-toggle" href="#" id="dropdown01"
							data-toggle="dropdown" aria-haspopup="true" aria-expanded="false"><sec:authentication
									property="principal.username" /></a>

							<div class="dropdown-menu" aria-labelledby="dropdown01">
								<form id="logOutForm" class="form-inline mt-2 mt-md-0"
									action="${pageContext.request.contextPath}/logout"
									method="post">
									<input type="hidden" name="${_csrf.parameterName }"
										value="${_csrf.token}" />
									<button type="submit"
										class="dropdown-item btn btn-outline-warning">خروج</button>
									<a
										href="${pageContext.request.contextPath }/deleteUser/<sec:authentication property="principal.username" />"
										onclick="if(confirm('با حذف حساب خود تمام اطلاعات ثبت شد توسط این حساب پاک خواهد شد') == false) return false;"
										class="dropdown-item btn btn-outline-danger" role="button"
										type="submit">حذف</a>
								</form>

								<sec:authorize access="hasRole('ADMIN')">

									<a class="dropdown-item btn btn-outline-secondary form-inline"
										role="button"
										href="${pageContext.request.contextPath}/userList">کاربران
										سایت </a>
									<a class="dropdown-item btn btn-outline-secondary form-inline"
										role="button"
										href="${pageContext.request.contextPath}/addContentForm">
										محتوای جدید </a>
									<a class="dropdown-item btn btn-outline-secondary form-inline"
										role="button"
										href="${pageContext.request.contextPath}/contentList">
										مطالب سایت </a>




								</sec:authorize>
							</div></li>
					</sec:authorize>

					<li class="nav-item"><a role="button"
						class="btn  btn-outline-danger my-3 my-sm-0"
						href="${pageContext.request.contextPath}/latestContents">آخرین
							مطالب </a></li> &nbsp;&nbsp;
					<li class="nav-item"><a role="button"
						class="btn btn-outline-danger my-3 my-sm-0"
						href="${pageContext.request.contextPath}/">خانه </a></li>
				</ul>
			</div>
		</div>
	</nav>

	<div id="carouselExampleIndicators" class="carousel slide "
		data-ride="carousel">

		<ol class="carousel-indicators">
			<li data-target="#carouselExampleIndicators" data-slide-to="0"
				class="active"></li>
			<li data-target="#carouselExampleIndicators" data-slide-to="1"></li>
			<li data-target="#carouselExampleIndicators" data-slide-to="2"></li>
			<li data-target="#carouselExampleIndicators" data-slide-to="3"></li>
			<li data-target="#carouselExampleIndicators" data-slide-to="4"></li>
			<li data-target="#carouselExampleIndicators" data-slide-to="5"></li>
		</ol>
		<div class="carousel-inner">
			<div class="carousel-item active">
				<img class="d-block w-100" style="height: 50vh"
					src="${pageContext.request.contextPath}/images/1.jpg"
					alt="First slide">
			</div>
			<div class="carousel-item">
				<img class="d-block w-100" style="height: 50vh"
					src="${pageContext.request.contextPath}/images/2.jpg"
					alt="Second slide">
			</div>
			<div class="carousel-item">
				<img class="d-block w-100" style="height: 50vh"
					src="${pageContext.request.contextPath}/images/3.jpg"
					alt="Third slide">
			</div>
			<div class="carousel-item">
				<img class="d-block w-100" style="height: 50vh"
					src="${pageContext.request.contextPath}/images/4.jpg"
					alt="Forth slide">
			</div>
			<div class="carousel-item">
				<img class="d-block w-100" style="height: 50vh"
					src="${pageContext.request.contextPath}/images/5.jpg"
					alt="Fifth slide">
			</div>
			<div class="carousel-item">
				<img class="d-block w-100" style="height: 50vh"
					src="${pageContext.request.contextPath}/images/6.jpg"
					alt="Sixth slide">
			</div>

		</div>

		<a class="carousel-control-prev" href="#carouselExampleIndicators"
			role="button" data-slide="next"> <span
			class="carousel-control-prev-icon" aria-hidden="true"></span> <span
			class="sr-only">بعدی</span>
		</a> <a class="carousel-control-next" href="#carouselExampleIndicators"
			role="button" data-slide="prev"> <span
			class="carousel-control-next-icon" aria-hidden="true"></span> <span
			class="sr-only">قبلی</span>
		</a>
	</div>


	<br>

	<!-- Main jumbotron for a primary marketing message or call to action 
	<div class="jumbotron">
		<div class="container">
			<h1 class="display-3">Hello, world!</h1>
			<p>This is a template for a simple marketing or informational
				website. It includes a large callout called a jumbotron and three
				supporting pieces of content. Use it as a starting point to create
				something more unique.</p>
		</div>
	</div> -->