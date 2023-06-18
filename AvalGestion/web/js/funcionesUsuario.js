$(document).ready(function () {
    
    $('#tablaUsuarios').DataTable({
    language: {
        "decimal": "",
        "emptyTable": "No hay información",
        "info": "Mostrando _START_ a _END_ de _TOTAL_ Entradas",
        "infoEmpty": "Mostrando 0 to 0 of 0 Entradas",
        "infoFiltered": "(Filtrado de _MAX_ total entradas)",
        "infoPostFix": "",
        "thousands": ",",
        "lengthMenu": "Mostrar _MENU_ Entradas",
        "loadingRecords": "Cargando...",
        "processing": "Procesando...",
        "search": "Buscar:",
        "zeroRecords": "Sin resultados encontrados",
        "paginate": {
            "first": "Primero",
            "last": "Ultimo",
            "next": "Siguiente",
            "previous": "Anterior"
        }
    }});
    
    $("tr #deleteUsuario").click(function (e) {
        e.preventDefault();
        var idCliente = $(this).parent().find('#idUsuarioEliminar').val();
        swal({
            title: "¿Esta seguro de eliminar?",
            text: "Una vez eliminado, deberá agregar de nuevo!",
            type: "warning",
            showCancelButton: true,
            confirmButtonClass: "btn-danger",
            confirmButtonText: "Si, eliminar!",
            cancelButtonText: "No, cancelar!",
            closeOnConfirm: false,
            closeOnCancel: false
        },
                function (isConfirm) {
                    if (isConfirm) {
                        console.log("Ingresa a Eliminar");
                        eliminarCliente(idCliente);
                        console.log("Termina Eliminado");
                        swal("Eliminado!", "El cliente se ha eliminado.", "success");
                        setTimeout(function () {
                           parent.location.href = "gd_usuarioSRV?accion=listarUsuarios" 
                        }, 2000);
                    } else {
                        swal("Cancelado", "Cancelaste la eliminación", "error");
                    }
                });
    });
    
    
});