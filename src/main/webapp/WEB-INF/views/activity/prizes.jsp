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
		    </ul>
	    </div>
    </div>
    
    <ul class="nav nav-tabs">
    	<li><a href="<spring:url value='/activity/index'/>" data-toggle="tab">活动花名册</a></li>
	    <li class="active"><a data-toggle="tab">活动奖品</a></li>
    </ul>
	
	<!-- 搜索form -->
    <spring:url value="/activity/prizes/${id}" var="search" />
	<form:form id="f_search" action="${search}" method="post" 
		modelAttribute="model" class="breadcrumb form-search">
		<div style="margin-top:8px;">
			<i class="icon-search"></i>
			&nbsp;<label>兑奖码：</label><form:input id="xmk" path="xmk" htmlEscape="false" class="input-xlarge"/>
			&nbsp;<label>奖品名称：</label><form:input id="name" path="name" htmlEscape="false" class="input-medium"/>
			&nbsp;<label>兑奖状态：</label>
			<select name="status" class="span2">
				<option value="1"${model.status==1?" selected='selected'":""}>已发放</option>
				<option value="0"${model.status==0?" selected='selected'":""}>未发放</option>
				<option value="2"${model.status==2?" selected='selected'":""}>已兑奖</option>
			</select>
			&nbsp;<input id="btnSubmit" class="btn btn-primary" type="submit" value="查询"/>
		</div>
	</form:form>
	
    <!-- 列表 -->
   	<table class="table table-hover">
   		<thead>
			<tr>
				<th scope="col">
       				<input type="checkbox" onclick="$('input[name=modelId]:checkbox').prop('checked',$(this).prop('checked'))">
       			</th>
				<th>序号</th>
				<th>兑奖码</th>
				<th>奖品名称</th>
				<th>奖品类型</th>
				<th>生产日期</th>
				<th>获奖者昵称</th>
				<th>获奖者手机号码</th>
				<th>兑奖状态</th>
				<th>兑奖日期</th>
				<th>动动手脚</th>
			</tr>
		</thead>
		<tbody>
			<c:forEach items="${prizes}" var="prize" varStatus="stat">
				<tr>
				<td><input type="checkbox" name="modelId" value="${prize.id}" /></td>
				<td>${stat.count}</td>
				<td>${prize.xmk}</td>
				<td>${prize.name}</td>
				<td>${prize.ptype}</td>
				<td><fmt:formatDate value="${prize.createtime}" pattern="yyyy-MM-dd HH:mm:ss" /></td>
				<td>${prize.unick}</td>
				<td>${prize.umobile}</td>
				<td>
					<c:if test="${prize.status==0}">未发放</c:if>
					<c:if test="${prize.status==1}">已发放</c:if>
					<c:if test="${prize.status==2}">已兑奖</c:if>
				</td>
				<td>
					<c:if test="${prize.duijiangtime == null}">
						0000-00-00 00:00:00
					</c:if>
					<c:if test="${prize.duijiangtime != null}">
						<fmt:formatDate value="${prize.duijiangtime}" pattern="yyyy-MM-dd HH:mm:ss" />
					</c:if>
				</td>
				<td>
					<c:if test="${prize.status==1}">
						<a id="duijiang" class="btn" href="<spring:url value='/activity/duijiang/${id}/${prize.id}'/>" onclick="return confirm('您确定兑奖吗？');">兑奖</a>
					</c:if>
				</td>
			</c:forEach>
		</tbody>
	</table>
	
	<!-- 延迟加载js，提高页面加载速度 -->
	<script src="<spring:url value="/webjars/jquery/2.0.3/jquery.js" />"></script>
	<script src="<spring:url value='/resources/js/common.js'/>"></script>
	
</body>
</html>

