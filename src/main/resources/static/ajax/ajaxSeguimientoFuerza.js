var colorGeneradoBorde;
function evolucionFuerzaUsuarioIntervalodias(inicio, fin) {

	$.ajax({
		url : "/gymfit/home/seguimientoPeso/evolucionFuerzaIntervalo",
		method : "GET",
		data : {
			inicio : inicio,
			fin : fin,
			idEjercicio : $("#ejercicioSelect2 option:selected").val()
		},
		success : function(respuesta) {
			procesarGraficoBarras(respuesta);
		}
	});
}

function procesarGraficoBarras(respuesta) {
	$('#cajaBarras').show();
	if (respuesta.valores.length == 0) {
		$(".chart")
				.html(
						"<h4 class='text-red'>No existen datos de fuerza para ese ejercicio y fecha</h4>")
	} else {

		$('#areaChart').remove();
		$(".chart").empty();
		$('.chart')
				.append(
						"<canvas id='areaChart' style='height: 250px; width: 788px;'	height='250' width='788'>");
		var barChartCanvas = $("#areaChart").get(0).getContext("2d");
		var opciones = {

			scales : {
				yAxes : [ {
					scaleLabel : {
						display : true,
						labelString : 'Carga Max. (Kg.)'
					},
					stacked : true,
					ticks : {
						beginAtZero : true
					}
				} ],
				xAxes : [ {
					scaleLabel : {
						display : true,
						labelString : 'Fecha'
					},
					stacked : true
				} ]
			}
		};
		var datos = {
			labels : respuesta.etiquetas,
			datasets : [ {
				label : "Carga Max. (Kg)",
				backgroundColor : generarColoresRgba(respuesta.valores),
				borderColor : colorGeneradoBorde,
				borderWidth : 1,
				data : respuesta.valores,
			},
			{
		        label: "Nº Repeticiones",
		        type: "line",
		        fill : false,
		        lineTension : 0.1,
				borderCapStyle : 'round',
				borderColor : "rgba(255,0,0,0.7)",
				borderDash : [],
				borderDashOffset : 0.0,
				borderJoinStyle : 'miter',
				pointBorderColor : "rgba(75,192,192,1)",
				pointBackgroundColor : "#fff",
				pointBorderWidth : 7,
				pointHoverRadius : 6,
				pointHoverBackgroundColor : "rgba(218, 44, 44,1)",
				pointHoverBorderWidth : 1.5,
				pointRadius : 1,
				pointHitRadius : 20,
				data : respuesta.valores2,
				spanGaps : true,
		    }]
		};

		var barChart = new Chart(barChartCanvas, {
			type : 'bar',
			data : datos,
			options : opciones
		});
	}
}

function generarColoresRgba(valores) {
	var r;
	var g;
	var b;
	var a = 0.2;
	var colores = [];
	colorGeneradoBorde = [];
	for (var i = 0; i < valores.length; i++) {
		r = Math.floor(Math.random() * 255);
		g = Math.floor(Math.random() * 255);
		b = Math.floor(Math.random() * 255);
		colores.push("rgba(" + r + "," + g + "," + b + "," + a + ")");
		colorGeneradoBorde.push("rgba(" + r + "," + g + "," + b + ",1" + ")");
	}

	return colores;
}
