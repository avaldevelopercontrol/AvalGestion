package Controller;

import Dao.gd_usuarioclienteDAO;
import Models.Conection;
import Models.av_cliente;
import Models.gd_usuario;
import Models.gd_usuariocliente;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet(name = "gd_usuarioclienteSRV", urlPatterns = {"/gd_usuarioclienteSRV"} )
public class gd_usuarioclienteSRV extends HttpServlet {

    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException, Exception {
        response.setContentType("text/html;charset=UTF-8");
        PrintWriter out = response.getWriter();
        
        String action = request.getParameter("action");
        String change = request.getParameter("change");
        
        try {
            if (action != null) { 
                if (action.equals("listUserClients")) {
                    listUserClients(request, response);
                } else if (action.equals("getUserClients")) {
                    getUserClients(request, response);
                } else if (action.equals("saveUserClient")) {
                    saveUserClient(request, response);
                }
            } else if (change != null) {
                if (change.equals("changeValidity")) {
                changeValidity(request, response);
                } else {
                    changeValidity(request, response);
                }
            }
        } finally {            
            out.close();
        }
    }
    
    private void listUserClients(HttpServletRequest request, HttpServletResponse response) throws Exception {
        
        if (request.getSession().getAttribute("gd_usuarioSession") == null) {
            this.getServletConfig().getServletContext()
                    .getRequestDispatcher("/login.jsp").forward(request, response);
        }
        
        Connection con = null;
        con = Conection.getConexion();
        
        gd_usuarioclienteDAO dao = new gd_usuarioclienteDAO(con);
        
        List<gd_usuario> usuariocli = null;
        try {
            usuariocli = dao.listarUsuarioClientes();
            request.setAttribute("gd_usuariosCli", usuariocli);
        } catch (Exception e) {
            request.setAttribute("msje", "No se pudo listar los usuarios" + e.getMessage());
        } finally {
            dao = null;
            usuariocli = null;
        }
        try {
            this.getServletConfig().getServletContext().getRequestDispatcher("/views/usersclients.jsp").forward(request, response);
        } catch (Exception ex) {
            request.setAttribute("msje", "No se pudo realizar la petición" + ex.getMessage());
        }
    }
    
    private void getUserClients(HttpServletRequest request, HttpServletResponse response) throws Exception {
        if (request.getSession().getAttribute("gd_usuarioSession") == null) {
            this.getServletConfig().getServletContext()
                    .getRequestDispatcher("/login.jsp").forward(request, response);
        }
        
        Connection con = null;
        con = Conection.getConexion();
            
        gd_usuarioclienteDAO dao;
        
        gd_usuariocliente usu;
        
        if (request.getParameter("idUsuario") != null) { 
            usu = new gd_usuariocliente();
            usu.setIdUsuario(Integer.parseInt(request.getParameter("idUsuario")));
            
            dao = new gd_usuarioclienteDAO(con);
            usu = dao.leerUsuario(usu);
            
            try {
                if (usu != null) { 
                    request.setAttribute("gd_UsuarioCliente", usu);
                    this.getClients(request);
                    this.getClientsxUser(request);
                } else {
                        request.setAttribute("msje", "No se encontró el usuario");
                }
            } catch (Exception e) {
                request.setAttribute("msje", "No se pudo acceder a la base de datos" + e.getMessage());
            }
        } else {
            request.setAttribute("msje", "No se tiene el parámetro necesario");
        }
        try {
            this.getServletConfig().getServletContext()
                    .getRequestDispatcher("/views/usersclientsadmin.jsp").forward(request, response);
        } catch (Exception e) {
            request.setAttribute("msje", "No se pudo cargar la vista");
        }
    }
    
    private void getClients(HttpServletRequest request) throws Exception {
        
        Connection con = null;
        con = Conection.getConexion();
        
        gd_usuarioclienteDAO dao = new gd_usuarioclienteDAO(con);
        gd_usuario usu;
        usu = new gd_usuario();
        
        usu.setIdUsuario(Integer.parseInt(request.getParameter("idUsuario")));
        List<av_cliente> av_clientes = null;
        try {
            av_clientes = dao.llenarComboClientes(usu);
            request.setAttribute("av_clientes", av_clientes);
        } catch (Exception e) {
            request.setAttribute("msje", "No se pudo cargar los Perfiles :( " + e.getMessage());
        } finally {
            dao = null;
            av_clientes = null;
        }
    }
    
