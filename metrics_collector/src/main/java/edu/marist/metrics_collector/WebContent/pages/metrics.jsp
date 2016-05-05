<%@ page language="java" pageEncoding="UTF-8"%>
<%@ page import="java.sql.*"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<title>Metrics Collector</title>
<meta name="viewport" content="width=device-width, initial-scale=1.0">
<!-- å¼•å…¥ Bootstrap -->
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
                                        <%
                                        try
                                        {
                                        Class.forName("org.postgresql.driver");
                                        String username="postgres";
                                        String password="metricsDb";
                                        String database="jdbc:postgresql:MetricCollection";
                                        String query="SELECT * FROM metrics";
                                        Connection conn = DriverManager.getConnection(database, username, password);
                                        Statement stmt = conn.createStatement();
                                        ResultSet rs=stmt.executeQuery(query);
                                        while(rs.next())
                                        {
                                        %>
						<tr>
							<td><%=rs.getString("pid") %></td>
							<td><%=rs.getString("processname") %></td>
                     <td><%=rs.getString("machinename") %></td>
                     <td><%=rs.getString("parentpid") %></td>
                     <td><%=rs.getString("totalsize") %></td>
                     <td><%=rs.getString("dateofcreation") %></td>
						</tr>
					</tbody>
                                        <%
                                        }
                                        rs.close();
                                        stmt.close();
                                        conn.close();
                                        }
                                        catch(Exception e)
                                        {
                                        e.printStackTrace();
                                        }
                                        %>
				</table>

			</div>
		</div>
	</div>

	<script>
		$(document).ready(function() {
			$('#datatable').DataTable();
		});


		var interval = 15;

		function changeInterval() {
			var newInterval = parseInt(document.getElementById("intervalInput").value);
			interval = newInterval;
		}

      function staticBool() {
         this.state = !this.state || true;
      }

      function updateMemory(var memoryVal) {
         var nav = document.getElementById("Nav");
         var memory = document.createElement("li");
         memory.innerHTML = "Max Memory: " + memoryVal;
         nav.appendChild(memory);
      }

		/*function refresh() {
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
                  
                  if(staticBool()) {
                     updateMemory(metric.totalram);
                     staticBool();
                  }

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

					$(document).ready(function() {
						$('#datatable').DataTable();
					});
				}
			};
			request.open("GET", "../MetricsCollector", true);
			request.send(null);

		}*/
//		setTimeout('refresh()', interval * 1000); //æŒ‡å®š1ç§’åˆ·æ–°ä¸€æ¬¡
	</script>
</body>
</html>