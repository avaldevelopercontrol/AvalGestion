package Controller;

import Dao.gd_usuarioDAO;
import Encrypt.Encrypt;
import Models.Conection;
import Models.gd_usuario;
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
import javax.servlet.http.HttpSession;

@WebServlet(name = "gd_usuarioSRV", urlPatterns = {"/gd_usuarioSRV"} )
public class gd_usuarioSRV extends HttpServlet {

    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException, Exception {
        response.setContentType("text/html;charset=UTF-8");
        PrintWriter out = response.getWriter();
        
        String action = request.getParameter("action");
        
        try {
            if (action != null) { 
                if (action.equals("verify")) { 
                    verify(request, response);
                } else if (action.equals("signoff")) {
                    signoff(request, response);
                } else if (action.equals("listusers")) {
                    listusers(request, response);
                } else if (action.equals("newuser")) {
                    newuser(request, response);
                } else if (action.equals("registeruser")) {
                    registeruser(request, response);
                } else if (action.equals("getuser")) {
                    getuser(request, response);
                } else if (action.equals("updateuser")) {
                    updateuser(request, response);
                } else if (action.equals("disabled")) {
                    disabled(request, response);
                } else if (action.equals("enabled")) {
                    enabled(request, response);
                }
            }
        } finally {            
            out.close();
        }
    }
    
    private void verify(HttpServletRequest request, HttpServletResponse response) throws Exception {
        HttpSession sesion;
        gd_usuarioDAO dao;
        gd_usuario usuario;
        usuario = this.getUser(request);
        
        Connection con =  null;
        con = Conection.getConexion();
        
        dao = new gd_usuarioDAO(con);
        
        usuario = dao.identificar(usuario);
        
        try {
            if (usuario != null && usuario.isEsRoot() == true) {
                sesion = request.getSession();
                sesion.setAttribute("gd_usuarioSession", usuario);
                request.setAttribute("msje", "Bienvenido al sistema");
                this.getServletConfig().getServletContext().getRequestDispatcher("/views/index.jsp").forward(request, response);
            } else { 
                request.setAttribute("msje", "Credenciales Incorrectas");
                request.getRequestDispatcher("login.jsp").forward(request, response);
            }
        } catch (Exception e) {
            request.setAttribute("msje", "Error Inicio Session" + e.getMessage());
        } finally {
            dao = null;
            usuario = null;
        }
    }
    
    private gd_usuario getUser(HttpServletRequest request) {
        gd_usuario u = new gd_usuario();
        u.setNombreUsuario(request.getParameter("txtUsu"));
        u.setClaveUsuario(request.getParameter("txtPass"));
        return u;
    }
    
    private void signoff(HttpServletRequest request, HttpServletResponse response) throws Exception {
        HttpSession sesion = request.getSession();
        sesion.setAttribute("gd_usuarioSession", null);
        sesion.invalidate();
        response.sendRedirect("login.jsp");
    }
    
    private void listusers(HttpServletRequest request, HttpServletResponse response) throws Exception {
        
        if (request.getSession().getAttribute("gd_usuarioSession") == null) {
            this.getServletConfig().getServletContext()
                    .getRequestDispatcher("/login.jsp").forward(request, response);
        }
        
        Connection con =  null;
        con = Conection.getConexion();
        
        gd_usuarioDAO dao = new gd_usuarioDAO(con);
        
        List<gd_usuario> usuarios = null;
        try {
            usuarios = dao.listarUsuarios();
            request.setAttribute("gd_usuario", usuarios);
        } catch (Exception e) {
            request.setAttribute("msje", "No se pudo listar los usuarios" + e.getMessage());
        } finally {
            dao = null;
            usuarios = null;
        }
        try {
            this.getServletConfig().getServletContext().getRequestDispatcher("/views/users.jsp").forward(request, response);
        } catch (Exception ex) {
            request.setAttribute("msje", "No se pudo realizar la petición" + ex.getMessage());
        }
    }
    
