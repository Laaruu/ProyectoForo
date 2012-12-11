/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package Conexion;

import ClasesDAO.Hilo;
import ClasesDAO.Usuario;
import Pojos.POJOUsuario;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.LinkedList;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 *
 * @author Laura Rubio
 */
@WebServlet(name = "Login", urlPatterns = {"/Login"})
public class Login extends HttpServlet {

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

        try {

            response.setContentType("text/html;charset=UTF-8");
            PrintWriter out = response.getWriter();

            /* Recogemos los parámetros y los ponemos en variables para utilizarlos*/
            String usuario = request.getParameter("usuario");
            String password = request.getParameter("password");

            HttpSession session = request.getSession();

            /* Establecemos y recogemos los parámetros usuario y password */
            POJOUsuario user = new POJOUsuario();
            user.setLogin(usuario);
            user.setPassword(password);

            /* Llamamos a la clase DAO Usuario para verificar el usuario y el password insertados */
            Usuario ClassDAO = new Usuario();
            user = ClassDAO.autentificar(user);


            /* Si la id del usuario es distinta de 0, mostramos el siguiente jsp con la lista cargada */
            if (user.getId() != 0) {


                session.setAttribute("id", user);


                Hilo h = new Hilo();
                LinkedList<Hilo> lista = h.MostrarTemas();

                request.setAttribute("datos", lista);


                String url = "/hiloForo.jsp";

                ServletContext cont = getServletConfig().getServletContext();
                RequestDispatcher reqDispatcher = cont.getRequestDispatcher(url);
                reqDispatcher.forward(request, response);

            } else {
                response.sendRedirect("index.jsp");
            }

        } catch (Exception e) {
        }
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
