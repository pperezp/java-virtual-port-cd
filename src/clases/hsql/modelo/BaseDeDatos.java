/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package clases.hsql.modelo;

import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author Patricio Pérez Pinto
 */
public class BaseDeDatos {
    private static List<Tabla> tablas = new ArrayList<Tabla>();
    
    public static int getCantidadDeTablas(){
        return tablas.size();
    }
    
    public static void agregarTabla(Tabla tabla){
        tablas.add(tabla);
    }
    
    public static String getScriptBaseDeDatos(){
        String script = "";
        for(int i=0; i<BaseDeDatos.getCantidadDeTablas(); i++){
            script += tablas.get(i).getTablaComoScript();
        }
        return script;
    }
    
    public static Tabla getTablaAt(int i){
        return tablas.get(i);
    }
}
