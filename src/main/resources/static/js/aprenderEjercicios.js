/**
 * Cuando la página esté preparada se crea el DataTable para la tabla de aprender los ejercicios.
 * Para ello se usa tambien FancyBox en las imagenes y videos
 */
$(document).ready(function() {
	miTabla = $('#tablaEjercicios').DataTable({

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
		"columnDefs": [
		               { "width": "5%", "targets": 2 },
		               { "width": "25%", "targets": 1 },
		               { "width": "25%", "targets": 0 },
		               { "width": "25%", "targets": 3 }
		             ],
		"lengthChange" : true,
		"searching" : true,
		"ordering" : true,
		"info" : true,
		"autoWidth" : true,
		"order" : [ [ 0, "asc" ] ]
	});
	

	    $(".imgEjercicio").fancybox({
	    	openEffect	: 'elastic',
	    	closeEffect	: 'elastic',

	    	helpers : {
	    		title : {
	    			type : 'over'
	    		}
	    	}
	    });
	    
	    $('.fancybox-media').fancybox({
			openEffect  : 'none',
			closeEffect : 'none',
			helpers : {
				media : {}
			}
		});
});