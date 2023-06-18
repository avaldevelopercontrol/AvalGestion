package Controller;

import Dao.gd_usuarioDAO;
import Models.Conection;
import Models.gd_usuario;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
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