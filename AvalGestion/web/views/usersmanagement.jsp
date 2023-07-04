<%@page import="javax.xml.ws.Response"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<jsp:useBean id="idCartera" scope="request" class="java.lang.String" />
<jsp:useBean id="cTipoBusqueda" scope="request" class="java.lang.String" />
<jsp:useBean id="msjValida" scope="request" class="java.lang.String" />

<jsp:useBean id="lstGesConNoCon" scope="request" class="java.util.ArrayList" /> 
<%@page import="Models.*"%>

<!DOCTYPE html>
<%
    if (session.getAttribute("gd_usuarioSession") != null ) {        
%>
<html lang="en">
    <head>
        <meta charset="utf-8">
        <meta name="viewport" content="width=device-width, initial-scale=1">
        <title>Sistema Gestión</title>
        <!-- Font Awesome -->
        <link rel="stylesheet" href="plugins/fontawesome-free/css/all.min.css">
        <!-- Ionicons -->
        <link rel="stylesheet" href="https://code.ionicframework.com/ionicons/2.0.1/css/ionicons.min.css">
        <!-- daterange picker -->
        <link rel="stylesheet" href="plugins/daterangepicker/daterangepicker.css">
        <!-- iCheck for checkboxes and radio inputs -->
        <link rel="stylesheet" href="plugins/icheck-bootstrap/icheck-bootstrap.min.css">
        <!-- Bootstrap Color Picker -->
        <link rel="stylesheet" href="plugins/bootstrap-colorpicker/css/bootstrap-colorpicker.min.css">
        <!-- Tempusdominus Bbootstrap 4 -->
        <link rel="stylesheet" href="plugins/tempusdominus-bootstrap-4/css/tempusdominus-bootstrap-4.min.css">
        <!-- Select2 -->
        <link rel="stylesheet" href="plugins/select2/css/select2.min.css">
        <link rel="stylesheet" href="plugins/select2-bootstrap4-theme/select2-bootstrap4.min.css">
        <!-- DataTables -->
        <link rel="stylesheet" href="plugins/datatables-bs4/css/dataTables.bootstrap4.min.css">
        <link rel="stylesheet" href="plugins/datatables-responsive/css/responsive.bootstrap4.min.css">
        <link rel="stylesheet" href="plugins/datatables-buttons/css/buttons.bootstrap4.min.css">
        <!-- Bootstrap4 Duallistbox -->
        <link rel="stylesheet" href="plugins/bootstrap4-duallistbox/bootstrap-duallistbox.min.css">
        <!-- Theme style -->
        <link rel="stylesheet" href="dist/css/adminlte.min.css">
        <link rel="shortcut icon" href="dist/img/aval_logo.jpg">
        <!-- Google Font: Source Sans Pro -->
        <link href="https://fonts.googleapis.com/css?family=Source+Sans+Pro:300,400,400i,700" rel="stylesheet">
    </head>
    <body class="hold-transition sidebar-mini layout-fixed">
        <div class="wrapper">
            <!-- Preloader -->
            <div class="preloader flex-column justify-content-center align-items-center">
                <img class="animation__shake" src="dist/img/aval_logo.jpg" alt="AvalPeru" height="60" width="60">
            </div>
            
            <!-- Navbar -->
            <nav class="main-header navbar navbar-expand navbar-white navbar-light">
                <!-- Left navbar links -->
                <ul class="navbar-nav">
                    <li class="nav-item">
                        <a class="nav-link" data-widget="pushmenu" href="#" role="button"><i class="fas fa-bars"></i></a>
                    </li>
                </ul>
                
                <!-- Right navbar links -->
                <ul class="navbar-nav ml-auto">
                    <!-- Navbar Right Menu -->
                    <div class="navbar-custom-menu">
                        <ul class="nav navbar-nav">
                            <!-- User Account Menu -->
                            <li class="dropdown user user-menu">
                                <!-- Menu Toggle Button -->
                                <a href="#" class="dropdown-toggle" data-toggle="dropdown">
                                    <!-- The user image in the navbar-->
                                    <img src="dist/img/user2-160x160.jpg" class="user-image" alt="User Image">
                                    <!-- hidden-xs hides the username on small devices so only the image appears. -->
                                    <span class="hidden-xs"> ${gd_usuarioSession.nombreUsuario}</span>
                                </a>
                                <ul class="dropdown-menu">
                                    <!-- The user image in the menu -->
                                    <li class="user-header">
                                        <img src="dist/img/user2-160x160.jpg" class="img-circle" alt="User Image">
                                        <p>                    
                                            Bienvenido - ${gd_usuarioSession.nombres} ${gd_usuarioSession.apellidos}
                                            <small>Usted es, ${gd_usuarioSession.nombreUsuario} </small>
                                        </p>
                                    </li>
                                    <!-- Menu Footer-->
                                    <li class="user-footer">
                                        <div class="pull-right">
                                            <form action="gd_usuarioSRV?action=signoff" method="POST">
                                                <input type="submit" name="signoff" value="Cerrar Session" class="btn btn-primary btn-block"/>
                                            </form>
                                        </div>
                                    </li>
                                </ul>
                            </li>
                        </ul>
                    </div>
                </ul>
            </nav>
            <!-- /.navbar -->
            <!-- Main Sidebar Container -->
            <aside class="main-sidebar sidebar-dark-primary elevation-4">
                <!-- Brand Logo -->
                <a href="#" class="brand-link">
                    <img src="dist/img/aval_logo.jpg" alt="AvalPeru" class="brand-image img-circle elevation-3" style="opacity: .8">
                    <span class="brand-text font-weight-light">Aval Gestión</span>
                </a>
                
                <!-- Sidebar -->
                <div class="sidebar">
                    <!-- Sidebar user panel (optional) -->
                    <div class="user-panel mt-3 pb-3 mb-3 d-flex">
                        <div class="image">
                            <img src="dist/img/user2-160x160.jpg" class="img-circle elevation-2" alt="User Image">
                        </div>
                        <div class="info">
                            <a href="#" class="d-block">${gd_usuarioSession.nombreUsuario}</a>
                        </div>
                    </div>
                        
                    <!-- Sidebar Menu -->
                    <nav class="mt-2">
                        <ul class="nav nav-pills nav-sidebar flex-column" data-widget="treeview" role="menu" data-accordion="false">
                            <!-- Add icons to the links using the .nav-icon class
                            with font-awesome or any other icon font library -->
                            <li class="nav-item">
                                <a href="#" class="nav-link">
                                    <i class="nav-icon fas fa-edit"></i>
                                    <p>
                                        Mantenimiento
                                        <i class="right fas fa-angle-left"></i>
                                    </p>
                                </a>
                                <ul class="nav nav-treeview">
                                    <li class="nav-item">
                                        <a href="gd_usuarioSRV?action=listusers" class="nav-link">
                                            <i class="far fa-user nav-icon"></i>
                                            <p>Usuarios</p>
                                        </a>
                                    </li>
                                    <li class="nav-item">
                                        <a href="av_clienteSRV?action=listclients" class="nav-link">
                                            <i class="far fa-building nav-icon"></i>
                                            <p>Clientes</p>
                                        </a>
                                    </li>
                                    <li class="nav-item">
                                        <a href="gd_usuarioclienteSRV?action=listUserClients" class="nav-link">
                                            <i class="far fa-id-badge nav-icon"></i>
                                            <p>Usuario - Clientes</p>
                                        </a>
                                    </li>
                                </ul>
                            </li>
                            <li class="nav-item menu-open">
                                <a href="#" class="nav-link active">
                                    <i class="nav-icon fas fa-paste"></i>
                                    <p>
                                        Gestión
                                        <i class="right fas fa-angle-left"></i>
                                    </p>
                                </a>
                                <ul class="nav nav-treeview">
                                    <li class="nav-item">
                                        <a href="gd_gestioncarteraSRV?action=getmanagementportfolios" class="nav-link active">
                                            <i class="far fa-calendar nav-icon"></i>
                                            <p>Llamadas</p>
                                        </a>
                                    </li>
                                </ul>
                            </li>
                        </ul>
                    </nav>
                    <!-- /.sidebar-menu -->
                </div>
                <!-- /.sidebar -->
            </aside>
            <!-- Content Wrapper. Contains page content -->
            <div class="content-wrapper">
                <!-- Content Header (Page header) -->
                <div class="content-header">
                    <div class="container-fluid">
                        <div class="row mb-2">
                            <div class="col-sm-6">
                                <h1 class="m-0">Gestión - LLamadas</h1>
                            </div><!-- /.col -->
                            <div class="col-sm-6">
                                <ol class="breadcrumb float-sm-right">
                                    <li class="breadcrumb-item"><a href="#">Gestión</a></li>
                                    <li class="breadcrumb-item active">Llamadas</li>
                                </ol>
                            </div><!-- /.col -->
                        </div><!-- /.row -->
                    </div><!-- /.container-fluid -->
                </div>
                <!-- /.content-header -->
                
                <!-- Main content -->
                <section class="content">
                    <div class="container-fluid">
                        <div class="row">
                            <div class="col-sm-12">
                                
                                <!-- Form Element sizes -->
                                <div class="card card-info">
                                    
                                    <div class="card-header">
                                        <h3 class="card-title">Resultado por Cartera</h3>
                                        <div class="card-tools">
                                            <button type="button" class="btn btn-tool" data-card-widget="collapse"><i class="fas fa-minus"></i></button>
                                        </div>
                                    </div>
                                    
                                    <div class="card-body">
                                        <form class="form-horizontal" action="gd_gestioncarteraSRV?action=searchnegotiations" method="POST">
                                            <div class="row">
                                                <div class="col-sm-6">
                                                    <div class="input-group mb-3">
                                                        <div class="input-group-prepend">
                                                            <span class="input-group-text"><i class="fas fa-user"></i></span>
                                                        </div>
                                                        <input id="txtnombreUsuario" type="text" class="form-control form-control-sm" placeholder="Nombres de Usuario" 
                                                               name="txtnombreUsuario" maxlength="350" value="${gd_usuario.nombreUsuario}" readonly>
                                                    </div>
                                                </div>
                                                <div class="col-sm-3">
                                                    <div class="form-group">
                                                        <input type="hidden" id="hdDesde" value="${gd_gestioncartera.dDocCobOpe_FecIni}">
                                                        <input type="date" class="form-control form-control-sm" id="dtpFechaDesde" 
                                                               name="dtpFechaDesde" />
                                                    </div>
                                                </div>
                                                <div class="col-sm-3">
                                                    <div class="form-group">
                                                        <input type="hidden" id="hdHasta" value="${gd_gestioncartera.dDocCobOpe_FecFin}">
                                                        <input type="date" class="form-control form-control-sm" id="dtpFechaHasta" 
                                                               name="dtpFechaHasta" />
                                                    </div>
                                                </div>
                                            </div>
                                            <div class="row">
                                                <div class="col-sm-12">
                                                    <div class="input-group mb-3">
                                                        <div class="input-group-prepend">
                                                            <span class="input-group-text"><i class="fas fa-folder nav-icon"></i></span>
                                                        </div>
                                                        <select class="form-control form-control-sm select2"  name="cboCartera" autofocus=""  required>
                                                            <option value="0">Seleccione Cartera</option>
                                                            <c:forEach items="${av_carteras}" var="av_carteras">
                                                                <option value="${av_carteras.nId_Cartera}" ${av_carteras.nId_Cartera == idCartera ? 'selected' : ''}>${av_carteras.cCar_Nombre}</option>
                                                            </c:forEach>
                                                        </select>
                                                    </div>
                                                </div>
                                            </div>
                                            <div class="row">
                                                <div class="col-md-6">
                                                    <div class="input-group mb-3">
                                                        <div class="input-group-prepend">
                                                            <span class="input-group-text"><i class="fas fa-search nav-icon"></i></span>
                                                        </div>
                                                        <select class="form-control form-control-sm" id="cboBuscarPor" name="cboBuscarPor" autofocus="" required="">
                                                            <option value="0">Buscar Por:</option>
                                                            <c:forEach items="${gd_tipobusquedas}" var="gd_tipobusquedas">
                                                                <option value="${gd_tipobusquedas.cTipoBusqueda}" ${gd_tipobusquedas.cTipoBusqueda == cTipoBusqueda ? 'selected' : ''}>${gd_tipobusquedas.cTipoBusquedaNombre}</option>
                                                            </c:forEach>
                                                        </select>
                                                    </div>
                                                </div>
                                                <div class="col-md-6">
                                                    <div class="input-group mb-3">
                                                        <div class="input-group-prepend">
                                                            <span class="input-group-text"><i class="fas fa-user"></i></span>
                                                        </div>
                                                        <input id="EncontrarPor" type="text" class="form-control form-control-sm" placeholder="Ingrese valor" 
                                                               name="txtEncontrarPor" maxlength="350" value="${gd_gestioncartera.cPers_CodCliente}">
                                                    </div>
                                                </div>
                                            </div>
                                            <div class="row">
                                                <div class="col-md-12">
                                                    <div class="form-group">
                                                        <div class="card-header" style="text-align: center">
                                                            <button type="submit" id="btnBuscar" name="btnBuscar" value="Buscar" class="btn btn-primary"><i class="fa fa-search"></i> Buscar</button>
                                                        </div>
                                                        <button type="button" class="btn btn-default" data-toggle="modal" data-target="#modal-xl">
                                                          Launch Extra Large Modal
                                                        </button>
                                                    </div>
                                                </div>
                                            </div>
                                                    
                                            <%
                                                if (!msjValida.equals("") ) {        
                                            %>
                                            <div class="social-auth-links text-center">
                                                <a href="#" class="btn btn-block btn-social btn-warning btn-flat"><i class="fa fa-info"></i> Mensaje: 
                                                    ${msjValida}
                                                </a>
                                            </div>
                                            <%
                                               }
                                            %>
                                                
                                            <div class="row">
                                                <div class="col-12">
                                                    <!-- Custom Tabs -->
                                                    <div class="card">
                                                        <div class="card-header d-flex p-0">
                                                            <h3 class="card-title p-3">Resultado</h3>
                                                            <ul class="nav nav-pills ml-auto p-2">
                                                                <li class="nav-item"><a class="nav-link active" href="#tab_1" data-toggle="tab">Cartera</a></li>
                                                                <li class="nav-item"><a class="nav-link" href="#tab_2" data-toggle="tab">Deudor</a></li>
                                                                <li class="nav-item"><a class="nav-link" href="#tab_3" data-toggle="tab">Dashboard</a></li>
                                                            </ul>
                                                        </div><!-- /.card-header -->
                                                        <div class="card-body">
                                                            <div class="tab-content">
                                                                <div class="tab-pane active" id="tab_1">
                                                                    <div class="row">
                                                                        <div class="col-md-12">
                                                                            <table id="tablaGestionCarteras" class="table table-bordered table-hover">
                                                                                <thead>
                                                                                    <tr>
                                                                                        <th>Cartera</th>
                                                                                        <th>Cod.Cliente</th>
                                                                                        <th>Deudor</th>
                                                                                        <th>Importe</th>
                                                                                        <th>Status</th>
                                                                                        <th>Ult. Gestión</th>
                                                                                    </tr>
                                                                                </thead>
                                                                                <c:forEach var="lstGestiones" items="${lstGestiones}" varStatus="iteracion">
                                                                                    <tr>
                                                                                        <td>${lstGestiones.cCar_Nombre}</td>
                                                                                        <td>${lstGestiones.cPers_CodCliente}</td>
                                                                                        <td>${lstGestiones.cPers_Nombres}</td>
                                                                                        <td>${lstGestiones.nDoc_ImpTotal}</td>
                                                                                        <td>${lstGestiones.cNombre_OpeCodCliOut}</td>
                                                                                        <td>${lstGestiones.dDocCobOpe_FecIni}</td>

                                                                                    </tr>
                                                                                </c:forEach>
                                                                            </table>
                                                                        </div>
                                                                    </div>
                                                                    
                                                                </div>
                                                                <!-- /.tab-pane -->
                                                                <div class="tab-pane" id="tab_2">
                                                                    <div class="row">
                                                                        <div class="col-md-12">
                                                                            <table id="tablaGestionDeudores" class="table table-bordered table-hover">
                                                                                <thead>
                                                                                    <tr>
                                                                                        <th>Fecha</th>
                                                                                        <th>Gestor</th>
                                                                                        <th>Documento</th>
                                                                                        <th>Comentario</th>
                                                                                        <th>Comentario</th>
                                                                                        <th>Accion</th>
                                                                                    </tr>
                                                                                </thead>
                                                                                <c:forEach var="lstGestionDeudores" items="${lstGestionDeudores}" varStatus="iteracion">
                                                                                    <tr>
                                                                                        <td>${lstGestionDeudores.dDocCobOpe_FecIni}</td>
                                                                                        <td>${lstGestionDeudores.cUsr_Nombres}</td>
                                                                                        <td>${lstGestionDeudores.nId_DocxCobrar}</td>
                                                                                        <td>${lstGestionDeudores.cNomTipoGestion}</td>
                                                                                        <td>${lstGestionDeudores.cDocOpeCobOut_Descr}</td>
                                                                                        <td>
                                                                                            <a href="<c:url value="lstGestiones">
                                                                                                        <c:param name="action" value="getuser" />
                                                                                                        <c:param name="nId_PersDeudor" value="${lstGestionDeudores.nId_OpeCodOut}" />
                                                                                                    </c:url>">
                                                                                                    <button type="button" class="btn btn-warning btn-sm" data-toggle="tooltip"  title="Descargar" data-original-title="Descargar">
                                                                                                        <i class="fa fa-download"></i>
                                                                                                    </button>
                                                                                            </a>
                                                                                        </td>
                                                                                    </tr>
                                                                                </c:forEach>
                                                                            </table>
                                                                        </div>
                                                                    </div>
                                                                </div>
                                                                <!-- /.tab-pane -->
                                                                <div class="tab-pane" id="tab_3">
                                                                    <div class="row">
                                                                        <%
                                                                            for (int i = 0; i < lstGesConNoCon.size(); i++) {
                                                                                av_DocxCobrarOpe objConNoCon = (av_DocxCobrarOpe)lstGesConNoCon.get(i);
                                                                                if (objConNoCon.getcNombre_OpeCodCliOut().equals("TIENE RECLAMO - TICKET")) { 
                                                                        %>
                                                                        <div class="col-lg-3 col-6">
                                                                            <!-- small box -->
                                                                            <div class="small-box bg-danger">
                                                                                <div class="inner">
                                                                                    <h3><%= objConNoCon.getNroContactabilidad() %></h3>

                                                                                    <p><%= objConNoCon.getcNombre_OpeCodCliOut() %></p>
                                                                                </div>
                                                                                <div class="icon">
                                                                                    <i class="ion ion-pie-graph"></i>
                                                                                </div>
                                                                                <a href="gd_usuarioSRV?action=listusers" class="small-box-footer"> Mas info <i class="fas fa-arrow-circle-right"></i></a>
                                                                                
                                                                            </div>
                                                                        </div>
                                                                        <!-- /.col -->
                                                                        <% } else if (objConNoCon.getcNombre_OpeCodCliOut().equals("SOLICITUD DE FRACCIONAMIENTO")) { %>
                                                                        <div class="col-lg-3 col-6">
                                                                            <!-- small box -->
                                                                            <div class="small-box bg-warning">
                                                                                <div class="inner">
                                                                                    <h3><%= objConNoCon.getNroContactabilidad() %></h3>

                                                                                    <p><%= objConNoCon.getcNombre_OpeCodCliOut() %></p>
                                                                                </div>
                                                                                <div class="icon">
                                                                                    <i class="ion ion-person-add"></i>
                                                                                </div>
                                                                                <a href="#" class="small-box-footer">Mas info <i class="fas fa-arrow-circle-right"></i></a>
                                                                            </div>
                                                                        </div>
                                                                        <% } else if (objConNoCon.getcNombre_OpeCodCliOut().equals("PROMESA DE PAGO")) { %>
                                                                        <div class="col-lg-3 col-6">
                                                                            <!-- small box -->
                                                                            <div class="small-box bg-success">
                                                                                <div class="inner">
                                                                                    <h3><%= objConNoCon.getNroContactabilidad() %></h3>

                                                                                    <p><%= objConNoCon.getcNombre_OpeCodCliOut() %></p>
                                                                                </div>
                                                                                <div class="icon">
                                                                                    <i class="ion ion-stats-bars"></i>
                                                                                </div>
                                                                                <a href="#" class="small-box-footer">Mas info <i class="fas fa-arrow-circle-right"></i></a>
                                                                            </div>
                                                                        </div>
                                                                        <% } else if (objConNoCon.getcNombre_OpeCodCliOut().equals("INDICA QUE PAGO")) { %>
                                                                        <div class="col-lg-3 col-6">
                                                                            <!-- small box -->
                                                                            <div class="small-box bg-success">
                                                                                <div class="inner">
                                                                                    <h3><%= objConNoCon.getNroContactabilidad() %></h3>

                                                                                    <p><%= objConNoCon.getcNombre_OpeCodCliOut() %></p>
                                                                                </div>
                                                                                <div class="icon">
                                                                                    <i class="ion ion-stats-bars"></i>
                                                                                </div>
                                                                                <a href="#" class="small-box-footer">Mas info <i class="fas fa-arrow-circle-right"></i></a>
                                                                            </div>
                                                                        </div>
                                                                        <% } else if (objConNoCon.getcNombre_OpeCodCliOut().equals("NO CONTESTA")) { %>
                                                                        <div class="col-lg-3 col-6">
                                                                            <!-- small box -->
                                                                            <div class="small-box bg-danger">
                                                                                <div class="inner">
                                                                                    <h3><%= objConNoCon.getNroContactabilidad() %></h3>

                                                                                    <p><%= objConNoCon.getcNombre_OpeCodCliOut() %></p>
                                                                                </div>
                                                                                <div class="icon">
                                                                                    <i class="ion ion-pie-graph"></i>
                                                                                </div>
                                                                                <a href="#" class="small-box-footer">Mas info <i class="fas fa-arrow-circle-right"></i></a>
                                                                            </div>
                                                                        </div>
                                                                        <% } else if (objConNoCon.getcNombre_OpeCodCliOut().equals("PRESENTARA RECLAMO")) { %>
                                                                        <div class="col-lg-3 col-6">
                                                                            <!-- small box -->
                                                                            <div class="small-box bg-danger">
                                                                                <div class="inner">
                                                                                    <h3><%= objConNoCon.getNroContactabilidad() %></h3>

                                                                                    <p><%= objConNoCon.getcNombre_OpeCodCliOut() %></p>
                                                                                </div>
                                                                                <div class="icon">
                                                                                    <i class="ion ion-pie-graph"></i>
                                                                                </div>
                                                                                <a href="#" class="small-box-footer">Mas info <i class="fas fa-arrow-circle-right"></i></a>
                                                                            </div>
                                                                        </div>
                                                                        <% } else if (objConNoCon.getcNombre_OpeCodCliOut().equals("NO GESTIONAR A SOLICITUD DEL CLIENTE")) { %>
                                                                        <div class="col-lg-3 col-6">
                                                                            <!-- small box -->
                                                                            <div class="small-box bg-warning">
                                                                                <div class="inner">
                                                                                    <h3><%= objConNoCon.getNroContactabilidad() %></h3>

                                                                                    <p><%= objConNoCon.getcNombre_OpeCodCliOut() %></p>
                                                                                </div>
                                                                                <div class="icon">
                                                                                    <i class="ion ion-person-add"></i>
                                                                                </div>
                                                                                <a href="#" class="small-box-footer">Mas info <i class="fas fa-arrow-circle-right"></i></a>
                                                                            </div>
                                                                        </div>
                                                                        <% } else { %>
                                                                        <div class="col-lg-3 col-6">
                                                                            <!-- small box -->
                                                                            <div class="small-box bg-info">
                                                                                <div class="inner">
                                                                                    <h3><%= objConNoCon.getNroContactabilidad() %></h3>

                                                                                    <p><%= objConNoCon.getcNombre_OpeCodCliOut() %></p>
                                                                                </div>
                                                                                <div class="icon">
                                                                                    <i class="ion ion-bag"></i>
                                                                                </div>
                                                                                <a href="#" class="small-box-footer">Mas info <i class="fas fa-arrow-circle-right"></i></a>
                                                                            </div>
                                                                        </div>
                                                                        
                                                                        <% } %>
                                                                        <% } %>
                                                                    <!-- /.col -->
                                                                    </div>
                                                                </div>
                                                                <!-- /.tab-pane -->
                                                            </div>
                                                            <!-- /.tab-content -->
                                                        </div><!-- /.card-body -->
                                                    </div>
                                                    <!-- ./card -->
                                                </div>
                                                <!-- /.col -->
                                            </div>
                                            <!-- /.row -->
                                        </form>
                                    </div>
                                </div>
                            </div>
                        </div>
                    </div>
                </section>
            </div>
                                                                    
                                                                    
    <div class="modal fade" id="modal-xl">
        <div class="modal-dialog modal-xl">
          <div class="modal-content">
            <div class="modal-header">
              <h4 class="modal-title">Extra Large Modal</h4>
              <button type="button" class="close" data-dismiss="modal" aria-label="Close">
                <span aria-hidden="true">&times;</span>
              </button>
            </div>
            <div class="modal-body">
              <p>One fine body&hellip;</p>
            </div>
            <div class="modal-footer justify-content-between">
              <button type="button" class="btn btn-default" data-dismiss="modal">Close</button>
              <button type="button" class="btn btn-primary">Save changes</button>
            </div>
          </div>
          <!-- /.modal-content -->
        </div>
        <!-- /.modal-dialog -->
      </div>
      <!-- /.modal -->
      
            <!-- /.content-wrapper -->
            <footer class="main-footer">
                <strong>Derechos de autor &copy; 2023 <a href="https://avalperu.com/">Aval Perú</a>.</strong>
                Reservados todos los derechos.
                <div class="float-right d-none d-sm-inline-block">
                    <b>Version</b> 1.1.0
                </div>
            </footer>
            <!-- /.control-sidebar -->
        </div>
        <!-- ./wrapper -->
        <!-- jQuery -->
        <script src="plugins/jquery/jquery.min.js"></script>
        <!-- Bootstrap 4 -->
        <script src="plugins/bootstrap/js/bootstrap.bundle.min.js"></script>
        <!-- Select2 -->
        <script src="plugins/select2/js/select2.full.min.js"></script>
        <!-- Bootstrap4 Duallistbox -->
        <script src="plugins/bootstrap4-duallistbox/jquery.bootstrap-duallistbox.min.js"></script>
        <!-- InputMask -->
        <script src="plugins/moment/moment.min.js"></script>
        
        <!-- date-range-picker -->
        <script src="plugins/daterangepicker/daterangepicker.js"></script>
        <!-- bootstrap color picker -->
        <script src="plugins/bootstrap-colorpicker/js/bootstrap-colorpicker.min.js"></script>
        <!-- Tempusdominus Bootstrap 4 -->
        <script src="plugins/tempusdominus-bootstrap-4/js/tempusdominus-bootstrap-4.min.js"></script>
        <!-- Bootstrap Switch -->
        <script src="plugins/bootstrap-switch/js/bootstrap-switch.min.js"></script>
        <!-- AdminLTE App -->
        <script src="dist/js/adminlte.min.js"></script>
        <!-- DataTables  & Plugins -->
        <script src="plugins/datatables/jquery.dataTables.min.js"></script>
        <script src="plugins/datatables-bs4/js/dataTables.bootstrap4.min.js"></script>
        <script src="plugins/datatables-responsive/js/dataTables.responsive.min.js"></script>
        <script src="plugins/datatables-responsive/js/responsive.bootstrap4.min.js"></script>
        <script src="plugins/datatables-buttons/js/dataTables.buttons.min.js"></script>
        <script src="plugins/datatables-buttons/js/buttons.bootstrap4.min.js"></script>
        <script src="plugins/jszip/jszip.min.js"></script>
        <script src="plugins/pdfmake/pdfmake.min.js"></script>
        <script src="plugins/pdfmake/vfs_fonts.js"></script>
        <script src="plugins/datatables-buttons/js/buttons.html5.min.js"></script>
        <script src="plugins/datatables-buttons/js/buttons.print.min.js"></script>
        <script src="plugins/datatables-buttons/js/buttons.colVis.min.js"></script>
        <!-- AdminLTE for demo purposes -->
        
        <script src="dist/js/demo.js"></script>
        <script src="js/funcionesGestionCartera.js" type="text/javascript"></script>
        <!-- Page script -->
        
        
    </body>
</html>
<%
    } else {
        response.setHeader("Cache-Control", "no-cache, no-store, must-revalidate");
        response.sendRedirect("login.jsp");
    }
%>