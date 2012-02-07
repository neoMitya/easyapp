<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Pilothallengespräche</title>
<%@ include file="/app/pep/include/mainheader.jsp"%>
<script type="text/javascript">
function addTab(pageTab, node) {
	pageTab.tabs('add', {
		title : node.text,
		content : '<iframe scrolling="yes" frameborder="0"  src="<%=request.getContextPath()%>' + node.attributes.path + '" style="width:100%;height:100%;"></iframe>',
		iconCls : 'icon-tap',
		closable : true
	});
}

$(function() {
	$('#navigateTree').tree({
		url: 'demoTree.json',
		//url: '<%=request.getContextPath()%>/ph/ListTree.do?folderuid=<%=request.getAttribute("uid")%>',
		onClick : function(node) {
		/*
			var text = node.text;
			var pageTab = $('#pageTab');
			var tab = pageTab.tabs('getTab', text);
			if (($('#navigateTree').tree('isLeaf',
					node.target) == true ) && (node.attributes!=null)) {
         
				if (tab == null) {
					addTab(pageTab, node);
				} else {
					$.messager.confirm('提示', '"'+text+'"已打开,是否重新打开', function(result){
						if (result){
							pageTab.tabs('close', text);
							addTab(pageTab, node);
						} else {
							pageTab.tabs('select', text);
						}
					});
				}
			}
			*/
			$('#ff').attr("src", "<%=request.getContextPath()%>" + node.attributes.path); 
		}
	});
});
</script>
</head>
<body class="easyui-layout">
	<!--<div region="north" split="false"
		style="height: 50px; overflow: hidden;">
		<div class="top_img">
	    <div class="user-info">用户名：${authUser.name}　　           　[<a href="<%=request.getContextPath()%>/app/auth/user/logout.action">退出</a>] </div>
		<div class="menu-info"></div>	
		</div> 
	</div>
	--><div region="west" split="true" title="目录"
		style="width: 200px; padding1: 10px; overflow: hidden;">
		<div style="padding: 10px;">
			<ul id="navigateTree" class="easyui-tree"></ul>
		</div>
	</div>
	<!--  
	<div region="center" style="overflow: hidden;">
		<div id="pageTab" class="easyui-tabs" fit="true" border="false">
			<div title="汇报材料" style="padding: 10px; overflow: hidden;">欢迎</div>
		</div>
	</div>
	-->
	
	<div region="center" style="overflow: hidden;">
		<iframe id="ff" name= "ff" scrolling="yes" frameborder="0"  src="<%=request.getContextPath()%>/app/pep/begin.jsp" style="width:100%;height:100%;"></iframe>
	</div>
</body>
</html>