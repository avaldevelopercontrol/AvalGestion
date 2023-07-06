package Controller;

import Dao.av_DocxCobrarOpeDAO;
import Dao.av_clienteDAO;
import Dao.gd_gestioncarteraDAO;
import Dao.gd_gestiondeudorDAO;
import Dao.gd_tipobusquedaDAO;
import Models.Conection;
import Models.av_DocxCobrarOpe;
import Models.av_cartera;
import Models.av_cliente;
import Models.gd_gestioncartera;
import Models.gd_gestiondeudor;
import Models.gd_tipobusqueda;
import Models.gd_usuario;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.util.ArrayList;
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
                } else if (action.equals("getmanagementtypifications")) {
                    getmanagementtypifications(request, response);
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
        
        int idUsuario = 0;
        av_cartera car = new av_cartera();
        gd_usuario usuSession = (gd_usuario)request.getSession().getAttribute("gd_usuarioSession");
        
        try {
            idUsuario = usuSession.getIdUsuario();
            car.setIdUsuario(idUsuario);
            request.setAttribute("gd_usuario", usuSession);
            this.getwallets(request);
            this.getsearchtype(request);
        } catch (Exception e) {
        } finally {
            usuSession = null;
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
    
    private void getsearchtype(HttpServletRequest request) throws Exception {
        
        Connection con = null;
        con = Conection.getConexion();
        
        gd_tipobusquedaDAO dao = new gd_tipobusquedaDAO(con);
        
        List<gd_tipobusqueda> gd_tipobusquedaLst = new ArrayList<gd_tipobusqueda>();
        try {
            gd_tipobusquedaLst = dao.listarTipoBusqueda();
            request.setAttribute("gd_tipobusquedas", gd_tipobusquedaLst);
        } catch (Exception e) {
            request.setAttribute("msje", "No se pudo cargar los Tipo de Busqueda :( " + e.getMessage());
        } finally {
            dao = null;
            gd_tipobusquedaLst = null;
        }
    }

    private void searchnegotiations(HttpServletRequest request, HttpServletResponse response) throws Exception {
        
        if (request.getSession().getAttribute("gd_usuarioSession") == null) {
            this.getServletConfig().getServletContext()
                    .getRequestDispatcher("/login.jsp").forward(request, response);
        }
        
        gd_usuario usuSession = (gd_usuario)request.getSession().getAttribute("gd_usuarioSession");
        
        Connection con = null;
        con = Conection.getConexion();
        gd_gestioncarteraDAO dao = new gd_gestioncarteraDAO(con);
        gd_gestiondeudorDAO daoDeudor = new gd_gestiondeudorDAO(con);
        
        av_clienteDAO daoCli = new av_clienteDAO(con);
        av_cliente beCli = new av_cliente();
        av_cartera beCar = new av_cartera();
        List<gd_gestioncartera> lstGestionCarteras = new ArrayList<gd_gestioncartera>();
        List<gd_gestiondeudor> lstGestionDeudores = new ArrayList<gd_gestiondeudor>();
        List<av_DocxCobrarOpe> lstGesConNoCon = new ArrayList<av_DocxCobrarOpe>();
        
        gd_gestioncartera beGesCar = new gd_gestioncartera();
        
        try {
            String Validacion = "";
            
            int idCartera = Integer.parseInt(request.getParameter("cboCartera"));
            String cTipoBusqueda = request.getParameter("cboBuscarPor");
            
            if (request.getParameter("txtEncontrarPor").equals("") &&
                !request.getParameter("cboBuscarPor").equals("0")) {
                Validacion = "- Si selecciona en Buscar Por, es obligatorio ingresar información de búsqueda -";
                request.setAttribute("msjValida", Validacion);
                this.getwallets(request);
                this.getsearchtype(request);
                request.setAttribute("lstGestiones", lstGestionCarteras);
                request.setAttribute("lstGestionDeudores", lstGestionDeudores);
                request.setAttribute("lstGesConNoCon", lstGesConNoCon);
                request.setAttribute("gd_usuario", usuSession);
                request.setAttribute("gd_gestioncartera", beGesCar);
                request.setAttribute("idCartera", String.valueOf(idCartera));
                request.setAttribute("cTipoBusqueda", String.valueOf(cTipoBusqueda));
                
                this.getServletConfig().getServletContext().getRequestDispatcher("/views/usersmanagement.jsp").forward(request, response);
                return;
            }
                
            if (request.getParameter("txtnombreUsuario") == "" ) {
                Validacion = "- Usuario Login faltante -";
                request.setAttribute("msjValida", Validacion);
                this.getwallets(request);
                this.getsearchtype(request);
                request.setAttribute("lstGestiones", lstGestionCarteras);
                request.setAttribute("lstGestionDeudores", lstGestionDeudores);
                request.setAttribute("lstGesConNoCon", lstGesConNoCon);
                request.setAttribute("gd_usuario", usuSession);
                request.setAttribute("gd_gestioncartera", beGesCar);
                request.setAttribute("idCartera", String.valueOf(idCartera));
                request.setAttribute("cTipoBusqueda", String.valueOf(cTipoBusqueda));
                
                this.getServletConfig().getServletContext().getRequestDispatcher("/views/usersmanagement.jsp").forward(request, response);
                return;
            }
            
            if (request.getParameter("cboCartera").equals("0")) {
                Validacion = Validacion + "- Seleccione Cartera -";
                request.setAttribute("msjValida", Validacion);
                this.getwallets(request);
                this.getsearchtype(request);
                request.setAttribute("lstGestiones", lstGestionCarteras);
                request.setAttribute("lstGestionDeudores", lstGestionDeudores);
                request.setAttribute("lstGesConNoCon", lstGesConNoCon);
                request.setAttribute("gd_usuario", usuSession);
                request.setAttribute("gd_gestioncartera", beGesCar);
                request.setAttribute("idCartera", String.valueOf(idCartera));
                request.setAttribute("cTipoBusqueda", String.valueOf(cTipoBusqueda));
                
                this.getServletConfig().getServletContext().getRequestDispatcher("/views/usersmanagement.jsp").forward(request, response);
                return;
            }
            
            //Inicio - Obtener el Id del Cliente
            beCar.setnId_Cartera(idCartera);
            beCli = daoCli.getClientexCartera(beCar);
            //Fin - Obtener el Id del Cliente

            //Inicio - Gestion Carteras
            beGesCar.setnId_Cliente(beCli.getnId_Cliente());
            beGesCar.setnId_Cartera(idCartera);
            beGesCar.setcTipoBusqueda(request.getParameter("cboBuscarPor"));
            beGesCar.setcPers_CodCliente(request.getParameter("txtEncontrarPor"));
            beGesCar.setcPers_RUC(request.getParameter("txtEncontrarPor"));
            beGesCar.setcPers_DNI(request.getParameter("txtEncontrarPor"));
            beGesCar.setdDocCobOpe_FecIni(request.getParameter("dtpFechaDesde"));
            beGesCar.setdDocCobOpe_FecFin(request.getParameter("dtpFechaHasta"));

            lstGestionCarteras = dao.listarGestionCarteras(beGesCar);
            //Fin - Gestion Carteras

            //Inicio - Gestion Deudores
            lstGestionDeudores = daoDeudor.listarGestionDeudores(beCli.getnId_Cliente(), idCartera, 
                                                                 beGesCar.getcTipoBusqueda(), 
                                                                 beGesCar.getcPers_CodCliente(), 
                                                                 beGesCar.getcPers_RUC(), 
                                                                 beGesCar.getcPers_DNI(),
                                                                 beGesCar.getdDocCobOpe_FecIni(),
                                                                 beGesCar.getdDocCobOpe_FecFin()
                                                                 );
            //Fin - Gestion Deudores
            
            //Inicio - Gestion Contacto / No Contacto
            lstGesConNoCon = daoDeudor.getContactoNoContacto(beCli.getnId_Cliente(), idCartera, 
                                                            beGesCar.getcTipoBusqueda(), 
                                                            beGesCar.getcPers_CodCliente(), 
                                                            beGesCar.getcPers_RUC(), 
                                                            beGesCar.getcPers_DNI(),
                                                            beGesCar.getdDocCobOpe_FecIni(),
                                                            beGesCar.getdDocCobOpe_FecFin());
            //Fin - Gestion Contacto / No Contacto
            
            
            
            this.getwallets(request);
            this.getsearchtype(request);
            request.setAttribute("lstGestiones", lstGestionCarteras);
            request.setAttribute("lstGestionDeudores", lstGestionDeudores);
            request.setAttribute("lstGesConNoCon", lstGesConNoCon);
            request.setAttribute("gd_usuario", usuSession);
            request.setAttribute("gd_gestioncartera", beGesCar);
            request.setAttribute("idCartera", String.valueOf(idCartera));
            request.setAttribute("cTipoBusqueda", String.valueOf(cTipoBusqueda));
            request.setAttribute("msjValida", "");
        } catch (Exception e) {
            request.setAttribute("msje", "No se pudo cargar la vista");
        } finally {
            lstGestionCarteras = null;
            lstGestionDeudores = null;
            lstGesConNoCon = null;
            daoCli = null;
            beGesCar = null;
            usuSession = null;
        }
        
        try {
            this.getServletConfig().getServletContext()
                    .getRequestDispatcher("/views/usersmanagement.jsp").forward(request, response);
        } catch (Exception e) {
            request.setAttribute("msje", "No se pudo cargar la vista");
        }
        
    }
    
    private void getmanagementtypifications(HttpServletRequest request, HttpServletResponse response) throws Exception {
        
        if (request.getSession().getAttribute("gd_usuarioSession") == null) {
            this.getServletConfig().getServletContext()
                    .getRequestDispatcher("/login.jsp").forward(request, response);
        }
        
        Connection con = null;
        con = Conection.getConexion();
        
        av_DocxCobrarOpeDAO dao = new av_DocxCobrarOpeDAO(con);
        av_clienteDAO daoCli = new av_clienteDAO(con);
        
        av_DocxCobrarOpe be = new av_DocxCobrarOpe();
        av_cartera car = new av_cartera();
        av_cliente beCli = new av_cliente();
        
        gd_usuario usuSession = (gd_usuario)request.getSession().getAttribute("gd_usuarioSession");
        car.setIdUsuario(usuSession.getIdUsuario());
        
        List<av_DocxCobrarOpe> lstGesTipi = new ArrayList<av_DocxCobrarOpe>();
        
        try {
            
            int idCartera = Integer.parseInt(request.getParameter("nId_Cartera"));
            //Inicio - Obtener el Id del Cliente
            car.setnId_Cartera(idCartera);
            beCli = daoCli.getClientexCartera(car);
            //Fin - Obtener el Id del Cliente
            
            //Inicio - Gestion Carteras
            be.setnId_Cliente(beCli.getnId_Cliente());
            be.setnId_Cartera(idCartera);
            be.setnId_OpeCodOut(Integer.parseInt(request.getParameter("nId_OpeCodOut")));
            be.setdDocCobOpe_FecIni(request.getParameter("dDesde"));
            be.setdDocCobOpe_FecFin(request.getParameter("dHasta"));

            lstGesTipi = dao.listarGestionTipificacion(be);
            //Fin - Gestion Carteras
            
            response.setContentType("application/json");
            response.getWriter().printf(new Gson().toJson(lstGesTipi, new TypeToken<List<av_DocxCobrarOpe>>(){}.getType()));
//            request.setAttribute("av_carteras", av_carteras);
        } catch (Exception e) {
            request.setAttribute("msje", "No se pudo cargar los Perfiles :( " + e.getMessage());
        } finally {
            dao = null;
            lstGesTipi = null;
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