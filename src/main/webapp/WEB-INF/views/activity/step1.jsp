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
		    <li><a href="<spring:url value='/pond/index'/>">奖池梦工厂</a></li>
		    <li class="active"><a href="<spring:url value='/activity/index'/>">活动梦工厂</a></li>
		    </ul>
	    </div>
    </div>
    
    <ul class="nav nav-tabs">
    	<li><a href="<spring:url value='/activity/index'/>" data-toggle="tab">活动花名册</a></li>
	    <li class="active"><a data-toggle="tab">编辑活动</a></li>
    </ul>
	
    <ul class="nav nav-pills">
	    <li class="active"><a>
		    &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
		    第一步 基础设置
		    &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
	    </a></li>
	    <li class="disabled"><a>
		    &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
		    第二步 活动项设置
		    &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
	    </a></li>
	    <li class="disabled"><a>
		    &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
		    第三步 奖项设置
		    &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
	    </a></li>
    </ul>
    
    <spring:url value="/activity/step1/save" var="save"/>
	<form:form action="${save}" modelAttribute="model" method="post" class="form-horizontal" 
		onsubmit="return checkFrm(this)">
	    <!-- 属性 -->
	    <div class="control-group">
			<div class="controls">
				<form:hidden path="id" />
			</div>
		</div>
		<div class="control-group">
			<label class="control-label">关键词<span style="color:red">*</span></label>
			<div class="controls">
				<form:input path="akey" id="akey"/>
			</div>
		</div>
	    <div class="control-group">
			<label class="control-label">活动名称<span style="color:red">*</span></label>
			<div class="controls">
				<form:input path="name" id="name"/>
			</div>
		</div>
		<div class="control-group">
			<label class="control-label">活动介绍</label>
			<div class="controls">
				<form:textarea path="intro" id="intro" />
			</div>
		</div>
		<div class="control-group">
			<label class="control-label">开始时间<span style="color:red">*</span></label>
			<div class="controls">
				<form:input id="starttime" path="starttime" onClick="WdatePicker({dateFmt:'yyyy-MM-dd HH:mm:ss'})" class="input-large"/>
			</div>
		</div>
		<div class="control-group">
			<label class="control-label">结束时间<span style="color:red">*</span></label>
			<div class="controls">
				<form:input id="stoptime" path="stoptime" onClick="WdatePicker({dateFmt:'yyyy-MM-dd HH:mm:ss'})" class="input-large"/>
			</div>
		</div>
		<div class="control-group">
			<div class="controls">
				<input type="button" class="btn" onclick="javascript:history.back(-1);" value="后退">
				<input type="submit" class="btn btn-primary" value="下一步">
			</div>
		</div>
	</form:form>
	
	<!-- 延迟加载js，提高页面加载速度 -->
	<script src="<spring:url value='/webjars/jquery/2.0.3/jquery.js'/>"></script>
	<script src="<spring:url value='/resources/My97DatePicker/WdatePicker.js'/>"></script>
	<script type="text/javascript">
		function checkFrm(object) {
			
			// 验证
			var akey = $("#akey").val();
			if (akey == null || akey == "") {
				alert("请拟定一个关键词");
				return false;
			}
			var name = $("#name").val();
			if (name == null || name == "") {
				alert("请填写活动名称");
				return false;
			}
			var starttime = $("#starttime").val();
			if (starttime == null || starttime == "") {
				alert("请选择开始时间");
				return false;
			}
			var stoptime = $("#stoptime").val();
			if (stoptime == null || stoptime == "") {
				alert("请选择结束时间");
				return false;
			}
			$(object).submit();
			return true;
		}
	</script>
</body>
</html>

