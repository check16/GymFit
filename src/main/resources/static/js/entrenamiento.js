$("#agregarEjercicio").on("click", function(e) {
	$.ajax({
		url : "/gymfit/home/entrenamiento/crearejercicio",
		method: "GET",
		success : function(respuesta) {
			var tr = "";
			tr += "<tr class='ejercicio'><td><input value=0 type='hidden'><select class='form-control select2'>";
			$.each(respuesta, function(indice, valor) {
				tr+="<option value='" + valor.idTipoEjercicio +"'>" + valor.nombreEjercicio + "</option>"
			});
			tr+="</select></td>";
			tr+="<td><input step='0.01' required class='form-control' min=0 type='number'value='0'></td>";
			tr+="<td><input step='1' required class='form-control' min=0 type='number'value='0'></td>";
			tr+="<td><a class='btn btn-danger btn borrarEjercicio'><i class='fa fa-trash'></i> Eliminar</a></td>";
			$('#tablaEjercicios > tbody:last-child').append(tr);
			
			$('#tablaEjercicios tbody tr:last').find('select').select2();
			modificarIndicesTabla();
		}
	});
	
});

function modificarIndicesTabla() {
	$('#tablaEjercicios tr').each(function (i) {
		var indice = i-1;
        if (i === 0)
            return;
        var ejercicio = $(this).find('select');
        var idEjercicio = $(this).find('input[type=hidden]');
        var numeroInput = $(this).find('input[type=number]');
        ejercicio.eq(0).attr({name: 'ejercicios[' + indice + '].tipoEjercicio.idTipoEjercicio'});
        numeroInput.eq(0).attr({name: 'ejercicios[' + indice + '].cargaMax'});
        numeroInput.eq(1).attr({name: 'ejercicios[' + indice + '].totalRepeticiones'});
        idEjercicio.eq(0).attr({name: 'ejercicios[' + indice + '].idEjercicio'});
    });
	
}

$(document).on('click','.borrarEjercicio', function(){
    $(this).parents('tr').remove();
    modificarIndicesTabla();
});