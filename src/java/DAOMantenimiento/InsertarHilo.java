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
public class InsertarHilo {

    public int insert(int id, String nombre, String fecha) {

        /* Con esta clase llamamos a la clase MySQL con el método InsertEntrada
         * y le pasamos los parámetros que queremos crear */
        MySQL conn = new MySQL();
        String param = nombre + "', '" + fecha;
        System.out.println("param = " + param);
        conn.InsertHilo(0, "hilo", param);

        return 1;
    }
}
