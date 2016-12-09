/**
 * Variable global de la fecha de inicio
 */
var fechaInicio;

/**
 * Variable global de la fecha de fin
 */
var fechaFin;

/**
 * Cuando la pagina esté cargada se inicializa el plugin de rango de fechas DateRangePicker
 */
$(document).ready(function() {
	fechaInicio = moment().subtract(29, 'days').format("DD/MM/YYYY");
	fechaFin = moment().format("DD/MM/YYYY");
		
		$('#rangoFecha').daterangepicker({
			"autoApply": true,
			"locale": {
		        "format": "DD/MM/YYYY",
		        "separator": " - ",
		        "applyLabel": "Aplicar",
		        "cancelLabel": "Cancelar",
		        "fromLabel": "Desde",
		        "toLabel": "Hasta",
		        "customRangeLabel": "Custom",
		        "daysOfWeek": [
		            "Dom",
		            "Lun",
		            "Mar",
		            "Mie",
		            "Jue",
		            "Vie",
		            "Sab"
		        ],
		        "monthNames": [
		            "Enero",
		            "Febrero",
		            "Marzo",
		            "Abril",
		            "Mayo",
		            "Junio",
		            "Julio",
		            "Augosto",
		            "Septiembre",
		            "Octubre",
		            "Noviembre",
		            "Deciembre"
		        ],
		        "firstDay": 1
		    },
			startDate: moment().subtract(29, 'days')
		},
		//CallBack de RangeDatePicker
		function(inicio, fin) {
			fechaInicio = inicio.format("DD/MM/YYYY");
			fechaFin = fin.format("DD/MM/YYYY");
		}
		);
		
    });

/**
 * Funcion para el evento de click para filtrar por fecha la evolucion del peso
 */
$('#btnFiltrar').on("click", function() {
	evolucionPesoUsuarioIntervalodias(fechaInicio, fechaFin);
})





