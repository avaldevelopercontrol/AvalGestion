
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
    
    dom: "Bfrtip",
                buttons: [
                    {
                        extend: "excelHtml5",
                        title: "Excel",
                        text:"Exportar a excel"
                    },
                    {
                        extend: "pdfHtml5",
                        title: "PDF",
                        text: "Exportar a PDF"
                    }
                ],
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
    dom: "Bfrtip",
                buttons: [
                    {
                        extend: "excelHtml5",
                        title: "Excel",
                        text:"Exportar a excel"
                    },
                    {
                        extend: "pdfHtml5",
                        title: "PDF",
                        text: "Exportar a PDF"
                    }
                ],
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
    
    //Initialize Select2 Elements
    $('.select2').select2();
    
});

function fn_SeleccionaCartera() {
    
    var nId_Cartera = document.getElementById("cboCartera").value;
    if (nId_Cartera != null) {
        
        $.post('gd_gestioncarteraSRV?action=getportfolio', 
        {   
            nId_Cartera: nId_Cartera
        },
        function (response) {
            var fInicio = response.dFecIniProceso;
            var fFin = response.dFecFinProceso;
            $('#dtpFechaDesde').val(fInicio);
            $('#dtpFechaHasta').val(fFin);
            
        });
    } else {
        alert("Seleccionar Cartera");
    }
}

function CargaGestiones(nId_OpeCodOut, cNombre_OpeCodCliOut) {

    var dDesde = document.getElementById("dtpFechaDesde").value;
    var dHasta = document.getElementById("dtpFechaHasta").value;
    var nId_Cartera = document.getElementById("cboCartera").value;
    
    $('#nomTipoGestion').html(cNombre_OpeCodCliOut);
    
    if ($.fn.DataTable.isDataTable('#tablaGestionTipificacion')) {
        $('#tablaGestionTipificacion').DataTable().destroy();
    }
    $('#tablaGestionTipificacion tbody').empty();
    
    $.post('gd_gestioncarteraSRV?action=getmanagementtypifications', 
    {   
        nId_OpeCodOut: nId_OpeCodOut,
        nId_Cartera: nId_Cartera,
        dDesde: dDesde,
        dHasta: dHasta
    },
    function (response) {
        var tabla = "";
        response.forEach(function (h){
                        tabla += '<tr>';
                        tabla += '<td>' + h.cNombre_OpeCodCliOutN2 + '</td>';
                        tabla += '<td>' + h.cUsr_Nombres + '</td>';
                        tabla += '<td>' + h.DuracionSegundo + '</td>';
                        tabla += '<td>' + h.cDoc_Numero + '</td>';
                        tabla += '<td>' + h.cPers_Nombres + '</td>';
                        tabla += '</tr>';
                    });
                    $('#tbodyGestionesAll').html(tabla);
                    
        $('#tablaGestionTipificacion').DataTable({
            dom: "Bfrtip",
                buttons: [
                    {
                        extend: "excelHtml5",
                        title: "Excel",
                        text:"Exportar a excel"
                    },
                    {
                        extend: "pdfHtml5",
                        title: "PDF",
                        text: "Exportar a PDF"
                    }
                ],
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
            }
        });
    });
       
}