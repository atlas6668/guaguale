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
	    <li class="disabled"><a>
		    &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
		    第一步 基础设置
		    &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
	    </a></li>
	    <li class="disabled"><a>
		    &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
		    第二步 活动项设置
		    &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
	    </a></li>
	    <li class="active"><a>
		    &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
		    第三步 奖项设置
		    &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
	    </a></li>
    </ul>
    
    <spring:url value='/activity/step3/save' var="save" />
	<form:form id="sform" action="${save}" modelAttribute="model" 
		method="post" class="form-horizontal" enctype="multipart/form-data">
		<!-- 属性 -->
		<div class="control-group">
			<div class="controls">
				<form:hidden path="activity"/>
				<form:hidden id="continued" path="continued"/>
			</div>
		</div>
		<div class="control-group">
			<div class="controls">
				<c:if test="${model.image == null || model.image == ''}">
					<spring:url value="/resources/images/no_image.jpg" var="path" />
				</c:if>
				<c:if test="${model.image != null && model.image != ''}">
					<spring:url value="${model.image}" var="path" />
				</c:if>
				<div id="preview">
					<img id="p_image" class="img-rounded" src="${path}">
				</div>
				<input type="file" id="file" name="file"
					onchange="previewImage(this)" />
			</div>
		</div>
		<div class="control-group">
			<label class="control-label">
				奖品名称<span style="color:red">*</span>
			</label>
			<div class="controls">
				<form:input path="name" />
			</div>
		</div>
		<div class="control-group">
			<label class="control-label">
				奖品类型
			</label>
			<div class="controls">
				<form:input path="ptype" />
			</div>
		</div>
		<div class="control-group">
			<label class="control-label">
				奖品数量
			</label>
			<div class="controls">
				<form:input path="sum" />
			</div>
		</div>
	    
		<div class="control-group">
			<div class="controls">
				<input id="continue" type="button" class="btn btn-primary" value="继续添加奖品">
				<input id="finish" type="button" class="btn btn-primary" value="完成">
			</div>
		</div>
	</form:form>
	
	<!-- 延迟加载js，提高页面加载速度 -->
	<script src="<spring:url value='/webjars/jquery/2.0.3/jquery.js'/>"></script>
	<script src="<spring:url value='/resources/My97DatePicker/WdatePicker.js'/>"></script>
	<script src="<spring:url value='/resources/js/common.js'/>"></script>
	<script type="text/javascript">
		$('#continue').click(function(e){
			$("#continued").val(true);
			var form = $("#sform");
			form.submit();
		});
		
		$('#finish').click(function(e){
			$("#continued").val(false);
			var form = $("#sform");
			form.submit();
		});
	</script>
</body>
</html>

