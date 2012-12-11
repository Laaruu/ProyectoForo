/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package ClasesDAO;

import ClaseMySQL.MySQL;
import Pojos.POJOUsuario;
import java.sql.ResultSet;

/**
 *
 * @author Laura Rubio
 */
public class Usuario {

    /*  Creamos el método autentificar para establecer y obtener las variables de la sesión */
    public POJOUsuario autentificar(POJOUsuario user) {
        String sql;
        try {

            MySQL conn = new MySQL();

            boolean i = conn.AbrirConexion();

            if (i == true) {
                sql = "select * from usuario where login= '" + user.getLogin() + "' and password = '" + user.getPassword() + "'";

                ResultSet result = conn.Get(sql);

                if (result.next()) {
                    user.setId(result.getInt("id"));
                    user.setNombre(result.getString("nombre"));
                    user.setApe1(result.getString("ape1"));
                    user.setApe2(result.getString("ape2"));
                    user.setLogin(result.getString("login"));
                    user.setPassword(result.getString("password"));
                    user.setEmail(result.getString("email"));
                    user.setTelefono(result.getString("telefono"));
                    user.setIdTipoUsuario(result.getInt("id_tipo_usuario"));


                }
            }

        } catch (Exception e) {
        }
        return user;

    }
}
