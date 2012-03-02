/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package clases.hsql.conexion;

import clases.hsql.modelo.BaseDeDatos;
import java.io.File;
import java.sql.Statement;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;

/**
 *
 * @author Administrador
 */
public class ConexionAux {

    public static Connection con = null;
    public static Statement sentencia = null;
    public static ResultSet rs = null;
    private static String parametros;

    public static void conexion(String server, String db, String uid, String pwd) {
        try {
            parametros = "jdbc:odbc:Driver={SQL Server};"
                    + "server=" + server + ";"
                    + "DataBase=" + db + ";"
                    + "Uid=" + uid + ";"
                    + "Pwd=" + pwd + ";";
            Class.forName("sun.jdbc.odbc.JdbcOdbcDriver");
            con = DriverManager.getConnection(parametros);
        } catch (SQLException e) {
        }catch(ClassNotFoundException ex ){}
    }

//    public static void conexionHSQL() {
//        try {
//            parametros = "jdbc:hsqldb:file:BD";
//            Class.forName("org.hsqldb.jdbcDriver");
//            con = DriverManager.getConnection(parametros);
//            String tabla;
//            tabla = "create table categoria("
//                    + "cod_cat INTEGER IDENTITY primary key,"
//                    + "nom_cat varchar(50))";
//            crearTabla(tabla);
//
//            tabla = "create table estuche("
//                    + "cod_est INTEGER IDENTITY primary key,"
//                    + "num_est int)";
//            crearTabla(tabla);
//
//            tabla = "create table cd("
//                    + "cod_cd INTEGER IDENTITY primary key,"
//                    + "esp_cd int,"
//                    + "nom_cd varchar(50),"
//                    + "cod_cat int,"
//                    + "cod_est int,"
//                    + "con_cd longvarchar,"
//                    + "foreign key(cod_cat) references categoria(cod_cat) on delete cascade,"
//                    + "foreign key(cod_est) references estuche(cod_est) on delete cascade"
//                    + ")";
//            crearTabla(tabla);
//        } catch (SQLException | ClassNotFoundException e1) {
//        }
//
//    }

    public static void conexionHSQL(String nombreBD) {
        try {
            parametros = "jdbc:hsqldb:file:"+nombreBD;
            Class.forName("org.hsqldb.jdbcDriver");
            con = DriverManager.getConnection(parametros);
            crearBD(BaseDeDatos.getScriptBaseDeDatos());
        } catch (ClassNotFoundException e1) {
        }catch(SQLException e){
        
        }
    }
    
    private static void crearBD(String query) {
        try {
            sentencia = con.createStatement();
            sentencia.executeUpdate(query);
            sentencia.close();
        } catch (SQLException ex) {
        }
    }
    
    public static void guardarCambias(){
        try {
            sentencia = con.createStatement();
            sentencia.executeUpdate("SHUTDOWN");
            sentencia.close();
            con.close();
        } catch (Exception ex) {
            ex.printStackTrace();
            System.out.println(ex.getMessage());
        }
    }
}
