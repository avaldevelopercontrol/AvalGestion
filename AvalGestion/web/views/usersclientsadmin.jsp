<%@page import="Models.gd_usuariocliente"%>
<%@page import="javax.xml.ws.Response"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<jsp:useBean id="listaUsuarioClientes" scope="request" class="java.util.ArrayList" />
<!DOCTYPE html>
<%
    if (session.getAttribute("gd_usuarioSession") != null ) {      
%>
<html>
    <head>
        <meta charset="utf-8">
        <meta name="viewport" content="width=device-width, initial-scale=1">
        <title>Sistema Gestión</title>

        <link rel="stylesheet" href="https://fonts.googleapis.com/css?family=Source+Sans+Pro:300,400,400i,700&display=fallback">
        <!-- Font Awesome -->
        <link rel="stylesheet" href="plugins/fontawesome-free/css/all.min.css">
        <!-- Theme style -->
        <link rel="stylesheet" href="dist/css/adminlte.min.css">
        <link rel="shortcut icon" href="dist/img/aval_logo.jpg">
        <script defer src="https://use.fontawesome.com/releases/v5.0.4/js/all.js"></script>
        <script defer src="https://use.fontawesome.com/releases/v5.0.4/js/v4-shims.js"></script> 
        <script defer src="https://use.fontawesome.com/releases/v5.0.4/js/fontawesome.js"> </script>
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
                            <li class="nav-item menu-open">
                                <a href="#" class="nav-link active">
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
                                        <a href="gd_usuarioclienteSRV?action=listUserClients" class="nav-link active">
                                            <i class="far fa-id-badge nav-icon"></i>
                                            <p>Usuario - Clientes</p>
                                        </a>
                                    </li>
                                </ul>
                            </li>
                            <li class="nav-item menu">
                                <a href="#" class="nav-link">
                                    <i class="nav-icon fas fa-paste"></i>
                                    <p>
                                        Gestión
                                        <i class="right fas fa-angle-left"></i>
                                    </p>
                                </a>
                                <ul class="nav nav-treeview">
                                    <li class="nav-item">
                                        <a href="gd_gestioncarteraSRV?action=getmanagementportfolios" class="nav-link">
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
                                <h1 class="m-0">Registrar Usuario - Cliente</h1>
                            </div><!-- /.col -->
                            <div class="col-sm-6">
                                <ol class="breadcrumb float-sm-right">
                                    <li class="breadcrumb-item"><a href="#">Mantenimiento</a></li>
                                    <li class="breadcrumb-item active">Usuario - Clientes</li>
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
                                    <div class="card-body">
                                        <form class="form-horizontal" action="gd_usuarioclienteSRV?action=saveUserClient" method="POST">
                                            <div class="row">
                                                <div class="col-sm-6">
                                                    <div class="input-group mb-3">
                                                        <div class="input-group-prepend">
                                                            <span class="input-group-text"><i class="fas fa-key"></i></span>
                                                        </div>
                                                        <input id="idUsuario" type="text" class="form-control form-control-sm" placeholder="Id Usuario" 
                                                               readonly="true" name="txtidUsuario" maxlength="10" value="${gd_UsuarioCliente.idUsuario}">
                                                        <input type="hidden" id="idUsuario" value="${gd_UsuarioCliente.idUsuario}">
                                                    </div>
                                                </div>
                                                <div class="col-sm-6">
                                                    <div class="input-group mb-12">
                                                        <div class="input-group-prepend">
                                                            <span class="input-group-text"><i class="fas fa-user"></i></span>
                                                        </div>
                                                        <input id="nombreUsuario" type="text" class="form-control form-control-sm" placeholder="Nombre Usuario" 
                                                               name="txtnombreUsuario" maxlength="350" value="${gd_UsuarioCliente.nombreUsuario}" readonly="">
                                                    </div>
                                                </div>
                                            </div>
                                            <div class="row">
                                                <div class="col-sm-6">
                                                    <div class="input-group mb-3">
                                                        <div class="input-group-prepend">
                                                        <span class="input-group-text"><i class="fas fa-building nav-icon"></i></span>
                                                        </div>
                                                        <select class="form-control form-control-sm"  name="cboCliente" autofocus=""  required="">
                                                            <option value="0">Seleccione Cliente</option>
                                                            <c:forEach items="${av_clientes}" var="av_clientes">
                                                                <option value="${av_clientes.nId_Cliente}">${av_clientes.cCli_Nombre}</option>
                                                            </c:forEach>
                                                        </select>
                                                    </div>
                                                </div>
                                                <div class="col-sm-6">
                                                    <button type="submit" id="" name="btnRegistrar" value="Registrar" class="btn btn-primary btn-sm"><i class="fa fa-plus-circle"></i> Agregar Cliente</button>
                                                </div>
                                            </div>
                                            <div>
                                                <div class="col-sm-12">
                                                    <div class="box-header with-border">
                                                        <i class="fa fa-plus-circle"></i> <h3 class="box-title">Listado de Clientes</h3>  
                                                    </div>
                                                    <div class="box-body">
                                                        <div class="table-responsive" >
                                                            <table class="table table-bordered table-striped dataTable table-hover" id="tablaUsuarioPerfiles" class="display">
                                                                <thead>
                                                                    <tr>
                                                                        <th>IDCliente</th>
                                                                        <th>Nombre Cliente</th>
                                                                        <th>Estado</th>
                                                                        <th>Acciones</th>
                                                                    </tr>
                                                                </thead>
                                                                <%
                                                                    for (int i = 0; i < listaUsuarioClientes.size(); i++) {
                                                                        gd_usuariocliente objUsuarioCliente = (gd_usuariocliente) listaUsuarioClientes.get(i);
                                                                %>
                                                                <tr>
                                                                    <td><%=objUsuarioCliente.getAv_Cliente().getnId_Cliente()%></td>
                                                                    <td><%=objUsuarioCliente.getAv_Cliente().getcCli_Nombre()%></td>
                                                                    <% if (objUsuarioCliente.isActivo() == true) { %>
                                                                    <td><span class="badge bg-green active">Activo</span></td>
                                                                    <% } else { %>
                                                                    <td><span class="badge bg-red active">Inactivo</span></td> 
                                                                    <% } %>
                                                                    <td>
                                                                        <!-- DESACTIVAR / ACTIVAR USUARIO PERFILES -->
                                                                        <% if (objUsuarioCliente.isActivo() == true) { %>
                                                                        <input type="hidden" id="idUsuarioCliente" name="idUsuarioCliente" value="<%=objUsuarioCliente.getIdUsuarioCliente()%>">
                                                                        <input type="hidden" id="idUsuario" name="idUsuario" value="<%=objUsuarioCliente.getGd_Usuario().getIdUsuario()%>">
                                                                        <a id="desactivarUsuarioCliente" href="gd_usuarioclienteSRV?change=changestatusoff&idUsuarioCliente=<%=objUsuarioCliente.getIdUsuarioCliente()%>&idUsuario=<%=objUsuarioCliente.getGd_Usuario().getIdUsuario()%>" class="btn btn-danger btn-xs"  data-toggle="tooltip" title="Desactivar" data-original-title="Desactivar">
                                                                            <i class="fa fa-remove"></i>
                                                                        </a>
                                                                        <% } else { %>
                                                                        <input type="hidden" id="idUsuarioCliente" value="<%=objUsuarioCliente.getIdUsuarioCliente()%>">
                                                                        <input type="hidden" id="idUsuario" name="idUsuario" value="<%=objUsuarioCliente.getGd_Usuario().getIdUsuario()%>">
                                                                        <a id="activarUsuarioCliente" href="gd_usuarioclienteSRV?change=activestate&idUsuarioCliente=<%=objUsuarioCliente.getIdUsuarioCliente()%>&idUsuario=<%=objUsuarioCliente.getGd_Usuario().getIdUsuario()%>" class="btn btn-primary btn-xs" data-toggle="tooltip" title="Activar" data-original-title="Activar">
                                                                            <i class="fa fa-eye"></i>
                                                                        </a>
                                                                        <% } %>
                                                                    </td>
                                                                </tr>
                                                                <% } %>
                                                            </table>
                                                        </div>
                                                    </div>
                                                </div>
                                            </div>
                                            <div class="card-footer">
                                                <a href="gd_usuarioclienteSRV?accion=listarUsuarioClientes" class="btn btn-danger btn-sm">
                                                    <i class="fa fa-close"></i> Cancelar 
                                                </a>
                                            </div>
                                        </form>
                                    </div>
                                </div>
                            </div>
                        </div>
                    </div><!-- /.container-fluid -->
                </section>
                <!-- /.content -->
            </div>
            <!-- /.content-wrapper -->
            <footer class="main-footer">
                <strong>Derechos de autor &copy; 2023 <a href="https://avalperu.com/">Aval Perú</a>.</strong>
                Reservados todos los derechos.
                <div class="float-right d-none d-sm-inline-block">
                    <b>Version</b> 1.1.0
                </div>
            </footer>

            <!-- Control Sidebar -->
            <aside class="control-sidebar control-sidebar-dark">
                <!-- Control sidebar content goes here -->
            </aside>
            <!-- /.control-sidebar -->
        </div>
        <!-- ./wrapper -->
        <!-- jQuery -->
        <script src="plugins/jquery/jquery.min.js"></script>
        <!-- Bootstrap 4 -->
        <script src="plugins/bootstrap/js/bootstrap.bundle.min.js"></script>
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
        <!-- bs-custom-file-input -->
        <script src="plugins/bs-custom-file-input/bs-custom-file-input.min.js"></script>
        <!-- AdminLTE App -->
        <script src="dist/js/adminlte.min.js"></script>
        <!-- AdminLTE for demo purposes -->
        <script src="dist/js/demo.js"></script>
        <!-- Page specific script -->
        <script src="js/funcionesUsuarioCliente.js" type="text/javascript"></script>
        <script>
        $(function () {
          bsCustomFileInput.init();
        });
        </script>
    </body>
</html>
<%
    } else {
        response.setHeader("Cache-Control", "no-cache, no-store, must-revalidate");
        response.sendRedirect("login.jsp");
    }
%>