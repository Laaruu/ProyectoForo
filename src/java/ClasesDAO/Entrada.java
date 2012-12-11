/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package ClasesDAO;

import ClaseMySQL.MySQL;
import Pojos.POJOEntrada;
import Pojos.POJOHilo;
import java.sql.ResultSet;
import java.util.LinkedList;

/**
 *
 * @author Laura Rubio
 */
public class Entrada {

    public Entrada() {
    }

    /* Creamos el metodo "MostrarComent" para mostrar la lista de entradas (comentarios) en la página jsp */
    public static LinkedList MostrarComent(POJOHilo pojo2) {
        String sql;
        LinkedList<POJOEntrada> comentario = new LinkedList();
        try {

            MySQL conn = new MySQL();

            boolean i = conn.AbrirConexion();

            sql = "select * from entrada where id_hilo in (select id from hilo where id = " + pojo2.getId() + ") "
                    + "order by fecha";


            ResultSet rs = conn.Get(sql);

            while (rs.next()) {

                POJOEntrada pojo = new POJOEntrada();

                pojo.setId(rs.getInt("id"));
                pojo.setTitulo(rs.getString("titulo"));
                pojo.setContenido(rs.getString("contenido"));
                pojo.setFecha(rs.getDate("fecha"));
                pojo.setIdUsuario(rs.getInt("id_usuario"));
                pojo.setIdHilo(rs.getInt("id_hilo"));

                comentario.add(pojo);


            }



        } catch (Exception e) {
        }
        return comentario;
    }

    /*
     * Creamos el metodo "MostrarEditar" para mostrar la lista de entradas (comentarios) 
     * y el id del hilo para indicar en cuál nos encontramos
     */
    public static LinkedList MostrarEditar(POJOHilo pojo2, String id) {
        String sql;
        LinkedList<POJOEntrada> comentario = new LinkedList();
        try {

            MySQL conn = new MySQL();

            boolean i = conn.AbrirConexion();

            sql = "select * from entrada where id_hilo in (select id from hilo where id = " + pojo2.getId() + ") "
                    + "id = " + id
                    + " order by fecha";


            ResultSet rs = conn.Get(sql);

            while (rs.next()) {

                POJOEntrada pojo = new POJOEntrada();

                pojo.setId(rs.getInt("id"));
                pojo.setTitulo(rs.getString("titulo"));
                pojo.setContenido(rs.getString("contenido"));
                pojo.setFecha(rs.getDate("fecha"));
                pojo.setIdUsuario(rs.getInt("id_usuario"));
                pojo.setIdHilo(rs.getInt("id_hilo"));

                comentario.add(pojo);


            }



        } catch (Exception e) {
        }
        return comentario;
    }
}
