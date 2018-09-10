<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%
	String path = request.getContextPath();
	String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>
<!DOCTYPE html>
<html lang="en">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>pageDemo</title>
<base href="<%=basePath%>">
<meta name="description" content="overview & stats" />
<meta name="viewport" content="width=device-width, initial-scale=1.0" />

<script type="text/javascript" src="static/js/jquery-2.1.4.min.js"></script>

</head>
<body>
	
	<c:forEach items="${pageinfo.list}" var="user" varStatus="vs">
		<h5>${user}</h5>
	</c:forEach>
	
	<h5> 当前页:${pageinfo.pageNum } </h5>
	<h5> 每页数量:${pageinfo.pageSize }  </h5>
	<h5> 当前页数量:${pageinfo.size }  </h5>
	<h5> 总记录数:${pageinfo.total }  </h5>
	<h5> 总页数:${pageinfo.pages }  </h5>
	
	<span style="color:#f00;">当前页面文件名：pageDemo.jsp</span>
	<script type="text/javascript">

	</script>
</body>
</html>