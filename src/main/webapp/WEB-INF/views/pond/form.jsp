<%@ page contentType="text/html; charset=utf-8" pageEncoding="UTF-8"%>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>

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
			    <li><a href="<spring:url value='/'/>">欢迎</a></li>
			    <li><a href="<spring:url value='/user/index'/>">彩民营</a></li>
			    <li><a href="<spring:url value='/prize/index'/>">奖品梦工厂</a></li>
			    <li class="active"><a href="<spring:url value='/pond/index'/>">奖池梦工厂</a></li>
			    <li><a href="<spring:url value='/activity/index'/>">活动梦工厂</a></li>
		    </ul>
	    </div>
    </div>
	
    <ul class="nav nav-tabs">
	    <li><a href="<spring:url value='/pond/index'/>" data-toggle="tab">奖池花名册</a></li>
	    <li class="active"><a data-toggle="tab">修筑奖池</a></li>
	    <li><a href="<spring:url value='/pond/batchform'/>" data-toggle="tab">批量修筑</a></li>
    </ul>
    
    <spring:url value="/pond/save" var="save"/>
	<form:form action="${save}" modelAttribute="model" method="post" class="form-horizontal">
	    <!-- 属性 -->
	    <div class="control-group">
			<label class="control-label">奖池名称</label>
			<div class="controls">
				<form:input path="name" id="name" class="input-large"/>
			</div>
		</div>
		<div class="control-group">
			<label class="control-label">开奖时间</label>
			<div class="controls">
				<form:input id="usetime" path="usetime" onClick="WdatePicker({dateFmt:'yyyy-MM-dd'})" class="input-large"/>
			</div>
		</div>
		<div class="control-group">
			<div class="controls">
				<input type="submit" class="btn btn-primary" value="保存" >
			</div>
		</div>
	</form:form>
	
	<script src="<spring:url value='/resources/My97DatePicker/WdatePicker.js'/>"></script>
	
</body>
</html>

