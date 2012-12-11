/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package ServletsMantenimiento;

import ClasesDAO.Entrada;
import ClasesDAO.Hilo;
import DAOMantenimiento.BorrarEntrada;
import DAOMantenimiento.BorrarHilo;
import Pojos.POJOEntrada;
import Pojos.POJOHilo;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.LinkedList;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 *
 * @author Laura Rubio
 */
@WebServlet(name = "Servlet", urlPatterns = {"/Servlet"})
public class Servlet extends HttpServlet {

    String url;

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

            /* Este Servlet lo vamos a utilizar como controlador, ya que los formularios de las páginas jsp
             son mandados aquí, y según lo que se quiera hacer, este Servlet los manda donde corresponda. */
            String op = request.getParameter("operacion");

            String idEntrada = request.getParameter("idEntrada");
            String idHilo = request.getParameter("idHilo");

            switch (op) {

                case "ver":

                    String id = request.getParameter("id");
                    int idid = Integer.parseInt(id);

                    POJOHilo c = new POJOHilo();
                    c.setId(idid);

                    Hilo h = new Hilo();
                    h.MostrarTema(c);


                    if (c.getId() != 0) {


                        LinkedList<POJOEntrada> comentario = Entrada.MostrarComent(c);
                        request.setAttribute("datos1", c);
                        request.setAttribute("datos2", comentario);

                        url = "/entradaForo.jsp";


                    } else {
                        response.sendRedirect("index.jsp");
                    }
                    break;


                case "Borrar":

                    BorrarHilo bh = new BorrarHilo();
                    bh.Borrar(idHilo);

                    getServletContext().getRequestDispatcher("/MostrarContenido").forward(request, response);

                    break;


                case "borrar":

                    BorrarEntrada be = new BorrarEntrada();
                    be.Borrar(idEntrada);

                    getServletContext().getRequestDispatcher("/MostrarContenidoEntrada").forward(request, response);

                    break;


                case "Editar":


                    Hilo h2 = new Hilo();
                    LinkedList<POJOHilo> editable = h2.MostrarEditar(idHilo);
                    request.setAttribute("datos", editable);

                    getServletContext().getRequestDispatcher("/editarHilo.jsp").forward(request, response);

                    break;

                case "editar":


                    POJOHilo ph = new POJOHilo();
                    ph.setId(Integer.parseInt(idHilo));

                    ph.getId();

                    Hilo hh = new Hilo();
                    hh.MostrarTema(ph);

                    LinkedList<POJOEntrada> comentario = Entrada.MostrarEditar(ph, idEntrada);
                    request.setAttribute("datos1", ph);
                    request.setAttribute("datos2", comentario);

                    getServletContext().getRequestDispatcher("/editarEntrada.jsp").forward(request, response);

                    break;

                case "Nuevo":

                    response.sendRedirect("insertarHilo.jsp");
                    break;


                case "Comentar":

                    request.setAttribute("idHilo", idHilo);
                    getServletContext().getRequestDispatcher("/insertarEntrada.jsp").forward(request, response);
                    break;

                case "Cancelar":

                    response.sendRedirect("MostrarContenido");
                    break;

                case "Cerrar":

                    POJOHilo ph2 = new POJOHilo();
                    ph2.setId(Integer.parseInt(idHilo));

                    ph2.getId();

                    Hilo hh2 = new Hilo();
                    hh2.MostrarTema(ph2);

                    LinkedList<POJOEntrada> comentario4 = Entrada.MostrarComent(ph2);
                    request.setAttribute("datos1", ph2);
                    request.setAttribute("datos2", comentario4);
                    getServletContext().getRequestDispatcher("/entradaForo.jsp").forward(request, response);

                    break;

                case "Buscar":

                    String buscando = request.getParameter("buscando");

                    Hilo hb = new Hilo();
                    LinkedList<POJOHilo> buscar = hb.MostrarBuscar(buscando);
                    if (buscar.get(0).getId() == 0) {
                        getServletContext().getRequestDispatcher("MostrarContenido").forward(request, response);
                    } else {
                        int idBuscado = buscar.get(0).getId();

                        POJOHilo c22 = new POJOHilo();
                        c22.setId(idBuscado);

                        Hilo h22 = new Hilo();
                        h22.MostrarTema(c22);

                        LinkedList<POJOEntrada> comentario22 = Entrada.MostrarComent(c22);
                        request.setAttribute("datos1", c22);
                        request.setAttribute("datos2", comentario22);

                        getServletContext().getRequestDispatcher("/entradaForo.jsp").forward(request, response);

                        break;
                    }

            }

            ServletContext cont = getServletConfig().getServletContext();
            RequestDispatcher reqDispatcher = cont.getRequestDispatcher(url);
            reqDispatcher.forward(request, response);

        } catch (Exception e) {
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
