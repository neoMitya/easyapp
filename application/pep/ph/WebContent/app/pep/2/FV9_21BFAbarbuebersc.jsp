<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@page import="java.util.List"%>
<%@page import="java.util.ArrayList"%>
<%@page import="java.util.Map"%>
<%@page import="java.util.HashMap"%>	
<%@page import="com.saturn.web.Web"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html>
	<head>
		<meta http-equiv="Content-Type" content="text/html; charset=utf-8"></meta>
		<%@ include file="/app/pep/include/header.jsp"%>
		<title><%=title %></title>
		<%
			String status = "08.12.2011";
			List<String> fv9BFreigType = (List<String>)form.get("fv9BFreigType"); //零件类型
			List<String> fv9BFreigTypeNum = (List)form.get("fv9BFreigTypeNum");  //认可数量
			List<String> fv9BFKWNo = (List)form.get("fv9BFKWNo");  //周数
			List<String> fv9BFreiSoll = (List)form.get("fv9BFreiSoll");  //fv9BFreiSoll
			List<String> fv9BFInArbeirt = (List)form.get("fv9BFInArbeirt");  //fv9BFInArbeirt
			List<String> fv9BFAWE = (List)form.get("fv9BFAWE");  //fv9BFAWE
			
			List<String> Gesamt = new ArrayList<String>();
			List<String> categories = new ArrayList<String>();
	//		StringBuffer BFreigTypeNum = new StringBuffer();
			StringBuffer BFreiSoll = new StringBuffer();
			StringBuffer BFInArbeirt = new StringBuffer();
			StringBuffer BFAWE = new StringBuffer();
	//		BFreigTypeNum.append("[");
			BFreiSoll.append("[");
			BFInArbeirt.append("[");
			BFAWE.append("[");
			int sum = 0;
			if (Web.getListYesOrNo(fv9BFreigTypeNum)) {
				for (int i=0; i<fv9BFreigTypeNum.size(); i++){
					sum += Integer.parseInt(fv9BFreigTypeNum.get(i));
				}
			
				categories.add("Gesamt");
		//		BFreigTypeNum.append("{y:" + sum + ", low:0, color:'#0200FE'},");
				BFreiSoll.append("{y:" + sum + ", low:0, color:'#0200FE'},");
				BFInArbeirt.append("{y:0, low:0, color:'#F9A700'},");
				BFAWE.append("{y:0, low:0, color:'#FFFFCC'},");
				
				int temp = 0;
				for (int i=0; i<fv9BFreigTypeNum.size(); i++){
					int y = Integer.parseInt(fv9BFreigTypeNum.get(i));
					temp = temp + y;
					int low = sum - temp;
					categories.add(fv9BFreigType.get(i));
					String color = "#0200FE";
					if (i == fv9BFreigTypeNum.size()-1) {
						color = "#FF00FE";
					}
		//			BFreigTypeNum.append("{y:" + y + ", low:" + low + ", color:'" + color + "'},");
					BFreiSoll.append("{y:" + y + ", low:" + low + ", color:'" + color + "'},");
					BFInArbeirt.append("{y:0, low:0, color:'#F9A700'},");
					BFAWE.append("{y:0, low:0, color:'#FFFFCC'},");
				}
				
			}
			if (Web.getObjectYesOrNo(fv9BFKWNo)) {
				for(int m=0; m<fv9BFKWNo.size(); m++) {
					categories.add(fv9BFKWNo.get(m));
					int BFAWENUM = Integer.parseInt(fv9BFAWE.get(m));
					int BFInArbeirtNUM = Integer.parseInt(fv9BFInArbeirt.get(m));
					int BFreiSollNUM = Integer.parseInt(fv9BFreiSoll.get(m));
					
		//			BFreigTypeNum.append("{y:0, low:0, color:'#0200FE'},");
					BFAWE.append("{y:" + BFAWENUM + ", low:0, color:'#FFFFCC'},");
					BFInArbeirt.append("{y:" + BFInArbeirtNUM + ", low:" + BFAWENUM + ", color:'#F9A700'},");
					BFreiSoll.append("{y:" + BFreiSollNUM + ", low:" + (BFInArbeirtNUM+BFAWENUM) + ", color:'#009C0E'},");
					
				}
			}
			String cat = Web.getStrListStr(categories);
			System.out.println("cat = " + cat);
	//		BFreigTypeNum.append("]");
			BFreiSoll.append("]");
			BFInArbeirt.append("]");
			BFAWE.append("]");
			
		%>	
