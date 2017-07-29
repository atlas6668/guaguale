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
		    <li><a href="<spring:url value='/pond/index'/>">奖池梦工厂</a></li>
		    <li class="active"><a href="<spring:url value='/activity/index'/>">活动梦工厂</a></li>
		    <li><a href="<spring:url value='/activity/testausers'/>">模拟测试</a></li>
		    </ul>
	    </div>
    </div>
    
    <ul class="nav nav-tabs">
	    <li class="active"><a data-toggle="tab">活动花名册</a></li>
    </ul>
	
	&nbsp;<input id="add-button" class="btn btn-primary" type="button" value="新增活动"/>
	&nbsp;<input id="delete-button" class="btn btn-danger" type="button" value="删除活动"/>
	<!-- 新增form -->
	<form id="add_form" action="<spring:url value='/activity/step1'/>" method="get">
   	</form>
   	
	<!-- 删除form -->
    <form id="delete_form" action="<spring:url value='/activity/delete'/>" method="post">
	    <input type="hidden" name="ids" id="delete_model_ids">
    </form>
	
    <!-- 列表 -->
   	<table class="table table-hover">
   		<thead>
			<tr>
				<th scope="col">
       				<input type="checkbox" onclick="$('input[name=modelId]:checkbox').prop('checked',$(this).prop('checked'))">
       			</th>
				<th>活动名称</th>
				<th>关键词</th>
				<th>开始时间</th>
				<th>结束时间</th>
				<th>状态</th>
				<th>操作</th>
			</tr>
		</thead>
		<tbody>
			<c:forEach items="${activities}" var="activity" varStatus="stat">
				<tr>
				<td><input type="checkbox" name="modelId" value="${activity.id}" /></td>
				<td>${activity.name}</td>
				<td>${activity.akey}</td>
				<td><fmt:formatDate value="${activity.starttime}" pattern="yyyy-MM-dd HH:mm:ss" /></td>
				<td><fmt:formatDate value="${activity.stoptime}" pattern="yyyy-MM-dd HH:mm:ss" /></td>
				<td>
					<c:if test="${activity.status==0}">创建中</c:if>
					<c:if test="${activity.status==1}">活动中</c:if>
					<c:if test="${activity.status==2}">已结束</c:if>
				</td>
				<td>
					<c:if test="${activity.status==0}">
						<a id="edit" class="btn btn-primary" href="<spring:url value='/activity/step1/${activity.id}'/>">编辑活动</a>
						<a id="start" class="btn btn-success" href="<spring:url value='/activity/start/${activity.id}'/>" onclick="return confirm('您确定启动吗？');">启动活动</a>
					</c:if>
					<c:if test="${activity.status==1}">
						<a id="stop" class="btn btn-danger" href="<spring:url value='/activity/stop/${activity.id}'/>" onclick="return confirm('您确定结束吗？');">结束活动</a>
						<a id="restart" class="btn btn-inverse" href="<spring:url value='/activity/restart/${activity.id}'/>" onclick="return confirm('您确定重启吗？');">重启活动</a>
					</c:if>
					<a id="uu" class="btn btn-info" href="<spring:url value='/activity/users/${activity.id}'/>">用户管理</a>
					<a id="pp" class="btn btn-info" href="<spring:url value='/activity/prizes/${activity.id}'/>">奖品管理</a>
				</td>
			</c:forEach>
		</tbody>
	</table>
		
	<!-- 延迟加载js，提高页面加载速度 -->
	<script src="<spring:url value="/webjars/jquery/2.0.3/jquery.js" />"></script>
	<script src="<spring:url value="/resources/js/common.js" />"></script>
	
</body>
</html>

