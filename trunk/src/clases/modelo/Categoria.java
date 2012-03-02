/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package clases.modelo;

/**
 *
 * @author Patricio Pérez Pinto
 */
public class Categoria {
    private String codigo;
    private String nombre;

    public Categoria(String codigo, String nombre) {
        this.codigo = codigo;
        this.nombre = nombre;
    }

    public String getCodigo() {
        return codigo;
    }

    public void setCodigo(String codigo) {
        this.codigo = codigo;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    
}
