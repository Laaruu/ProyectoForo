/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package DAOMantenimiento;

import ClaseMySQL.MySQL;

/**
 *
 * @author Laura Rubio
 */
public class InsertarEntrada {

    public int insert(int id, String titulo, String contenido, String id_usuario, String id_hilo, String fecha) {

        /* Con esta clase llamamos a la clase MySQL con el método InsertEntrada
         * y le pasamos los parámetros que queremos crear */
        MySQL conn = new MySQL();
        String param = titulo + "', '" + contenido + "', '" + id_usuario + "', '" + id_hilo + "', '" + fecha;
        System.out.println("param = " + param);
        conn.InsertEntrada(0, "entrada", param);

        return 1;
    }
}
