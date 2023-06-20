package Controller;

import Dao.gd_gestioncarteraDAO;
import Models.Conection;
import Models.av_cartera;
import Models.gd_usuario;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet(name = "gd_gestioncarteraSRV", urlPatterns = {"/gd_gestioncarteraSRV"} )
public class gd_gestioncarteraSRV extends HttpServlet {

    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException, Exception {
        response.setContentType("text/html;charset=UTF-8");
        PrintWriter out = response.getWriter();
        
        String action = request.getParameter("action");
        
        try {
            if (action != null) {
                if (action.equals("getmanagementportfolios")) {
                    getmanagementportfolios(request, response);
                } else if (action.equals("searchnegotiations")) {
                    searchnegotiations(request, response);
                }
            }
        } finally {            
            out.close();
        }
    }
    
    private void getmanagementportfolios(HttpServletRequest request, HttpServletResponse response) throws Exception {
        if (request.getSession().getAttribute("gd_usuarioSession") == null) {
            this.getServletConfig().getServletContext()
                    .getRequestDispatcher("/login.jsp").forward(request, response);
        }
        
        Connection con = null;
        con = Conection.getConexion();
            
        gd_gestioncarteraDAO dao;
        
        DateFormat dateFormat = new SimpleDateFormat("d/MM/yyyy");
        String FechaActual = dateFormat.format(Calendar.getInstance().getTime());

        int idUsuario = 0;
        try {
            av_cartera car = new av_cartera();
            gd_usuario usuSession = (gd_usuario)request.getSession().getAttribute("gd_usuarioSession");
            idUsuario = usuSession.getIdUsuario();
            car.setIdUsuario(idUsuario);
            
            request.setAttribute("FechaDesde", FechaActual);
//            request.setAttribute("FechaHasta", FechaActual);
            
            request.setAttribute("gd_usuario", usuSession);
            
            this.getwallets(request);
        } catch (Exception e) {
        }
        
        try {
            this.getServletConfig().getServletContext()
                    .getRequestDispatcher("/views/usersmanagement.jsp").forward(request, response);
        } catch (Exception e) {
            request.setAttribute("msje", "No se pudo cargar la vista");
        }
    }
    
    private void getwallets(HttpServletRequest request) throws Exception {
        Connection con = null;
        con = Conection.getConexion();
        
        gd_gestioncarteraDAO dao = new gd_gestioncarteraDAO(con);
        av_cartera car = new av_cartera();
        gd_usuario usuSession = (gd_usuario)request.getSession().getAttribute("gd_usuarioSession");
        car.setIdUsuario(usuSession.getIdUsuario());
       List<av_cartera> av_carteras = new ArrayList<av_cartera>();
        try {
            av_carteras = dao.listarCarteras(car);
            request.setAttribute("av_carteras", av_carteras);
        } catch (Exception e) {
            request.setAttribute("msje", "No se pudo cargar los Perfiles :( " + e.getMessage());
        } finally {
            dao = null;
            av_carteras = null;
        }
    }
    
    private void searchnegotiations(HttpServletRequest request, HttpServletResponse response) throws Exception {
        if (request.getSession().getAttribute("gd_usuarioSession") == null) {
            this.getServletConfig().getServletContext()
                    .getRequestDispatcher("/login.jsp").forward(request, response);
        }
        
        String valor1 = request.getParameter("txtidUsuario");
        String valor2 = request.getParameter("txtnombreUsuario");
        String valor3 = request.getParameter("cboCartera");
        
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
            Logger.getLogger(gd_gestioncarteraSRV.class.getName()).log(Level.SEVERE, null, ex);
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
            Logger.getLogger(gd_gestioncarteraSRV.class.getName()).log(Level.SEVERE, null, ex);
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