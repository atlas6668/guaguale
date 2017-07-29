<%@ page contentType="text/html; charset=utf-8" pageEncoding="UTF-8"%>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>

<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
	<link href="<spring:url value='/webjars/bootstrap/2.3.0/css/bootstrap.min.css' />" rel="stylesheet"/>
</head>
<body>
	<!-- 面包屑 -->
    <div class="navbar">
	    <div class="navbar-inner">
		    <a class="brand">刮刮乐</a>
		    <ul class="nav">
			    <li class="active"><a href="<spring:url value='/'/>">欢迎</a></li>
			    <li><a href="<spring:url value='/user/index'/>">彩民营</a></li>
			    <li><a href="<spring:url value='/prize/index'/>">奖品梦工厂</a></li>
			    <li><a href="<spring:url value='/pond/index'/>">奖池梦工厂</a></li>
			    <li><a href="<spring:url value='/activity/index'/>">活动梦工厂</a></li>
			    <li><a href="<spring:url value='/activity/testausers'/>">模拟测试</a></li>
		    </ul>
	    </div>
    </div>
    
    <div class="hero-unit">
	    <h1>Hello,World!爪哇欢迎您！</h1>
    </div>
    
    <!-- 延迟加载js，提高页面加载速度 -->
	<script src="<spring:url value='/webjars/jquery/2.0.3/jquery.js' />"></script>
	
</body>
</html>