<%

			int preNum = 0;
			
			if(Web.getObjectYesOrNo(fv9BFreigType)){
				preNum = fv9BFreigType.size() + 1;
			}
			int total = sum;
			
			int[] arr = null;
			if(Web.getObjectYesOrNo(form.get("fv9BFKWNo"))){
			 	arr = Web.getIntArrByStringlist( (List<String>)form.get("fv9BFKWNo"));
			}
			
			Map<String,Integer> lichengbeiNum = Web.getLCBNum(request, arr);
			
			int vffNum =  lichengbeiNum.get("vffNum");//柱子个数
			int pvsNum = lichengbeiNum.get("pvsNum");//柱子个数
			int osNum =  lichengbeiNum.get("osNum");//柱子个数
			int sopNum =  lichengbeiNum.get("sopNum");//柱子个数
			
			double temp0 = 0;
			int temp1 = 0;
			int temp2 = 0;
			int temp3 = 0;
			
			if(vffNum > 0){
				temp0 = preNum;
			}
			if(pvsNum > 0){
				temp1 = preNum + vffNum;
			}
			if(osNum > 0){
				temp2 = preNum + vffNum + pvsNum ;
			}
			if(sopNum > 0){
				temp3 = preNum + vffNum + pvsNum + osNum ;
			}
