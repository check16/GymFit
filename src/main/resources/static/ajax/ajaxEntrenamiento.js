$(document).ready(function() {
	eliminarEntreno();
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
			}
		});
	});
}