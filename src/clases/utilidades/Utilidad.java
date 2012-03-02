package clases.utilidades;
/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author Patricio Pérez Pinto
 */
import clases.hsql.conexion.Conexion;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;

public class Utilidad {

    public static void llenarCombo(String campo, String tabla, javax.swing.JComboBox combo){
        combo.removeAllItems();
        try {
            Conexion.sentencia = Conexion.con.createStatement();
            Conexion.rs = Conexion.sentencia.executeQuery("SELECT "+campo+ " FROM "+tabla);
            while(Conexion.rs.next()){
                combo.addItem(Conexion.rs.getString(campo));
            }
            Conexion.sentencia.close();
        } catch (SQLException ex) {
            Logger.getLogger(Utilidad.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    public static String SO = System.getProperty("os.name");
}