%>
		

		<script type="text/javascript">
		var chart1;
		var chart2;
		$(document).ready(function() {
				chart1 = new Highcharts.Chart({
				chart: {
					renderTo: 'chart1',
					defaultSeriesType: 'column'
				},
				title: {
					text: 'LC BMG-Teile von ES Teileliste',
					align: 'left',
					style: {
						color: 'black'
					}
				},
				xAxis: {
					lineColor:'black',
					tickPosition:'inside',
					tickColor:'black',
					categories: <%=cat%>,
					labels:{
						rotation: -45,
						enabled:true,
						y:20,
						style:{
							fontSize:'10px',
							color:'black'
						}
					}
				},
				yAxis: {
					min: 0,
					gridLineWidth: 0,
					lineWidth:1,
					lineColor:'black',
					tickWidth:1,
					tickPosition:'inside',
					tickColor:'black',
					title: {
						text: ''
					},
					labels:{
						style:{
							color:'black'
						}
					}
				},
				tooltip: {
					formatter: function() {
						return '<b>'+ this.x +'</b><br/>'+
							 this.series.name +': '+ this.y +'<br/>'+
							 'Total: '+ this.point.stackTotal;
					}
				},
				plotOptions: {
					column: {
						stacking: 'normal',
						borderColor: 'black',
				//		pointWidth:33,
						shadow:false,
						dataLabels: {
							enabled: true,
							style : {
								fontWeight: 'bold',
								fontSize:'12px'
							},
							color: 'white',
							formatter: function() {
								if (this.y == 0 || this.y == 0.0 || this.y == null) {
									return '';
								}
								return this.y + '';
							}
					//		rotation: -90,
					//		x:5
						}
					}
				},
				legend: {
					layout: 'vertical',
					align: 'right',
					verticalAlign: 'middle',
					borderWidth: 1,
					shadow:false,
					enabled:true
				},
			    series: [{
					name: 'B-Freigaben Soll',
					data: <%=BFreiSoll%>,
					color: '#009C0E',
					type: 'column',
					dashStyle: 'dash',
					marker: {enabled: false},
					lineWidth: 1,
					shadow: false,
					enableMouseTracking: false,
					dataLabels: {
						enabled: true,
						style : {
							fontSize:'10px'
						},
						color: 'black'
					}
				},{
					name: 'in arbeit',
					data: <%=BFInArbeirt%>,
					color: '#F9A700',
					type: 'column',
					dashStyle: 'dash',
					marker: {enabled: false},
					lineWidth: 1,
					shadow: false,
					enableMouseTracking: false,
					dataLabels: {
						enabled: true,
						style : {
							fontSize:'10px'
						},
						color: 'black'
					}
				}, {
					name: 'AWE',
					data: <%=BFAWE%>,
					color: '#FFFFCC',
					type: 'column',
					dashStyle: 'dash',
					marker: {enabled: false},
					lineWidth: 1,
					shadow: false,
					enableMouseTracking: false,
					dataLabels: {
						enabled: true,
						style : {
							fontSize:'10px'
						},
						color: 'black'
					}
				}
<%if(vffNum > 0){%>
				,{
					data: [[<%=temp0-1%> + 0.5, -20], [<%=temp0-1%> + 0.5001, <%=total%>]],
		//			color: 'black',
					dashStyle: 'dash',
					lineWidth: 2,
					marker: {enabled: false},
					shadow: false,
					showInLegend: false,
					enableMouseTracking: false,
					type: 'line',
					name :"VFF",
					dataLabels: {
						enabled: true,
						formatter: function() {
							return "<B>VFF</B>";
						}
					}
				}
<%}%><%if(pvsNum > 0){%>
				,{
					data: [[<%=temp1 - 1%> + 0.5, -20], [<%=temp1 - 1%> + 0.5001, <%=total%>]],
		//			color: 'black',
					dashStyle: 'dash',
					lineWidth: 2,
					marker: {enabled: false},
					shadow: false,
					showInLegend: false,
					enableMouseTracking: false,
					type: 'line',
					name :"PVS",
					dataLabels: {
						enabled: true,
						formatter: function() {
							return "<B>PVS</B>";
						}
					}
				}
<%}%><%if(osNum > 0){%>
				, {
					data: [[<%=temp2 - 1%> + 0.5, -20], [<%=temp2 - 1%> + 0.5001, <%=total%>]],
		//			color: 'black',
					dashStyle: 'dash',
					lineWidth: 2,
					marker: {enabled: false},
					shadow: false,
					showInLegend: false,
					enableMouseTracking: false,
					type: 'line',
					name :"0-S",
					dataLabels: {
						enabled: true,
						formatter: function() {
							return "<B>0-S</B>";
						}
					}
				}
<%}%><%if(sopNum > 0){%>
				, {
					data: [[<%=temp3-1%> + 0.5, -20], [<%=temp3-1%> + 0.5001, <%=total%>]],
		//			color: 'black',
					dashStyle: 'dash',
					lineWidth: 2,
					marker: {enabled: false},
					shadow: false,
					showInLegend: false,
					enableMouseTracking: false,
					type: 'line',
					name :"SOP",
					dataLabels: {
						enabled: true,
						formatter: function() {
							return "<B>SOP</B>";
						}
					}
				}
<%}%>
				]
			});

		});
		</script>
</head>
<body>		
	<div id="container">
		<div id="nr">
		<div id="top">
			<div class="fl"><%=status_left %></div>
			<div class="fr"><%=status_right %></div>
			<h1><%=title %></h1>
		</div>
	
		<div id="content">
			<div id="chart1" style="width: 900px; height: 520px; float: left; margin: 30px 30px auto; "></div>
		</div>
		<%@ include file="/app/pep/include/foot.jsp"%>
	</div>	
	
</body>
</html>