<%@ page contentType="text/html; charset=utf-8" pageEncoding="UTF-8"%>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>

<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
	<link href="<spring:url value='/webjars/bootstrap/2.3.0/css/bootstrap.min.css' />" rel="stylesheet"/>
	<script src="<spring:url value='/resources/My97DatePicker/WdatePicker.js'/>"></script>
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
	    <li class="active"><a data-toggle="tab">抽奖转盘</a></li>
    </ul>
    
	<!-- 画饼 -->
	<div class="hero-unit">
	    <!-- Step:1 为ECharts准备一个具备大小（宽高）的Dom -->
	    <div id="main" style="height:666px;border:1px solid #ccc;padding:10px;"></div>
	    
	    <!-- Step:2 引入echarts.js -->
	    <script src="<spring:url value='/resources/js/echarts.js'/>"></script>
	    
	    <script type="text/javascript">
		    // Step:3 为模块加载器配置echarts的路径，从当前页面链接到echarts.js，定义所需图表路径
		    require.config({
		        paths: {
		            echarts: "<spring:url value='/resources/js'/>"
		        }
		    });
		    
		    // Step:4 动态加载echarts然后在回调函数中开始使用，注意保持按需加载结构定义图表路径
		    require(
		            [
		                "echarts",
		                "echarts/chart/pie",
		                "echarts/chart/funnel"// 按需加载所需图表，如需动态类型切换功能，别忘了同时加载相应图表
		            ],
		            function (ec) {
		                var myChart = ec.init(document.getElementById('main'));
		                // 后台封装数据
		                ${option};
		                myChart.setOption(option);
		            }
		    );
	    </script>
    </div>
    
    <!-- 列表
   	<table class="table table-hover">
   		<thead>
			<tr>
				<th>编号</th>
				<th>访问时间</th>
				<th>加载时间</th>
				<th>主站域名</th>
				<th>访问页面</th>
				<th>User Agent</th>
			</tr>
		</thead>
		<tbody>
			<c:forEach items="${visitors}" var="visitor" varStatus="stat">
				<tr>
				<td>${visitor.id}</td>
				<td><fmt:formatDate value="${visitor.visit_time}" pattern="yyyy-MM-dd HH:mm:ss" /></td>
				<td>${visitor.load_time}</td>
				<td>${visitor.domain}</td>
				<td>
					<c:if test="${visitor.page_url.length() gt 50}">
						${fn:substring(visitor.page_url, 0, 51)}...
					</c:if>
					<c:if test="${visitor.page_url.length() le 50}">
						${visitor.page_url}
					</c:if>
				</td>
				<td>
					<c:if test="${visitor.ua.length() gt 100}">
						${fn:substring(visitor.ua, 0, 101)}...
					</c:if>
					<c:if test="${visitor.ua.length() le 100}">
						${visitor.ua}
					</c:if>
				</td>
			</c:forEach>
		</tbody>
	</table> -->
	
	<!-- 延迟加载js，提高页面加载速度 -->
	<script src="<spring:url value='/webjars/jquery/2.0.3/jquery.js' />"></script>
	<script src="<spring:url value='/resources/js/common.js' />"></script>
	
</body>
</html>

