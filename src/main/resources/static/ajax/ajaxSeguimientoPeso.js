$(document).ready(function() {
	evolucionPesoUsuario30dias();
	
});

function evolucionPesoUsuario30dias() {

	$.ajax({
		url : "/gymfit/home/seguimientoPeso/evolucionPeso30",
		method : "GET",
		success : function(respuesta) {
			procesarGrafico(respuesta);
		}
	});
}

function procesarGrafico(respuesta) {
	if (respuesta.valores.length == 0) {
		$(".chart").html("<h3 class='text-red'>No existen datos registrados de peso</h3>")
	} else {
		var areaChartCanvas = $("#areaChart").get(0).getContext("2d");
		var areaChart = new Chart(areaChartCanvas);

		var areaChartData = {
			labels : respuesta.etiquetas,
			datasets : [ {
				label : "Peso",
				fillColor : "rgba(60,141,188,0.9)",
				strokeColor : "rgba(60,141,188,0.8)",
				pointColor : "#1DD1C2",
				pointStrokeColor : "rgba(3, 154, 141,1)",
				pointHighlightFill : "#fff",
				pointHighlightStroke : "rgba(60,141,188,1)",
				data : respuesta.valores
			} ]
		};

		var areaChartOptions = {
			// Boolean - If we should show the scale at all
			showScale : true,
			// Boolean - Whether grid lines are shown across the chart
			scaleShowGridLines : true,
			// String - Colour of the grid lines
			scaleGridLineColor : "rgba(0,0,0,.1)",
			// Number - Width of the grid lines
			scaleGridLineWidth : 1,
			// Boolean - Whether to show horizontal lines (except X axis)
			scaleShowHorizontalLines : true,
			// Boolean - Whether to show vertical lines (except Y axis)
			scaleShowVerticalLines : true,
			// Boolean - Whether the line is curved between points
			bezierCurve : true,
			// Number - Tension of the bezier curve between points
			bezierCurveTension : 0.3,
			// Boolean - Whether to show a dot for each point
			pointDot : true,
			// Number - Radius of each point dot in pixels
			pointDotRadius : 4,
			// Number - Pixel width of point dot stroke
			pointDotStrokeWidth : 1,
			// Number - amount extra to add to the radius to cater for hit
			// detection outside the drawn point
			pointHitDetectionRadius : 20,
			// Boolean - Whether to show a stroke for datasets
			datasetStroke : true,
			// Number - Pixel width of dataset stroke
			datasetStrokeWidth : 2,
			// Boolean - Whether to fill the dataset with a color
			datasetFill : false,
			// String - A legend template
			legendTemplate : "<ul class=\"<%=name.toLowerCase()%>-legend\"><% for (var i=0; i<datasets.length; i++){%><li><span style=\"background-color:<%=datasets[i].lineColor%>\"></span><%if(datasets[i].label){%><%=datasets[i].label%><%}%></li><%}%></ul>",
			// Boolean - whether to maintain the starting aspect ratio or not
			// when responsive, if set to false, will take up entire container
			maintainAspectRatio : true,
			// Boolean - whether to make the chart responsive to window resizing
			responsive : true,
		};
		// Create the line chart
		areaChart.Line(areaChartData, areaChartOptions);
	}

}