    private void getClientsxUser(HttpServletRequest request) throws Exception {
        
        Connection con = null;
        con = Conection.getConexion();
        
        gd_usuarioclienteDAO dao = new gd_usuarioclienteDAO(con);
        gd_usuario usu = new gd_usuario();
        usu.setIdUsuario(Integer.parseInt(request.getParameter("idUsuario")));
        List<gd_usuariocliente> gd_usuarioClientes = null;
        try {
            gd_usuarioClientes = dao.ListarUsuarioClientes(usu);
            request.setAttribute("listaUsuarioClientes", gd_usuarioClientes);
        } catch (Exception e) {
            request.setAttribute("msje", "No se pudo cargar los Clientes por Usuario :( " + e.getMessage());
        } finally {
            dao = null;
            gd_usuarioClientes = null;
        }
    }
    
    private void saveUserClient(HttpServletRequest request, HttpServletResponse response) throws Exception {
        if (request.getSession().getAttribute("gd_usuarioSession") == null) {
            this.getServletConfig().getServletContext()
                .getRequestDispatcher("/login.jsp").forward(request, response);
        }
        
        Connection con = null;
        con = Conection.getConexion();
        
        gd_usuarioclienteDAO daoUsuarioCliente;
        gd_usuariocliente usucli = null;
        gd_usuario usu = null;
        av_cliente cli = null;
        if (request.getParameter("txtidUsuario") != null
                && request.getParameter("cboCliente") != null) {
            
            gd_usuario usuSession = (gd_usuario)request.getSession().getAttribute("gd_usuarioSession");

            usucli = new gd_usuariocliente();
            usu = new gd_usuario();
            usu.setIdUsuario(Integer.parseInt(request.getParameter("txtidUsuario")));
            usucli.setGd_Usuario(usu);
            cli = new av_cliente();
            cli.setnId_Cliente(Integer.parseInt(request.getParameter("cboCliente")));
            usucli.setAv_Cliente(cli);
            usucli.setActivo(true);
            usucli.setUsuarioCrea(usuSession.getNombreUsuario());
            daoUsuarioCliente = new gd_usuarioclienteDAO(con);
            try {
                daoUsuarioCliente.registrarUsuarioCliente(usucli);
                
                response.sendRedirect("gd_usuarioclienteSRV?action=getUserClients&idUsuario=" + request.getParameter("txtidUsuario"));
            } catch (Exception e) {
                request.setAttribute("msje",
                        "No se pudo registrar el Cliente" + e.getMessage());
                request.setAttribute("apr_usuarioclienteSRV", usucli);
                this.getUserClients(request, response);
            } finally {
                
            }
        }
    }
    
    private void changeValidity(HttpServletRequest request, HttpServletResponse response) throws Exception {
        if (request.getSession().getAttribute("gd_usuarioSession") == null) {
            this.getServletConfig().getServletContext()
                    .getRequestDispatcher("/login.jsp").forward(request, response);
        }
        
        Integer idUsuarioCliente = Integer.parseInt(request.getParameter("idUsuarioCliente"));
        Integer idUsuario = Integer.parseInt(request.getParameter("idUsuario"));
        
        Connection con = null;
        con = Conection.getConexion();
        
        gd_usuarioclienteDAO dao;
        gd_usuariocliente usucli;
        
        try {
            dao = new gd_usuarioclienteDAO(con);
            usucli = new gd_usuariocliente();
            
            if (request.getParameter("change").equals("active")) {
                usucli.setActivo(true);
            } else {
                usucli.setActivo(false);
            }
            
            if (request.getParameter("idUsuarioCliente") != null) {
                usucli.setIdUsuarioCliente(idUsuarioCliente);
                dao.cambiarVigencia(usucli);
                response.sendRedirect("gd_usuarioclienteSRV?action=getUserClients&idUsuario=" + idUsuario);
            } else {
                request.setAttribute("msje", "No se obtuvo el Id del UsuarioCliente");
            }
        } catch (Exception e) {
        }
    }

    // <editor-fold defaultstate="collapsed" desc="HttpServlet methods. Click on the + sign on the left to edit the code.">
    /** 
     * Handles the HTTP <code>GET</code> method.
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        try {
            processRequest(request, response);
        } catch (Exception ex) {
            Logger.getLogger(gd_usuarioclienteSRV.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    /** 
     * Handles the HTTP <code>POST</code> method.
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        try {
            processRequest(request, response);
        } catch (Exception ex) {
            Logger.getLogger(gd_usuarioclienteSRV.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    /** 
     * Returns a short description of the servlet.
     * @return a String containing servlet description
     */
    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>

}