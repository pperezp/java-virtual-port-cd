/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package clases.modelo;

/**
 *
 * @author Administrador
 */
public class Estuche {
    private String codigo;
    private String nombre;

    public Estuche(String codigo, String numero) {
        this.codigo = codigo;
        this.nombre = "Estuche "+numero;
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

    public void setNombre(String numero) {
        this.nombre = "Estuche "+numero;
    }


}
