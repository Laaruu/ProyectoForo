/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package ServletsMantenimiento;

import DAOMantenimiento.InsertarEntrada;
import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 *
 * @author Laura Rubio
 */
@WebServlet(name = "NuevaEntrada", urlPatterns = {"/NuevaEntrada"})
public class NuevaEntrada extends HttpServlet {

    /**
     * Processes requests for both HTTP
     * <code>GET</code> and
     * <code>POST</code> methods.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        PrintWriter out = response.getWriter();
        try {

            /* Recogemos los parámetros añadidos en el jsp para poder añadir una entrada
             * y los declaramos en variables*/
            String titulo = request.getParameter("titulo");
            String contenido = request.getParameter("contenido");
            String id_usuario = request.getParameter("id_usuario");
            String id_hilo = request.getParameter("idHilo");
            String fecha = request.getParameter("fecha");

            /* Llamamos a la clase DAO InsertarEntrada y le pasamos las variables para poder crearlas */
            InsertarEntrada ie = new InsertarEntrada();
            int i = ie.insert(0, titulo, contenido, id_usuario, id_hilo, fecha);

            getServletContext().getRequestDispatcher("/MostrarContenidoEntrada").forward(request, response);

        } finally {
            out.close();
        }
    }

    // <editor-fold defaultstate="collapsed" desc="HttpServlet methods. Click on the + sign on the left to edit the code.">
    /**
     * Handles the HTTP
     * <code>GET</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    /**
     * Handles the HTTP
     * <code>POST</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    /**
     * Returns a short description of the servlet.
     *
     * @return a String containing servlet description
     */
    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>
}
