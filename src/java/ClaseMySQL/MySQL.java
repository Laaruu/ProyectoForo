/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package ClaseMySQL;

import java.sql.Connection;
import java.sql.DatabaseMetaData;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

/**
 *
 * @author Laura Rubio
 */
public class MySQL {

    protected static Connection con = null;
    protected static Statement stmt;

    // AbrirConexion: permite conectarse a la base de datos
    public boolean AbrirConexion() throws Exception {

        try {

            Class.forName("com.mysql.jdbc.Driver");
            String urlOdbc = "jdbc:mysql://localhost:3307/proyecto01";
            con = (Connection) (java.sql.DriverManager.getConnection(
                    urlOdbc, "root", "laaruu"));

        } catch (Exception e) {
            e.printStackTrace();
            throw new Exception("No se ha podido establecer la conexion " + e.getMessage());
        }

        if (con == null) {
            return false;
        } else {
            return true;
        }
    }

    // CerrarConexion: permite desconectarse de la base de datos
    public void CerrarConexion() throws Exception {

        try {

            if (con != null) {
                con.close();
            }

        } catch (SQLException e) {
            e.printStackTrace();
            throw new Exception("No se ha podido cerrar la conexion " + e.getMessage());
        }
    }

    // Inicia una transacción
    public void InitTrans() throws Exception {

        try {

            if (con != null) {
                con.setAutoCommit(false);
                stmt = con.createStatement();
            } else {
                throw new Exception("No está conectado a la base de datos.");
            }

        } catch (SQLException ex) {
            throw new Exception("No se ha podido iniciar la transacción: " + ex.getMessage());
        }
    }

    // Hace commit de una transacción
    public void CommitTrans() throws Exception {

        try {

            if (con != null) {
                con.commit();
            } else {
                throw new Exception("No está conectado a la base de datos.");
            }

        } catch (SQLException ex) {
            throw new Exception("No se ha podido realizar commit de la transacción: " + ex.getMessage());
        }
    }

    // Hace rollback de una transacción
    public void RollbackTrans() throws Exception {

        try {

            if (con != null) {
                con.rollback();
            } else {
                throw new Exception("No está conectado a la base de datos.");
            }

        } catch (SQLException ex) {
            throw new Exception("No se ha podido realizar rollback de la transacción: " + ex.getMessage());
        }
    }

    public String getOneRafa(String tabla, String campo, int id)
            throws Exception {
        Statement stmt = null;
        ResultSet rs = null;
        try {
            stmt = (Statement) con.createStatement();
            String sql = "SELECT " + campo + " FROM  " + tabla + " WHERE  id="
                    + Integer.toString(id);
            rs = stmt.executeQuery(sql);
            rs.first();
            return rs.getString(campo);
        } catch (Exception ex) {
            throw new Exception(
                    "mysql.getOne: No se ha podido realizar la consulta: "
                    + ex.getMessage());
        }
    }

    // Borra una fila
    public boolean DeleteOne(int id, String tabla) {
        Statement stmt1;
        boolean result = false;

        try {
            if (con != null) {
                String consulta = "DELETE FROM " + tabla + " WHERE id =" + id;
                stmt1 = con.createStatement();
                stmt1.executeUpdate(consulta);
                result = true;
            } else {
                throw new Exception("No está conectado a la base de datos.");
            }

        } catch (SQLException ex) {
            result = false;
            throw new Exception("No se ha podido borrar la fila: " + ex.getMessage());
        } finally {
            return result;
        }
    }

    // Realiza el update de ese campo en esa fila de la tabla. 
    public boolean Update(String id, String tabla, String param) throws Exception {
        Statement stmt2;
        boolean result = false;
        try {
            if (con != null) {
                stmt2 = con.createStatement();
                String sql = "UPDATE " + tabla + " SET " + param + "' WHERE id = " + id;
                stmt2.executeUpdate(sql);
                result = true;
            } else {
                throw new Exception("No está conectado a la base de datos.");
            }

        } catch (SQLException ex) {
            result = false;
            throw new Exception("No se ha podido borrar la fila: " + ex.getMessage());
        } finally {
            return result;
        }
    }

    // Inserta un registro en la tabla entrada
    public boolean InsertEntrada(int id, String tabla, String param) {
        Statement stmt3;
        boolean resultado = false;
        try {
            if (con != null) {
                stmt3 = con.createStatement();
                stmt3.executeUpdate("INSERT INTO " + tabla + "( id, titulo, contenido, id_usuario, id_hilo, fecha) "
                        + "VALUES (null, '" + param + "')");
                resultado = true;
            } else {
                throw new Exception("No está conectado a la base de datos.");
            }
        } catch (SQLException ex) {
            resultado = false;
            throw new Exception("No se ha podido insertar el registro: " + ex.getMessage());
        } finally {
            return resultado;
        }

    }

    // Inserta un registro en la tabla hilo
    public boolean InsertHilo(int id, String tabla, String param) {
        Statement stmt3;
        boolean resultado = false;
        try {
            if (con != null) {
                stmt3 = con.createStatement();
                stmt3.executeUpdate("INSERT INTO " + tabla + "( id, nombre, fecha) VALUES (null, '" + param + "')");
                resultado = true;
            } else {
                throw new Exception("No está conectado a la base de datos.");
            }
        } catch (SQLException ex) {
            resultado = false;
            throw new Exception("No se ha podido insertar el registro: " + ex.getMessage());
        } finally {
            return resultado;
        }

    }

