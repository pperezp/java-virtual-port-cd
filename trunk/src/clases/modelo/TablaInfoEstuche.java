/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package clases.modelo;

import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author Fabiola Muñoz
 */
public class TablaInfoEstuche {
    private List<CategoriaTabla> listaCategoria = new ArrayList<CategoriaTabla>();

    public List<CategoriaTabla> getListaCategoria() {
        return listaCategoria;
    }

    public void setListaCategoria(List<CategoriaTabla> listaCategoria) {
        this.listaCategoria = listaCategoria;
    }

    public void addCategoriaTabla(CategoriaTabla categoria){
        listaCategoria.add(categoria);
    }

   


}
