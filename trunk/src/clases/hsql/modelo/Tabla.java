/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package clases.hsql.modelo;

import clases.hsql.modelo.tiposDeDatos.Tipo;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author Patricio Pérez Pinto
 */
public class Tabla {
    private String nombre;
    private List campos = new ArrayList();

    public Tabla(String nombreTabla){
        nombre = nombreTabla;
    }
    
    public List getCampos() {
        return campos;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }
    
    public void setCampo(String nombreCampo, String tipo ){
        this.campos.add(nombreCampo+" "+tipo);
    }
    
    public void setPrimaryKey(String nombreCampo, String tipo){
        this.campos.add(nombreCampo+" "+tipo+" primary key");
    }
    
    public void setPrimaryKeyAutoIncremento(String nombreCampo, String tipo){
        this.campos.add(nombreCampo+" "+tipo+" "+Tipo.autoIncremento.IDENTITY+" primary key");
    }
    /**
     * 
     * @param campo
     * @param tabla
     * @param campoReferencia 
     * Ejemplo
     * Tabla.campos.add("foreign key("+campo+") references "+tabla+"("+campoReferencia+")");
     */
    public void setForeignKey(String campo, String tablaReferencia, String campoReferencia){
        this.campos.add("foreign key("+campo+") references "+tablaReferencia+"("+campoReferencia+")");
    }
    
    public String getTablaComoScript(){
        String script = "";
        script = "create table "+this.nombre+" (\n";
        for(int i=0; i<this.campos.size(); i++){
            if(i == this.campos.size()-1){//estoy en el ultimo campo, no le agrego la coma
                script += campos.get(i)+"\n";
            }else{
                script += campos.get(i)+",\n";
            }
        }
        script +=");\n";
        return script;
    }
}
