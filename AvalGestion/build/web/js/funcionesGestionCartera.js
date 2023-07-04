
$(document).ready(function () {
    
    var now = new Date();
    var day = ("0" + now.getDate()).slice(-2);
    var month = ("0" + (now.getMonth() + 1)).slice(-2);
    var today = now.getFullYear()+"-"+(month)+"-"+(day) ;
    
    var hdDesde = document.getElementById("hdDesde").value;
    var hdHasta = document.getElementById("hdHasta").value;
    
    if (hdDesde == '') {
        $('#dtpFechaDesde').val(today);
    } else {
        $('#dtpFechaDesde').val(hdDesde);
    }
    
    if (hdHasta == '') {
      $('#dtpFechaHasta').val(today);  
    } else {
        $('#dtpFechaHasta').val(hdHasta);
    }
    
    //$('#dtpFechaDesde').val(today);
    //$('#dtpFechaHasta').val(today);
        
    $('#tablaGestionCarteras').DataTable({
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

    $('#tablaGestionDeudores').DataTable({
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
    
//    $("#btnBuscar").click(function (e) {
//        e.preventDefault();
//        
//        var nombreUsuario  = document.getElementById("txtnombreUsuario").value;
//        var idCartera = document.getElementById("cboCartera").value;
//        var BuscarPor = document.getElementById("cboBuscarPor").value;
//        var EncontrarPor = document.getElementById("txtEncontrarPor").value;
//        
//        var itemsPorPage = 20;
//        var currentPage = 1;
//        var totalItems = 0;
//        var totalPages = 0;
//        
//        var Mensaje = "";
//        var result;
//        
//        if (nombreUsuario == '') {
//            Mensaje = "No hay usuario de consulta";
//        } else if (idCartera == '0') {
//            Mensaje = "No seleccionó Cartera";
//        } else if (BuscarPor == '0') {
//            Mensaje = "No seleccionó Tipo Búsqueda";
//        } else if (EncontrarPor == '') {
//            Mensaje = "No seleccionó Tipo Búsqueda";
//        }
//        
//        if (Mensaje != '') {
//            //swal("Validación", "Mensaje: " + Mensaje)
//            //swal("Validación", "Mensaje", "error");
//            alert("Selecciona información en: " + Mensaje);
//        }
//        else {
//            var url = "gd_gestioncarteraSRV?action=searchnegotiations&iCar=" +idCartera+ "&sPor='"+ BuscarPor+ "'&sEnc='"+EncontrarPor+"'";
//            $.ajax({
//            type: 'POST',
//            url: url,
//            async: true,
//            success: function (data) {
//                
//                result = data;
//                totalItems = result.length;
//                totalPages = Math.ceil(totalItems / itemsPorPage);
//                
//                if (totalItems > 0) {
//                    var startIndex = (currentPage -1) * itemsPorPage;
//                    var endIdex = Math.min(startIndex + itemsPorPage, data.length);
//                    
//                    )
//                }
//                
//               parent.location.href = "gd_gestioncarteraSRV?action=getmanagementportfolios" 
//                }
//            });
//            
//            
//        }
//    });
    
    //Initialize Select2 Elements
    $('.select2').select2();
    
    
    
});

function showModals(idCliente, idCartera, idTipoRespuesta) {

    var dDesde = document.getElementById("dtpFechaDesde").value;
    var dHasta = document.getElementById("dtpFechaHasta").value;
    
}