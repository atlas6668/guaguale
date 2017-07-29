<%@ page contentType="text/html; charset=utf-8" pageEncoding="UTF-8"%>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib prefix="izy" uri="/WEB-INF/tlds/pagination.tld"%>

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
		    <li class="active"><a href="<spring:url value='/user/index'/>">彩民营</a></li>
		    <li><a href="<spring:url value='/prize/index'/>">奖品梦工厂</a></li>
		    <li><a href="<spring:url value='/pond/index'/>">奖池梦工厂</a></li>
		    <li><a href="<spring:url value='/activity/index'/>">活动梦工厂</a></li>
		    </ul>
	    </div>
    </div>
	
    <ul class="nav nav-tabs">
	    <li class="active"><a data-toggle="tab">彩民营</a></li>
	    <!-- <li><a href="<spring:url value='/user/form'/>" data-toggle="tab">集结彩民</a></li> -->
    </ul>
   	<!-- 搜索form -->
    <spring:url value="/user/index" var="search" />
	<form:form id="f_search" action="${search}" method="post" 
		modelAttribute="model" class="breadcrumb form-search">
		<input type="hidden" id="pageNum" name="pageNum"/>
		<input type="hidden" id="pageSize" name="pageSize"/>
		
		<div style="margin-top:8px;">
			<i class="icon-search"></i>
			&nbsp;<label>昵称：</label><form:input path="nick" htmlEscape="false" class="input-medium"/>
			&nbsp;<label>手机：</label><form:input path="mobile" htmlEscape="false" class="input-medium"/>
			&nbsp;<input id="btnSubmit" class="btn btn-primary" type="submit" value="查询"/>
		</div>
	</form:form>
	
	&nbsp;<input id="delete-button" class="btn btn-danger" type="button" value="删除良民"/>
	<!-- 批量删除form -->
    <form id="delete_form" action="<spring:url value='/user/delete'/>" method="post">
       <input type="hidden" name="ids" id="delete_model_ids">
    </form>
	
	<form id="f_list">
	
	    <!-- 列表 -->
	   	<table class="table table-hover">
	   		<thead>
				<tr>
					<th scope="col">
	       				<input type="checkbox" onclick="$('input[name=modelId]:checkbox').prop('checked',$(this).prop('checked'))">
	       			</th>
	       			<th>序号</th>
					<th>昵称</th>
					<th>手机</th>
					<th>参加的活动</th>
					<th>记录时间</th>
				</tr>
			</thead>
			<tbody>
				<c:forEach items="${users}" var="user" varStatus="stat">
					<tr>
					<td><input type="checkbox" name="modelId" value="${user.id}" /></td>
					<td>${stat.count}</td>
					<td>${user.nick}</td>
					<td>${user.mobile}</td>
					<td>${user.akey}</td>
					<td><fmt:formatDate value="${user.createtime}" pattern="yyyy-MM-dd HH:mm:ss" /></td>
				</c:forEach>
			</tbody>
		</table>
		
		<!-- 分页DIV -->
		<izy:pagination pager="${pager}" pageTagClass="pagination pagination-large"/>
	
	</form>
	
	<!-- 延迟加载js，提高页面加载速度 -->
	<script src="<spring:url value="/webjars/jquery/2.0.3/jquery.js" />"></script>
	<script src="<spring:url value="/resources/js/common.js" />"></script>
	
</body>
</html>

