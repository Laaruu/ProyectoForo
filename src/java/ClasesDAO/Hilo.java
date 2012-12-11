/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package ClasesDAO;

import ClaseMySQL.MySQL;
import Pojos.POJOHilo;
import java.sql.ResultSet;
import java.util.LinkedList;

/**
 *
 * @author Laura Rubio
 */
public class Hilo {

    public Hilo() {
    }

    /* Creamos el metodo "MostrarTema" para mostrar */
    public POJOHilo MostrarTema(POJOHilo p) throws Exception {
        MySQL conn = new MySQL();
        boolean i = conn.AbrirConexion();
        p.setNombre(conn.getOneRafa("hilo", "nombre", p.getId()));
        return p;
    }

    /* Creamos el metodo "MostrarTemas" para mostrar la lista de hilos (temas) en la página jsp  */
    public LinkedList MostrarTemas() {
        String sql;
        LinkedList<POJOHilo> lista = new LinkedList();
        try {

            MySQL conn = new MySQL();

            boolean i = conn.AbrirConexion();

            sql = "select * from hilo order by fecha";


            ResultSet rs = conn.Get(sql);

            while (rs.next()) {

                POJOHilo pojo = new POJOHilo();

                pojo.setId(rs.getInt("id"));
                pojo.setNombre(rs.getString("nombre"));;
                pojo.setFecha(rs.getDate("fecha"));

                lista.add(pojo);

            }

        } catch (Exception e) {
        }
        return lista;
    }

    public LinkedList MostrarEditar(String id) {
        String sql;
        LinkedList<POJOHilo> lista = new LinkedList();
        try {

            MySQL conn = new MySQL();

            boolean i = conn.AbrirConexion();

            sql = "select * from hilo where id = " + id;


            ResultSet rs = conn.Get(sql);

            while (rs.next()) {

                POJOHilo pojo = new POJOHilo();

                pojo.setId(rs.getInt("id"));
                pojo.setNombre(rs.getString("nombre"));;
                pojo.setFecha(rs.getDate("fecha"));

                lista.add(pojo);

            }

        } catch (Exception e) {
        }
        return lista;
    }
    
    public LinkedList MostrarBuscar(String buscando) {
        String sql;
        LinkedList<POJOHilo> lista = new LinkedList();
        try {

            MySQL conn = new MySQL();

            boolean i = conn.AbrirConexion();

            sql = "select * from hilo where nombre like (trim('" + buscando + "'))";


            ResultSet rs = conn.Get(sql);

            while (rs.next()) {

                POJOHilo pojo = new POJOHilo();

                pojo.setId(rs.getInt("id"));
                pojo.setNombre(rs.getString("nombre"));;
                pojo.setFecha(rs.getDate("fecha"));

                lista.add(pojo);

            }

        } catch (Exception e) {
        }
        return lista;
    }
}