    // Devuelve sólo el campo indicado de la primera fila de una select 
    public int GetOne(String tabla, List condiciones) {
        int resultado = 0;
        try {
            Statement st = con.createStatement(ResultSet.TYPE_SCROLL_SENSITIVE,
                    ResultSet.CONCUR_READ_ONLY);

            String sql = "SELECT * FROM " + tabla + " WHERE 1=1";

            if (condiciones != null && !condiciones.isEmpty()) {
                for (int i = 0; i < condiciones.size(); i++) {
                    sql += " AND " + condiciones.get(i);
                }
            }

            ResultSet resultSet = st.executeQuery(sql);
            resultSet.first();

            resultado = resultSet.getInt("id");
        } catch (SQLException ex) {
            resultado = 0;
            throw new Exception("No se ha podido realizar la consulta: " + ex.getMessage());
        } finally {
            return resultado;
        }
    }

    // Devuelve el número de páginas
    public int GetPages(String tabla, String campos, List condiciones,
            int limit) {
        int resultado = 0;
        try {
            Statement st = con.createStatement(ResultSet.TYPE_SCROLL_SENSITIVE,
                    ResultSet.CONCUR_READ_ONLY);

            String sql = "SELECT " + campos + " FROM " + tabla + " WHERE 1=1";

            if (condiciones != null) {
                for (int i = 0; i < condiciones.size(); i++) {
                    sql += " AND " + condiciones.get(i);
                }
            }

            ResultSet resultSet = st.executeQuery(sql);
            resultSet.first();

            int reg = 0;

            do {
                reg++;
            } while (resultSet.next());

            resultado = reg / limit;
        } catch (SQLException ex) {
            resultado = 0;
            throw new Exception("No se ha podido realizar la consulta: " + ex.getMessage());
        } finally {
            return resultado;
        }
    }

    // Devuelve un array con los ID's de la página
    public ArrayList GetPage(String tabla, String campos, List condiciones,
            int pagina, int limit) {
        ArrayList arr = new ArrayList();

        try {
            Statement st = con.createStatement(ResultSet.TYPE_SCROLL_SENSITIVE,
                    ResultSet.CONCUR_READ_ONLY);

            String sql = "SELECT " + campos + " FROM " + tabla + " WHERE 1=1 ";

            if (condiciones != null) {
                for (int i = 0; i < condiciones.size(); i++) {
                    sql += " AND " + condiciones.get(i);
                }
            }

            sql += " LIMIT " + pagina + ", " + limit;

            ResultSet resultSet = st.executeQuery(sql);
            resultSet.first();

            do {
                arr.add(resultSet.getInt("id"));
            } while (resultSet.next());
        } catch (SQLException ex) {
            arr.add("0");
            throw new Exception("No se ha podido realizar la consulta: " + ex.getMessage());
        } finally {
            return arr;
        }
    }

    /*
     * Devuelve un HashMap con los campos de la fila y 
     * sus valores (para rellenar un pojo) de la primera fila de una select 
     */
    public HashMap GetRow(String tabla, int id) {
        HashMap<String, String> fila = new HashMap();

        try {
            Statement st = con.createStatement(ResultSet.TYPE_SCROLL_SENSITIVE,
                    ResultSet.CONCUR_READ_ONLY);

            String sql = "SELECT * FROM " + tabla + " WHERE ID = " + id;

            ResultSet resultSet = st.executeQuery(sql);
            resultSet.first();

            ArrayList<String> colNames = (ArrayList) GetColNames(tabla);

            for (int i = 0; i < colNames.size(); i++) {
                fila.put(colNames.get(i), resultSet.getString(colNames.get(i)));
            }
        } catch (SQLException ex) {
            fila.put("0", "0");
            throw new Exception("No se ha podido realizar la consulta: " + ex.getMessage());
        } finally {
            return fila;
        }
    }

    //Obtiene los nombres de las columnas de la tabla indicada
    public ArrayList GetColNames(String tabla) {
        ArrayList<String> colNames = new ArrayList();

        try {
            DatabaseMetaData metaData = con.getMetaData();
            ResultSet rs = metaData.getColumns(null, null, tabla, null);

            while (rs.next()) {
                colNames.add(rs.getString(4).toUpperCase());
            }
        } catch (SQLException ex) {
            colNames.add("0");
            throw new RuntimeException("No se ha podido realizar la consulta: " + ex.getMessage());
        } finally {
            return colNames;
        }
    }

    public ResultSet Get(String sql) throws Exception {
        ResultSet resultset = null;
        PreparedStatement stmt = null;
        String SqlGet = sql;
        try {

            stmt = con.prepareStatement(SqlGet);
            resultset = stmt.executeQuery();
            return resultset;



        } catch (SQLException e) {


            e.getStackTrace();
            throw new Exception("No va el Get " + e.getMessage());
        }

    }
}
