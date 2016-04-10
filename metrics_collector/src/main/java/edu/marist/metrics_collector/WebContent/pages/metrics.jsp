<%@ page language="java" pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<title>Metrics Collector</title>
<meta name="viewport" content="width=device-width, initial-scale=1.0">
<!-- 引入 Bootstrap -->
<link href="../css/bootstrap.min.css" rel="stylesheet">
<link href="../css/jquery-ui.css" rel="stylesheet">
<link href="../css/dataTables.jqueryui.min.css" rel="stylesheet">

<script type="text/javascript" src="../js/jquery.min.js"></script>
<script type="text/javascript"
	src="https://ajax.googleapis.com/ajax/libs/jqueryui/1.8.23/jquery-ui.min.js"></script>
<script type="text/javascript" src="../js/jquery.dataTables.min.js"></script>
<script type="text/javascript" src="../js/dataTables.jqueryui.min.js"></script>
<script src="http://d3js.org/d3.v3.min.js"></script>
<script
	src="http://labratrevenge.com/d3-tip/javascripts/d3.tip.v0.6.3.js"></script>
<script src="../js/bootstrap.min.js"></script>
<script src="../js/echarts-plain-map.js"></script>
</head>

<body>
	<div class="container-fluid"
		style="padding-left: 0px; padding-right: 0px;" id="container">

		<nav class="navbar navbar-default" role="navigation"
			style="padding-right: 20px; margin-bottom: 0px">
		<div class="navbar-header">
			<a class="navbar-brand" href="#">Metrics Collector</a>
		</div>
		<div>
			<form class="navbar-form navbar-right" role="search">
				<div class="form-group">
					<input type="text" class="form-control"
						placeholder="update frequency" id="intervalInput" onclick = "changeInterval()">
				</div>
				<button type="submit" class="btn btn-default">Submit</button>
			</form>
		</div>
		</nav>


		<div class="row-fluid" style="padding-left: 0px; padding-right: 0px;"
			id="row">
			<div class="col-md-2"
				style="height: inherit; padding-left: 0px; padding-right: 0px; background-color: #f7f7f7;">
				<ul class="nav nav-pills nav-stacked" style="height: 100%;">
					<li class="active"><a href="#">Metrics</a></li>
					<li><a href="#">outline</a></li>
					<li><a href="#">details</a></li>
				</ul>
			</div>
			<div class="col-md-10" id="column" style="padding-top: 10px">

				<table id="datatable" class="display" cellspacing="0" width="100%">
					<thead>
						<tr>
							<th>PID</th>
							<th>Process Name</th>
							<th>Machine Name</th>
							<th>Parent PID</th>
							<th>Total Size</th>
							<th>Date of Creation</th>
						</tr>
					</thead>
					<tbody id="tableBody">

						<tr>
							<td>0001</td>
							<td>text editor</td>
							<td>my computer</td>
							<td>0003</td>
							<td>10094</td>
							<td>20160406</td>
						</tr>

						<tr>
							<td>0002</td>
							<td>photo viewer</td>
							<td>my computer</td>
							<td>0004</td>
							<td>33557</td>
							<td>20160405</td>
						</tr>
					</tbody>
				</table>

				<div id="chart"
					style="height: 400px; border: 1px solid #ccc; margin-top: 20px;"></div>
			</div>
		</div>
	</div>

	<script>
		$(document).ready(function() {
			$('#datatable').DataTable();
		});

		var myChart = echarts.init(document.getElementById('chart'));
//		var times = new Array(0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0,
//				0, 0, 0, 0);
		var times = new Array(2, 3, 5, 4, 2, 7, 4, 8, 4, 2, 5, 8, 5, 3, 1, 4,
				6, 5, 9, 8);
		var interval = 15;

		function changeInterval() {
			var newInterval = parseInt(document.getElementById("intervalInput").value);
			interval = newInterval;
		}

		function refreshChart(times) {
			myChart.setOption({
				title : {
					text : 'Size for all processes',
					textStyle : {
						fontSize : 12
					}
				},
				tooltip : {
					trigger : 'axis'
				},
				grid : {
					x : 55,
					x2 : 30
				},
				toolbox : {
					show : false,
					feature : {
						magicType : {
							show : true,
							type : [ 'line', 'bar' ]
						},
						restore : {
							show : true
						},
						saveAsImage : {
							show : true
						}
					}
				},
				calculable : true,
				xAxis : [ {
					type : 'category',
					boundaryGap : false,
					data : [ '0s', interval + 's', interval*2 + 's',
							interval*3 + 's', interval*4 + 's', interval*5 + 's',
							interval*6 + 's', interval*7 + 's', interval*8 + 's',
							interval*9 + 's', interval*10 + 's', interval*11 + 's',
							interval*12 + 's', interval*13 + 's', interval*14 + 's',
							interval*15 + 's', interval*16 + 's', interval*17 + 's',
							interval*18 + 's', interval*19 + 's' ]
				} ],
				yAxis : [ {
					type : 'value',
					axisLabel : {
						rotate : 50
					}
				} ],
				series : [ {
					name : '刷卡次数',
					type : 'line',
					smooth : true,
					itemStyle : {
						normal : {
							areaStyle : {
								color : 'rgba(138, 43, 226, 0.5)'
							}
						}
					},
					data : times
				} ]
			});
		}
		
		function temp()
		{
			refreshChart(times);
		}
		
		temp();

		function refresh() {
			var request = new XMLHttpRequest();
			request.onreadystatechange = function() {
				if (request.readyState == 4 && request.status == 200) {
					var res = request.responseText;
					var data = eval("(" + res + ")");
					var metrics = data.metrics;
					var total = 0;

					var table = document.getElementById("tableBody");
					while (table.hasChildNodes()) {
						table.removeChild(table.lastChild);
					}
					
					for (var i = 0; i < attributes.length; i++) {
						var metric = metrics[i];

						var line = document.createElement("tr");
						var pidCell = document.createElement("td");
						var nameCell = document.createElement("td");
						var machineCell = document.createElement("td");
						var parentCell = document.createElement("td");
						var sizeCell = document.createElement("td");
						var dateCell = document.createElement("td");

						pidCell.innerHTML = metric.PID;
						nameCell.innerHTML = metric.processname;
						machineCell.innerHTML = metric.machinename;
						parentCell.innerHTML = metric.parentPID;
						sizeCell.innerHTML = metric.totalsize;
						dateCell.innerHTML = metric.dateofcreation;

						line.appendChild(pidCell);
						line.appendChild(nameCell);
						line.appendChild(machineCell);
						line.appendChild(parentCell);
						line.appendChild(sizeCell);
						line.appendChild(dateCell);

						table.appendChild(line);
						total = total + parseInt(metric.totalsize);
					}

					for (var i = times.length - 1; i > 0; i--)
						times[i] = times[i - 1];
					times[0] = total;
					refreshChart(times);

					$(document).ready(function() {
						$('#datatable').DataTable();
					});
				}
			};
			request.open("GET", "../MetricsCollector", true);
			request.send(null);

		}
//		setTimeout('refresh()', interval * 1000); //指定1秒刷新一次
	</script>
</body>
</html>