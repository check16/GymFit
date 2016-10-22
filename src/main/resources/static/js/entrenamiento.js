$("#agregarEjercicio").on("click", function(e) {
	$('#tablaEjercicios tbody tr:last').clone().find('input[type=number], input[type=hidden]').val(0).end().appendTo($('#tablaEjercicios tbody'));
	modificarIndicesTabla();
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