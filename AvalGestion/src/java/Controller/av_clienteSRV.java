package Controller;

import Dao.av_clienteDAO;
import Models.Conection;
import Models.av_cliente;
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

@WebServlet(name = "av_clienteSRV", urlPatterns = {"/av_clienteSRV"} )
public class av_clienteSRV extends HttpServlet {

    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException, Exception {
        response.setContentType("text/html;charset=UTF-8");
        PrintWriter out = response.getWriter();
        
        String accion = request.getParameter("action");
        
        try {
            if (accion != null) { 
                if (accion.equals("listclients")) {
                    listarClientes(request, response);
                }
            }
        } finally {            
            out.close();
        }
    }
    
    private void listarClientes(HttpServletRequest request, HttpServletResponse response) throws Exception {
        
        if (request.getSession().getAttribute("gd_usuarioSession") == null) {
            this.getServletConfig().getServletContext()
                    .getRequestDispatcher("/identificar.jsp").forward(request, response);
        }
        
        Connection con = null;
        con = Conection.getConexion();
        
        av_clienteDAO dao = new av_clienteDAO(con);
        
        List<av_cliente> clientes = null;
        try {
            clientes = dao.listarClientes();
            request.setAttribute("av_cliente", clientes);
        } catch (Exception e) {
            request.setAttribute("msje", "No se pudo listar los clientes" + e.getMessage());
        } finally {
            dao = null;
            clientes = null;
        }
        try {
            this.getServletConfig().getServletContext().getRequestDispatcher("/views/clients.jsp").forward(request, response);
        } catch (Exception ex) {
            request.setAttribute("msje", "No se pudo realizar la petición" + ex.getMessage());
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
            Logger.getLogger(av_clienteSRV.class.getName()).log(Level.SEVERE, null, ex);
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
            Logger.getLogger(av_clienteSRV.class.getName()).log(Level.SEVERE, null, ex);
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
