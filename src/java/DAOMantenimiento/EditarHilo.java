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
public class EditarHilo {

    public int update(String id, String nombre, String fecha) throws Exception {

        /* Con esta clase llamamos a la clase MySQL con el método Update 
         * y le pasamos los parámetros que queremos editar/modificar */
        MySQL conn = new MySQL();
        String param = "nombre = '" + nombre + "', fecha = '" + fecha;
        conn.Update(id, "hilo", param);

        return 1;
    }
}
