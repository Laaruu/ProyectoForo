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
public class EditarEntradaa {
    
    public int update (String id, String titulo, String contenido) throws Exception {
        
        /* Con esta clase llamamos a la clase MySQL con el método Update 
         * y le pasamos los parámetros que queremos editar/modificar */
        MySQL conn = new MySQL();
        String param = "titulo = '" + titulo + "', contenido = '" + contenido ;
        conn.Update(id, "entrada", param);
     
        return 1;
    }
}

