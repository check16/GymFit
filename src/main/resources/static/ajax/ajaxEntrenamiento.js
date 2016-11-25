$(document).ready(function() {
	eliminarEntreno();
	eliminarEjercicio();
	miTabla = $('#tablaEntreno').DataTable({

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
		"lengthChange" : true,
		"searching" : true,
		"ordering" : true,
		"info" : true,
		"autoWidth" : true,
		'columnDefs' : [ {
			'sortable' : false,
			'searchable' : true,
			'visible' : true,
			'targets' : [1]
		},{
			'sortable' : false,
			'searchable' : false,
			'visible' : true,
			'targets' : [3]
		}],
		"order" : [ [ 0, "asc" ] ]
	});
});

function eliminarEntreno() {
	$(".eliminarEntreno").click(function(event) {
		event.preventDefault();
		swal({
			title : "¿Desea borrar el entrenamiento?",
			type : "warning",
			showCancelButton : true,
			confirmButtonColor : "#DD6B55",
			confirmButtonText : "Sí",
			cancelButtonText : "No",
			closeOnConfirm : false,
			closeOnCancel : true
		}, function(isConfirm) {
			if (isConfirm) {
				$.ajax({
					url : $(event.target).attr("href"),
					method: "GET",
					success : function() {
							var tr = $(event.target).closest("tr");
			                tr.css("background-color","#287ca0");
			                tr.fadeIn(1000).fadeOut(300, function(){
			                tr.remove();})
			                swal("Eliminado", "El entrenamiento fue eliminado correctamente.", "success");
					}
				});
			}else {
				swal("Error", "Se produjo un error al eliminar el entrenamiento", "warning");
			}
		});
	});
}

function eliminarEjercicio() {
	$(".eliminarEjercicio").click(function(event) {
		event.preventDefault();
		$.ajax({
			url : $(event.target).attr("href"),
			method: "GET",
			success : function(respuesta) {
				if (respuesta) {
					var tr = $(event.target).closest("tr");
	                tr.css("background-color","#287ca0");
	                tr.fadeIn(1000).fadeOut(300, function(){
	                tr.remove();})
	                swal("Eliminado", "El ejercicio fue eliminado correctamente.", "success");
				} else {
					swal("Error", "El ejercicio NO se eliminó.", "warning");
				}
					
			}
		});
	});
}