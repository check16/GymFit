/**
 * Variable global con la tabla para el DataTable
 */
var miTabla;

/**
 * Cuando el documento este cargado, se realizan las funciones de
 * mostrar el grafico de la evolucion del peso de los últimos 30 días y 
 * de la tabla
 */
$(document).ready(
		function() {
			evolucionPesoUsuariodias(30);
			evolucionPesoUsuarioTabla(30);

			moment.updateLocale('es', {
				months : [ "Enero", "Febrero", "Marzo", "Abril", "Mayo",
						"Junio", "Julio", "Agosto", "Septiembre", "Octubre",
						"Noviembre", "Diciembre" ]
			});

		});

/**
 * Funcion para la peticion de la evolucion gráfica del peso del usuario X dias antes del dia actual
 * @param dias el numero de dias antes del dia actual para la evolucion del peso
 */
function evolucionPesoUsuariodias(dias) {

	$.ajax({
		url : "/gymfit/home/seguimientoPeso/evolucionPeso",
		method : "GET",
		data : {
			dias : dias
		},
		success : function(respuesta) {
			procesarGrafico(respuesta);
		}
	});
}

/**
 * Funcion para la peticion de la evolucion del peso del usuario X dias antes del dia actual para la tabla
 * @param dias el numero de dias antes del dia actual para la evolucion del peso
 */
function evolucionPesoUsuarioTabla(dias) {
	$.ajax({
		url : "/gymfit/home/seguimientoPeso/evolucionPesoTabla",
		method : "GET",
		data : {
			dias : dias
		},
		success : function(respuesta) {
			procesarTabla(respuesta);
		}
	});
}

/**
 * Funcion para la peticion de la evolucion del peso del usuario X dias antes del dia actual para la tabla
 * @param dias el numero de dias antes del dia actual para la evolucion del peso
 */
function evolucionPesoUsuarioIntervalodias(inicio, fin) {

	$.ajax({
		url : "/gymfit/home/seguimientoPeso/evolucionPesoIntervalo",
		method : "GET",
		data : {
			inicio : inicio,
			fin : fin
		},
		success : function(respuesta) {
			procesarGrafico(respuesta);
		}
	});

	$.ajax({
		url : "/gymfit/home/seguimientoPeso/evolucionPesoIntervaloTabla",
		method : "GET",
		data : {
			inicio : inicio,
			fin : fin
		},
		success : function(respuesta) {
			procesarTabla(respuesta);
		}
	});
}

/**
 * Metodo para procesar el Grafico de la evolucion del peso
 * @param respuesta la respuesta ajax con los datos para el grafico
 */
function procesarGrafico(respuesta) {
	if (respuesta.valores.length == 0) {
		$(".chart")
				.html(
						"<h4 class='text-red'>No existen datos registrados de peso</h4>")
	} else {
		$('#areaChart').remove();
		$(".chart").empty();
		$('.chart')
				.append(
						"<canvas id='areaChart' style='height: 250px; width: 788px;'	height='250' width='788'>");
		var areaChartCanvas = $("#areaChart").get(0).getContext("2d");
		var opciones = {

			scales : {
				yAxes : [ {
					scaleLabel : {
						display : true,
						labelString : 'Peso (Kg.)'
					}
				} ],
				xAxes : [ {
					scaleLabel : {
						display : true,
						labelString : 'Fecha'
					}
				} ]
			}
		};
		var datos = {
			labels : respuesta.etiquetas,
			datasets : [ {
				label : "Peso (Kg.)",
				fill : true,
				lineTension : 0.1,
				backgroundColor : "rgba(60,141,188,0.15)",
				borderColor : "rgba(60,141,188,0.9)",
				borderCapStyle : 'round',
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
				pointHitRadius : 40,
				data : respuesta.valores,
				spanGaps : false,
			} ],

		};

		var myLineChart = new Chart(areaChartCanvas, {
			type : 'line',
			data : datos,
			options : opciones
		});

	}
}

/**
 * Funcion para procesar la tabla con los datos de la respuesta ajax
 * @param datos los datos para generar la tabla.
 */
function procesarTabla(datos) {
	if (!$.fn.DataTable.isDataTable('#dataTable')) {
		miTabla = $('#dataTable').DataTable({

			"language" : {
				"lengthMenu" : "Mostrando _MENU_ registros por página",
				"search" : "Buscar: ",
				"zeroRecords" : "Nada encontrado. Lo sentimos",
				"info" : "Mostrando página _PAGE_ de _PAGES_",
				"infoEmpty" : "No existen datos disponibles",
				"infoFiltered" : "(De un total de _MAX_ registros)",
				"paginate" : {
					"previous" : "Anterior",
					"next" : "Siguiente"
				}
			},
			"paging" : true,
			data : datos,
			columns : [ {
				data : 'pesoValor'
			}, {
				data : 'fechaPeso',

				render : function(data, type, row) {
					return moment(data).format("DD/MM/YYYY");
				}
			}, {
				data : 'iconoEstado',

			}, {
				data : 'fechaPeso',

			} ],
			"lengthChange" : true,
			"searching" : true,
			"ordering" : true,
			"info" : true,
			"autoWidth" : true,
			'columnDefs' : [ {
				'sortable' : false,
				'searchable' : false,
				'visible' : false,
				'type' : 'num',
				'targets' : [ 3 ]
			}, {
				'orderData' : [ 3 ],
				'targets' : [ 1 ]
			}, ],
			"order" : [ [ 1, "desc" ] ]
		});
	} else {
		miTabla.clear().draw();
		miTabla.rows.add(datos);
		miTabla.columns.adjust().draw();
	}

}
