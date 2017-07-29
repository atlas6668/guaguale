<%@ page contentType="text/html; charset=utf-8" pageEncoding="UTF-8"%>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
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
	    <li class="active"><a data-toggle="tab">奖池花名册</a></li>
	    <!-- <li><a href="<spring:url value='/pond/form'/>" data-toggle="tab">修筑奖池</a></li>
	    <li><a href="<spring:url value='/pond/batchform'/>" data-toggle="tab">批量修筑</a></li> -->
    </ul>
	
	&nbsp;<input id="delete-button" class="btn btn-danger" type="button" value="删除奖池"/>
	<!-- 批量删除form -->
    <form id="delete_form" action="<spring:url value='/pond/delete'/>" method="post">
       <input type="hidden" name="ids" id="delete_model_ids">
    </form>
    
    <!-- 列表 -->
   	<table class="table table-hover">
   		<thead>
			<tr>
				<th scope="col">
       				<input type="checkbox" onclick="$('input[name=modelId]:checkbox').prop('checked',$(this).prop('checked'))">
       			</th>
				<th>奖池编号</th>
				<th>奖池名称</th>
				<th>开奖日期</th>
				<th>动动手脚</th>
			</tr>
		</thead>
		<tbody>
			<c:forEach items="${ponds}" var="pond" varStatus="stat">
				<tr>
				<td><input type="checkbox" name="modelId" value="${pond.id}" /></td>
				<td>${pond.id}</td>
				<td>${pond.name}</td>
				<td><fmt:formatDate value="${pond.usetime}" pattern="yyyy-MM-dd" /></td>
				<td>
					<!-- <a id="edit" class="btn" href="<spring:url value='/pond/form/${pond.id}'/>">维护奖池</a> -->
					<a id="view" class="btn" href="<spring:url value='/pond/chart/${pond.id}'/>">查看奖盘</a>
				</td>
			</c:forEach>
		</tbody>
	</table>
		
	<!-- 延迟加载js，提高页面加载速度 -->
	<script src="<spring:url value="/webjars/jquery/2.0.3/jquery.js" />"></script>
	<script src="<spring:url value="/resources/js/common.js" />"></script>
	
</body>
</html>

