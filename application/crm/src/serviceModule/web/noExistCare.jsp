<%@ page contentType="text/html; charset=utf-8" language="java"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<title>养护管理</title>
<%@ taglib prefix="c" uri="http://java.sun.com/jstl/core_rt"%>
<%@ include file="/crm/coreModule/web/common.jsp"%>
<%@ include file="/crm/coreModule/web/saturnCalendar.jsp"%>
</head>
<body>
<div id="man_zone">
		<div style="margin-top:200px;text-align:center;">
			<div style="color:#2664EB; font-size:14px;font-weight:bolder;">
				工单号为${careorderid}的工单不存在养护记录。
			</div>
			<div style="margin-top:10px;">
				<input type="button" onclick="$('#hiddenForm').submit();" value="添加养护记录"/>
				<input type="button" onclick="window.close();" value="关闭"/>
			</div>
		</div>
		<div style="display:none;">
			<form action="${pageContext.request.contextPath}/crm/careModule/web/addCare.jsp" id="hiddenForm" name="hiddenForm" method="post">
				<input type="hidden" name="careorderid" id="careorderid" value="${careorderid}"/>
				<input type="hidden" name="careintime" id="careintime" value="${careintime}"/>
				<input type="hidden" name="carvin" id="carvin" value="${carvin}"/>
				<input type="hidden" name="carestaff" id="carestaff" value="${carestaff}"/>
				<input type="hidden" name="careouttime" id="careouttime" value="${careouttime}"/>
			</form>
		</div>
</div>
</body>
</html>