    private void newuser(HttpServletRequest request, HttpServletResponse response) throws Exception {
        if (request.getSession().getAttribute("gd_usuarioSession") == null) {
            this.getServletConfig().getServletContext()
                    .getRequestDispatcher("/login.jsp").forward(request, response);
        }
        try {
            this.getServletConfig().getServletContext()
                    .getRequestDispatcher("/views/usersnew.jsp").forward(request, response);
        } catch (Exception e) {
            request.setAttribute("msje", "No se pudo cargar la vista");
        }
    }

    private void registeruser(HttpServletRequest request, HttpServletResponse response) throws Exception {
        if (request.getSession().getAttribute("gd_usuarioSession") == null) {
            this.getServletConfig().getServletContext()
                    .getRequestDispatcher("/login.jsp").forward(request, response);
        }
        
        gd_usuarioDAO daoUsu;
        gd_usuario usu = null;
        if (request.getParameter("txtnombreUsuario") != null
                && request.getParameter("txtnombres") != null
                && request.getParameter("txtapellidos") != null
                && request.getParameter("txtclaveUsuario") != null) {
            
            gd_usuario usuSession = (gd_usuario)request.getSession().getAttribute("gd_usuarioSession");
            
            Encrypt encrypta = new Encrypt();
            String passEncrypta = encrypta.Encrypt(request.getParameter("txtclaveUsuario"));
            
            usu = new gd_usuario();
            usu.setNombreUsuario(request.getParameter("txtnombreUsuario"));
            usu.setNombres(request.getParameter("txtnombres"));
            usu.setApellidos(request.getParameter("txtapellidos"));
            usu.setClaveUsuario(passEncrypta);
            if (request.getParameter("chkactivo") != null) {
                usu.setActivo(true);
            } else {
                usu.setActivo(false);
            }
            usu.setEsRoot(false);
            usu.setUsuarioCrea(usuSession.getNombreUsuario());
            Connection con = null;
            con = Conection.getConexion();
        
            daoUsu = new gd_usuarioDAO(con);
        
            try {
                daoUsu.registrarUsuarios(usu);
                response.sendRedirect("gd_usuarioSRV?action=listusers");
            } catch (Exception e) {
                request.setAttribute("msje",
                        "No se pudo registrar el Cliente" + e.getMessage());
                request.setAttribute("gd_usuarioSRV", usu);
                this.newuser(request, response);
            }
        }
    }
    
