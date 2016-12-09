/**
 * Cuando la pagina esté preparada asignamos la funcion de la 
 * modificacion de la clave al evento de submit del formulario
 */
$(document).ready(function() {
	modificarClave();
});

/**
 * Metodo para la peticion ajax de modificacion de la clave de usuario
 */
function modificarClave() {
	$("#formClave").submit(function(event) {
		event.preventDefault();
		$.ajax({
			url : "/gymfit/home/perfil/modificarclave",
			data: $("#formClave").serialize(),
			method: "POST",
			success : function(respuesta) {
				if (respuesta.length == 0) {
	                swal("Modificada", "La contraseña se modificó correctamente.", "success");
	                $('#errorClave').empty();
	                $("#formClave")[0].reset();
				} else {
					swal("Error", "La contraseña no se modificó correctamente, revise los campos", "error");
					$('#errorClave').empty();
					$.each( respuesta, function( key, value ) {						
						$.notify(respuesta[key].code, "error");
						});
				}
			}
		});
	});
}