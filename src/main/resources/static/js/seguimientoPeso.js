var fechaInicio;
var fechaFin;
$(document).ready(function() {
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

$('#btnFiltrar').on("click", function() {
	console.log("Fecha de inicio: " + fechaInicio + " || Fecha fin: " + fechaFin);
	evolucionPesoUsuarioIntervalodias(fechaInicio, fechaFin);
})