    private void getuser(HttpServletRequest request, HttpServletResponse response) throws Exception {
        if (request.getSession().getAttribute("gd_usuarioSession") == null) {
            this.getServletConfig().getServletContext()
                    .getRequestDispatcher("/login.jsp").forward(request, response);
        }
        
        gd_usuarioDAO dao;
        gd_usuario usus;
        if (request.getParameter("idUsuario") != null) {
            usus = new gd_usuario();
            usus.setIdUsuario(Integer.parseInt(request.getParameter("idUsuario")));
            Connection con = null;
            con = Conection.getConexion();
        
            dao = new gd_usuarioDAO(con);

            try {
                usus = dao.leerUsuario(usus);
                if (usus != null) {
                    request.setAttribute("gd_Usuario", usus);
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
            this.getServletConfig().getServletContext().
                    getRequestDispatcher("/views/usersupdate.jsp"
                    ).forward(request, response);
        } catch (Exception e) {
            request.setAttribute("msje", "No se pudo realizar la operacion" + e.getMessage());
        }
    }
    
    private void updateuser(HttpServletRequest request, HttpServletResponse response) throws Exception {
        if (request.getSession().getAttribute("gd_usuarioSession") == null) {
            this.getServletConfig().getServletContext()
                    .getRequestDispatcher("/login.jsp").forward(request, response);
        }
        
        gd_usuarioDAO dao;
        gd_usuario usus = null;
        if (request.getParameter("txtnombreUsuario") != null
                && request.getParameter("txtnombres") != null
                && request.getParameter("txtapellidos") != null
                && request.getParameter("txtclaveUsuario") != null) {
            
            gd_usuario usuSession = (gd_usuario)request.getSession().getAttribute("gd_usuarioSession");
            
            Encrypt encrypta = new Encrypt();
            usus = new gd_usuario();
            usus.setIdUsuario(Integer.parseInt(request.getParameter("txtidUsuario")));
            usus.setNombreUsuario(request.getParameter("txtnombreUsuario"));
            usus.setNombres(request.getParameter("txtnombres"));
            usus.setApellidos(request.getParameter("txtapellidos"));
            
            String passEncrypta = encrypta.Encrypt(request.getParameter("txtclaveUsuario"));
            usus.setClaveUsuario(passEncrypta);
            if (request.getParameter("chkactivo") != null) {
                usus.setActivo(true);
            } else {
                usus.setActivo(false);
            }
            usus.setEsRoot(false);
            usus.setUsuarioCrea(usuSession.getNombreUsuario());
            
            Connection con = null;
            con = Conection.getConexion();
        
            dao = new gd_usuarioDAO(con);
            try {
                dao.actualizarUsuarios(usus);
                response.sendRedirect("gd_usuarioSRV?action=listusers");
            } catch (Exception e) {
                request.setAttribute("msje",
                        "No se pudo actualizar el usuario" + e.getMessage());
                request.setAttribute("gd_usuario", usus);
            }
        }
    }
    
    private void disabled(HttpServletRequest request, HttpServletResponse response) throws Exception {
        if (request.getSession().getAttribute("gd_usuarioSession") == null) {
            this.getServletConfig().getServletContext()
                    .getRequestDispatcher("/login.jsp").forward(request, response);
        }
        
        gd_usuarioDAO dao;
        gd_usuario usu;
        gd_usuario usuSession;
        
        Connection con = null;
        con = Conection.getConexion();
        dao = new gd_usuarioDAO(con);
        
        usuSession = (gd_usuario)request.getSession().getAttribute("gd_usuarioSession");
            
        try {
            dao = new gd_usuarioDAO(con);
            usu = new gd_usuario();
            if (request.getParameter("idUsuario") != null) {
                usu.setIdUsuario(Integer.parseInt(request.getParameter("idUsuario")));
                usu.setActivo(false);
                usu.setUsuarioCrea(usuSession.getNombreUsuario());
                dao.UsuarioActivoInactivo(usu);
            } else {
                request.setAttribute("msje", "No se obtuvo el Id del Usuario");
            }
        } catch (Exception e) {
            request.setAttribute("msje", e.getMessage());
        }
        this.listusers(request, response);
    }
    
    private void enabled(HttpServletRequest request, HttpServletResponse response) throws Exception {
        if (request.getSession().getAttribute("gd_usuarioSession") == null) {
            this.getServletConfig().getServletContext()
                    .getRequestDispatcher("/login.jsp").forward(request, response);
        }
        
        gd_usuarioDAO dao;
        gd_usuario usu;
        gd_usuario usuSession;
        
        Connection con = null;
        con = Conection.getConexion();
        dao = new gd_usuarioDAO(con);
        
        usuSession = (gd_usuario)request.getSession().getAttribute("gd_usuarioSession");
            
        try {
            dao = new gd_usuarioDAO(con);
            usu = new gd_usuario();
            if (request.getParameter("idUsuario") != null) {
                usu.setIdUsuario(Integer.parseInt(request.getParameter("idUsuario")));
                usu.setActivo(true);
                usu.setUsuarioCrea(usuSession.getNombreUsuario());
                dao.UsuarioActivoInactivo(usu);
            } else {
                request.setAttribute("msje", "No se obtuvo el Id del Usuario");
            }
        } catch (Exception e) {
            request.setAttribute("msje", e.getMessage());
        }
        this.listusers(request, response);
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
            Logger.getLogger(gd_usuarioSRV.class.getName()).log(Level.SEVERE, null, ex);
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
            Logger.getLogger(gd_usuarioSRV.class.getName()).log(Level.SEVERE, null, ex);
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