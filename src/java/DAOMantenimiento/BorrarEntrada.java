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
public class BorrarEntrada {

    public void Borrar(String id) {

        /* Con esta clase llamamos a la clase MySQL con el método DeleteOne 
         * y le pasamos los parámetros que queremos borrar*/
        MySQL conn = new MySQL();
        int idid = Integer.parseInt(id);
        conn.DeleteOne(idid, "entrada");
    }
}
