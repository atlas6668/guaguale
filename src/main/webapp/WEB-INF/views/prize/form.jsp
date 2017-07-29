<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">

<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page contentType="text/html; charset=utf-8" pageEncoding="UTF-8"%>

<html xmlns="http://www.w3.org/1999/xhtml">
<head>
	<link href="<spring:url value='/webjars/bootstrap/2.3.0/css/bootstrap.min.css' />" rel="stylesheet" />
	<style type="text/css">
		#preview{width:220px;height:180px;border:0px solid #000;overflow:hidden;}
		#p_image{filter:progid:DXImageTransform.Microsoft.AlphaImageLoader(sizingMethod=image);}
	</style>
</head>
<body>
	<!-- 面包屑 -->
	<div class="navbar">
		<div class="navbar-inner">
			<a class="brand">刮刮乐</a>
			<ul class="nav">
				<li><a href="<spring:url value='/'/>">欢迎</a></li>
				<li><a href="<spring:url value='/user/index'/>">彩民营</a></li>
				<li class="active"><a href="<spring:url value='/prize/index'/>">奖品梦工厂</a></li>
				<li><a href="<spring:url value='/pond/index'/>">奖池梦工厂</a></li>
				<li><a href="<spring:url value='/activity/index'/>">活动梦工厂</a></li>
			</ul>
		</div>
	</div>

	<ul class="nav nav-tabs">
		<li><a href="<spring:url value='/prize/index'/>" data-toggle="tab">奖品花名册</a></li>
		<li class="active"><a data-toggle="tab">预览奖品</a></li>
		<!-- <li><a href="<spring:url value='/prize/batchform'/>" data-toggle="tab">批量生产</a></li> -->
	</ul>

	<spring:url value='/prize/save' var="save" />
	<form:form action="${save}" modelAttribute="model" method="post"
		onsubmit="return checkFrm(this)" class="form-horizontal" enctype="multipart/form-data">
		<!-- 属性 -->
		<div class="control-group">
			<div class="controls">
				<form:hidden path="id" />
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
					<img id="image" class="img-rounded" src="${path}">
				</div>
				<c:if test="${model.id==null}">
					<input type="file" id="file" name="file" onchange="previewImage(this)" />
				</c:if>
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
			<div class="controls">
				<c:if test="${model.id==null}">
					<input type="submit" class="btn btn-primary" value="保存">
				</c:if>
			</div>
		</div>
	</form:form>

	<!-- 延迟加载js，提高页面加载速度 -->
	<script src="<spring:url value='/webjars/jquery/2.0.3/jquery.js'/>"></script>
	<script src="<spring:url value='/resources/js/common.js'/>"></script>
	<script type="text/javascript">
		function checkFrm(object) {
			
			// 奖品图片验证
			var file = $("#file").val();
			if (file == null || file == "") {
				alert("请选择一张图片");
				return false;
			}
			$(object).submit();
			return true;
		}
	</script>
</body>
</html>

